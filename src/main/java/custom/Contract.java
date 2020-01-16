package custom;

import contractLib.Party;
import shellChain.ShellChain;
import shellChain.ShellChainContract;

import java.util.ArrayList;

public class Contract extends  ShellChainContract {
   // private static final long serialVersionUID=-1263038529591619388;
    private String name;
    private int times;


//
//    private String a;
//    private int b;

//    public String pubkey;
//    public String signature;
    public static void askSignParties (){
        ShellChain.party.regist("1TkgibF2AsDEkriRWHJAuJsnXuL4u4UsiqAz2U");
    }

    public Contract() {
        super();
        this.name = ShellChain.getFromAddress();
        this.times = 0;
    }
//
//    public void signature( ) {
//        signature = ShellChain.getSignature();
//        pubkey = ShellChain.getPubkey();
//    }
//

    public String addT(int T) {
        if(name.equals(ShellChain.getFromAddress())) {
            times += T;
            return "sucess " + times;
        }
        else
            return "access denied with name " + ShellChain.getFromAddress();
    }


}
