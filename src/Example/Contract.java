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
		//��������ֵ
	    name = params[0];
	    data = params[1];
		System.out.println("ִ��init����");
		System.out.println("name="+name+"    data="+data);
	}
    
	
	@Override
	public String toString() {
		return "Contract [name=" + name + ", data=" + data + "]";
	}
   
    //֮��ִ��ʱֱ�����ϴ�֮���л����ص�ֵ���з����л���ִ������ڴ����л�
    public void again(String[] params){
	   System.out.println("ִ��again����");
	   System.out.print("����Ϊ��");
	   for(String param:params){
		   System.out.print(param+"  ");
	   }
	   System.out.println("heeehhegdj"+this.toString());
	   System.out.println("");
	   
	}
   
    public void funthree(String[] params){
	   System.out.println("ִ��fun3����");
	   System.out.print("����Ϊ��");
	   for(String param:params){
		   System.out.print(param+"  ");
	   }
	   System.out.println("");
	}
}
