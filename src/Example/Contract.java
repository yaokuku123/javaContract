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
	public void funtwo(double a,String b){
	   System.out.println("ִ��funtwo����");
	   System.out.print("����Ϊ��");
	   System.out.println(a);
       System.out.println(b);
	}
     
    public void funthree(float a, double b,boolean c){
 	   System.out.println("ִ��funthree����");
 	   System.out.print("����Ϊ��");
       System.out.println(a);
       System.out.println(b);
       System.out.println(c);
 	}
       
    public void funfour(int a,double b){
	   System.out.println("ִ��funfour����");
	   System.out.println("����Ϊ");
	   System.out.println(a);
       System.out.println(b);
	}
}
