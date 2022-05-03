package custom;

import contractLib.Party;
import shellChain.ShellChain;
import shellChain.ShellChainContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contract extends  ShellChainContract {

    private static final long serialVersionUID = 6806795909133642266L;
    private String name;
    private int times;
    private Map<String,Integer> balances = new HashMap<String, Integer>();



//
//    private String a;
//    private int b;

    //    public String pubkey;
//    public String signature;
    public static void askSignParties (){
        ShellChain.party.regist();
    }

    public Contract() {
        super();
        this.name = ShellChain.getFromAddress();
        this.times = 0;
        balances.put(name,0);
    }

    //谁创建合约，谁就拥有货币的使用权
    public String mint(String receiver,int amout){
        if(name.equals(receiver)){
            int money = balances.get(name);
            balances.put(name,money+amout);
            for(String key:balances.keySet()){
                System.out.println("mint===="+"Key="+key+"\tvalue="+balances.get(key));
            }
            return "success create coin " + balances.get(name);
        }
        else{
            return "access denied with name " + ShellChain.getFromAddress();
        }
    }
    //可以被任何人（拥有一定数量的代币）调用，发送一些币给其他人
    public String send(String receiver,int amount){
        String sender = ShellChain.getFromAddress();
        if(!balances.containsKey(sender) || balances.get(sender)<amount){
            return "Insufficient Balance";
        }
        else {
            balances.put(sender,balances.get(sender)-amount) ;
            if(!balances.containsKey(receiver)){
                balances.put(receiver,amount) ;
            }
            else {
                balances.put(receiver,balances.get(receiver)+amount) ;
            }

            for(String key:balances.keySet()){
                System.out.println("send====="+"Key="+key+"\tvalue="+balances.get(key));
            }
            return "Transfer success";
        }
    }
    //获取余额
    public String getBalance (String address){
        int balance = 0;
        if(balances.get(address) == null){
            return "success balance "+balance;
        }
        else {
            return "success balance "+balances.get(address).intValue();
        }
    }
    public String addT(int T) {
        if(name.equals(ShellChain.getFromAddress())) {
            times += T;
            return "sucess " + times;
        }
        else
            return "access denied with name " + ShellChain.getFromAddress();
    }


    //条款
    public void Bid(){

    }

}
