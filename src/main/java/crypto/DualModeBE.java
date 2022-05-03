package crypto;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;


public class DualModeBE {

    public static void main(String[] args) {

        int rBits = 160;
        int qBits = 512;
        TypeACurveGenerator pg = new TypeACurveGenerator(rBits, qBits);
        PairingParameters pp = pg.generate();
        it.unisa.dia.gas.jpbc.Pairing bp = PairingFactory.getPairing(pp);
        Field G1 = bp.getG1();
        Field Zr = bp.getZr();
        Field GT = bp.getGT();


//------*********Setup 算法***********---------
        //getPK function
        Element g = G1.newRandomElement().getImmutable();
        int m = 10; // U={1,2,3,4,5,6,7,8,9,10}
        Element[] PK = new Element[m];
        Element[] a = new Element[m]; // MSK a_i
        for(int i = 0; i < m; i++) {
            a[i] = Zr.newRandomElement().getImmutable(); // a_i is the master secret key, it is the random element in Z, a_i
            PK[i] = g.powZn(a[i]);// get PK_i = g^{a_i}
        }

        System.out.println("getPK success!");
        for(int i = 0; i < m; i++) {
            System.out.println("PK_" + i + " = " + PK[i]);
        }


//------*********Extract 算法***********---------
        //getSK function
        int n = 9;
        Element[] ID = new Element[n]; // x_i = hash1(ID_i)
        Element[] sk = new Element[n];
        Element[] Hi = new Element[n];
        Element Zero_element = Zr.newZeroElement().getImmutable();
        for(int i = 0; i < n; i++){
            ID[i] = Zr.newRandomElement().getImmutable(); // x_i is random element in Z
            Element temp = Zr.newOneElement().set(i); //0,1,2,...,99
            for(int j = 0; j < n; j++){
                Hi[j] = a[j].mul(ID[i].powZn(temp));
                sk[j] = Zero_element.add(Hi[j]);
            }
        }

        System.out.println("getSK success!");
        for(int i = 0; i < n; i++){
            System.out.println("SK_" + i + " = " + sk[i]);
        }

//------*********Encrypt 算法***********---------
        //Encrypt function
        Element M = G1.newRandomElement().getImmutable();// the message M
        Element s = Zr.newRandomElement().getImmutable();// secret s, used to encrypt the message M
        Element alpha = Zr.newRandomElement().getImmutable();

        Element C1 = g.powZn(alpha);  //C_1 = g^{alpha}
        Element C0 = g.powZn(alpha.mul(s)).mul(M);  //C_0 = M·g^{alpha s}
        int u = 4;
        int v = 3;

        Element[] S = new Element[u]; //S is a subset of {ID_i}, S= {ID_0, ID_1, ..., ID_48}, There are 49 users in S.
        for(int i = 0; i <= v; i++){
            S[i] = ID[i];
        }
        Element One_element = G1.newOneElement().getImmutable();
        Element[] T = new Element[u];//compute T_i
        for(int i = 0; i <= v; i++){
            for(int j = 0; j < m; j++){
                Element temp_1 = Zr.newOneElement().set(j); //0,1,2,...,99
                T[i] = One_element.mul(PK[j].powZn(S[i].powZn(temp_1)));
            }
        }
        Element[] T_1 = new Element[u+1];//T_1 = {g^s, T_0, T_1, T_2, ... , T_48}
        T_1[0] = g.powZn(s);
        for(int i = 1; i<= u; i++){
            T_1[i] = T[i-1];
        }
        Element[] S_1 = new Element[u+1];//S_1 = {0, ID_0, ID_1, ID_2, ... , ID_48}, there are 50 elements in S_1. It is used to compute Lagrange g^f(Tau)
        S_1[0] = Zero_element;
        for(int i = 1; i<= u; i++){
            S_1[i] = S[i-1];
        }
        Element[] Tau = new Element[u];//randomly choose Tau_i
        for(int i = 0; i < u; i++){
            for(int j = 0; j < m; j++){
                Tau[i] = Zr.newRandomElement().getImmutable();// random number Tau_i
            }
        }

        Element One_Z = Zr.newOneElement().getImmutable();

        Element[] C2 = new Element[u];
        for(int k = 0; k < u; k++) {
            Element[] L_Tau_1 = new Element[u+1];// Lagrange coefficient in SEM, L_i(Tau_0)
            for (int i = 0; i <= u; i++) {
                for (int j = 0; j <= u; j++) {
                    if (j != i) {
                        L_Tau_1[i] = One_Z.mul((Tau[k].sub(S_1[j])).div(S_1[i].sub(S_1[j])));
                        //System.out.println("L_"+i+"(Tau_"+l+") = " + L[i]);
                    }
                }
            }
            Element Z_C2_1 = One_element;//compute ciphertext C2_0
            for (int i = 0; i <= u; i++) {
                Z_C2_1 = One_element.mul(T_1[i].powZn(L_Tau_1[i]));
            }
            C2[k] = Z_C2_1.powZn(alpha);
            //System.out.println("C2_" + k + " = " + C2[k]);
        }

        System.out.println("Encrypt success!");
        System.out.println("C_0 = " + C0);
        System.out.println("C_1 = " +C1);
        for (int i = 0; i < u; i++) {
            System.out.println("C_"+i+"1 = " + Tau[i]);
            System.out.println("C_"+i+"2 = " + C2[i]);
        }


//------*********Decrypt 算法***********---------
        //Decrypt function

        Element[] Tau_1 = new Element[u+1];
        Tau_1[0] = ID[0];
        for(int i = 1; i <= u; i++){
            Tau_1[i] = Tau[i-1];
        }
        Element[] L_Tau_SEM = new Element[u+1];// Lagrange coefficient in SEM, L_i(Tau_0)
        for (int i = 0; i <= u; i++) {
            for (int j = 0; j <= u; j++) {
                if (j != i) {
                    L_Tau_SEM[i] = One_Z.mul((Zero_element.sub(Tau_1[j])).div(Tau_1[i].sub(Tau_1[j])));
                }
            }
        }

        Element C2_new = One_element;//compute ciphertext C2_0
        for (int i = 1; i < u; i++) {
            C2_new = C2_new.mul(C2[i].powZn(L_Tau_SEM[i]));
        }
        Element M_1;
        M_1 = C0.div(C1.powZn(Tau_1[0].mul(L_Tau_SEM[0])).mul(C2_new));

        System.out.println("Decrypt success!");
        System.out.println("Message = " + M_1);
    }

}


