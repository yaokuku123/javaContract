package contractLib;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import java.io.Serializable;
import java.sql.Struct;
import java.util.ArrayList;

public class Party implements Serializable {


    public  ArrayList<PartiesSigns> listSign = new ArrayList<PartiesSigns>();

    public  ArrayList<PartiesSigns> getListSign() {
        return listSign;
    }

    public  String regist (String...params){
        int length = params.length;

        for (int i = 0; i < length; i++) {
            PartiesSigns ps = new PartiesSigns();
            ps.setUserAddress(params[i]);
            listSign.add(ps);
        }
        System.out.println(JSONSerializer.toJSON(listSign).toString());
       return JSONSerializer.toJSON(listSign).toString();
    }

    public  Boolean toSign (String myAddress,String signature ){
        Boolean flag = false;
        for (int i = 0; i < listSign.size(); i++) {
            String signAddress = listSign.get(i).getUserAddress();
            if (myAddress.equals(signAddress)){
                listSign.get(i).setSignature(signature);
                flag = true;
            }
        }
        return flag;
    }

    public  Boolean check (){
        for (int i = 0; i < listSign.size(); i++) {
            String sign = listSign.get(i).getSignature();
            if (sign == null){
               return false;
            }
        }
        return true;
    }

/*    public static void main(String[] args) {
//        regist("5r4r","rete");
//        toSign("5r4r","hfls");
//        JSON json = JSONSerializer.toJSON(listSign);
//        Boolean check = check();
//        System.out.println(check);
//        toSign("rete","hdrfrgtr");
//        Boolean check1 = check();
//        System.out.println(check1);

    }*/

}

