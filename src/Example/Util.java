package Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.util.Scanner;

//import CeShi.readFile;

public class Util {
	
	public static void excute(Object object,String[] args) throws Exception{
        
		 Class c = object.getClass();
		 //根据输入判断执行哪个函数
		 String[] params = new String[args.length-2];
		 for(int i=2;i<args.length;i++){
			 params[i-2] = args[i];
		 }
		 Method[] methods = c.getDeclaredMethods();
		if("_init".equals(args[0])){
			try {
				Contract contract = new Contract(params);
				System.out.println(contract);
				System.out.println("\r\r\r\r\r");//分隔符
			//	write(contract);
				System.out.println(args[0]+" function");
				System.out.println("When the code was init excuted, the state of code");
				System.out.println(contract);
			//	readFile.outPut();
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
					//	object = read();
					//	System.out.println(object);
					    Contract object1 = new Contract(params);
						method.invoke(object1,(Object)params);
						System.out.println(object1);
						 System.out.println("\r\r\r\r\r");
						//write(object);
						 System.out.println(args[0]+" function");
						System.out.println("When the code was excuted, the state of code");
						 System.out.println(object1);
						//readFile.outPut();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	 //序列化
	 public static void write(Object object){
	 	try {
	 		File myFile = new File("state.txt");
	 		ObjectOutputStream oos = new ObjectOutputStream(
	 				new FileOutputStream(myFile));
	 		oos.writeObject(object);
	 		System.out.println("序列化完成");
	 		oos.close();
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	} 
	 }
	 
	//反序列化
	 public static Contract read(){
		 try {
		 	ObjectInputStream ois = new ObjectInputStream(
		 			new FileInputStream(new File("state.txt")));
		 	System.out.println("反序列化完成");
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
