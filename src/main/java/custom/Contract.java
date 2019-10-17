package custom;

import java.io.Serializable;

public class Contract implements Serializable{

    private String name;
    private int times;

    public Contract(String name) {
        super();
        //给参数赋值
        this.name = name;
        this.times = 0;
    }

    //之后执行时直接用上次之序列化返回的值进行反序列化，执行完后在此序列化
    public String addOne(String name){
        if(name.equals(this.name)) {
            times += 1;
            return "sucess " + times;
        }
        else
            return "access denied with name " + name;
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
