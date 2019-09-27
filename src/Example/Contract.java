package Example;

import java.io.*;

public class Contract implements Serializable{
    
	private String name = null;
	private String data = null;
	
	public Contract() {
		super();
	}

	public Contract(String[] params) {
		super();
		//给参数赋值
	    name = params[0];
	    data = params[1];
		System.out.println("执行init函数");
		System.out.println("name="+name+"    data="+data);
	}
    
	
	@Override
	public String toString() {
		return "Contract [name=" + name + ", data=" + data + "]";
	}
   
    //之后执行时直接用上次之序列化返回的值进行反序列化，执行完后在此序列化
    public void again(String[] params){
	   System.out.println("执行again函数");
	   System.out.print("参数为：");
	   for(String param:params){
		   System.out.print(param+"  ");
	   }
	   System.out.println("heeehhegdj"+this.toString());
	   System.out.println("");
	   
	}
   
    public void funthree(String[] params){
	   System.out.println("执行fun3函数");
	   System.out.print("参数为：");
	   for(String param:params){
		   System.out.print(param+"  ");
	   }
	   System.out.println("");
	}
}
