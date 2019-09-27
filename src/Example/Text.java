package Example;

import java.util.Scanner;

public class Text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//设计java智能合约执行入口
		Contract contract = new Contract();
		try {
			Util.excute(contract,args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
