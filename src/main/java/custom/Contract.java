package custom;

import shellChain.ShellChainContract;


public class Contract extends  ShellChainContract {

    private String name;
    private int times;

    public Contract(String name) {
        super();
        this.name = name;
        this.times = 0;
    }

    public String addT(String name, int T){
        if(name.equals(this.name)) {
            times += T;
            return "sucess " + times;
        }
        else
            return "access denied with name " + name;
    }
}
