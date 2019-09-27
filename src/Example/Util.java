package Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.util.Scanner;

import CeShi.readFile;

public class Util {
	
	public static void excute(Object object,String[] args) throws Exception{
        
		 Class c = object.getClass();
		 //���������ж�ִ���ĸ�����
		 String[] params = new String[args.length-1];
		 for(int i=1;i<args.length;i++){
			 params[i-1] = args[i];
		 }
		 Method[] methods = c.getDeclaredMethods();
		if("_init".equals(args[0])){
			try {
				Contract contract = new Contract(params);
				System.out.println("��ʼ��");
				write(contract);
				System.out.println("��һ��ִ��ʱ��д����̵�����");
				readFile.outPut();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			for(Method method:methods){
				if(args[0].equals(method.getName()))
				{
					 try {
						object = read();
						System.out.println(object);
						method.invoke(object,(Object)params);
						write(object);
						System.out.println("���ǳ�ʼִ��ʱ��д����̵�����");
						readFile.outPut();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	 //���л�
	 public static void write(Object object){
	 	try {
	 		File myFile = new File("state.txt");
	 		ObjectOutputStream oos = new ObjectOutputStream(
	 				new FileOutputStream(myFile));
	 		oos.writeObject(object);
	 		System.out.println("���л����");
	 		oos.close();
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	} 
	 }
	 
	//�����л�
	 public static Contract read(){
		 try {
		 	ObjectInputStream ois = new ObjectInputStream(
		 			new FileInputStream(new File("state.txt")));
		 	System.out.println("�����л����");
		 	Contract contract = (Contract) ois.readObject();
		 	ois.close();
		 	return contract;
		 } catch (Exception e) {
		 	// TODO Auto-generated catch block
		 	e.printStackTrace();
		 	return null;
		 }
	}
}