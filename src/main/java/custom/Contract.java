package custom;

import shellChain.ShellChain;
import shellChain.ShellChainContract;

public class Contract extends  ShellChainContract {

    private String name;
    private int times;

    public Contract() {
        super();
        this.name = ShellChain.getFromAddress();
        this.times = 0;
    }

    public String addT(int T){
        if(name.equals(ShellChain.getFromAddress())) {
            times += T;
            return "sucess " + times;
        }
        else
            return "access denied with name " + ShellChain.getFromAddress();
    }
}
