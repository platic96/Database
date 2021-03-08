package bit.Server.pro;

/*
 * [client -> server]
 * 	"MakeAccount@111#ccm#1000"
 * 	"SelectAccount@111"
 * 	"InputAccount@111#1000"
 * 	"OutputAccount@111#1000"
 * 	"DeleteAccount@111"
 * 	"SelectAllAccount@"
 * 
 * [server -> client]
 * 	"MakeAccount_ck@111#S"
 * 	"MakeAccount_ack@111#F"
 */


public class Parser {
	
	public static String RecvData(String msg) {
		String[] filter = msg.split("@");
		if(filter[0].equals("MakeAccount")) {
			String[] filter1 = filter[1].split("#");
			int number = Integer.parseInt(filter1[0]);
			String name = filter1[1];
			int balance = Integer.parseInt(filter1[2]);
			return Manager.getInstance().MakeAccount(number, name, balance);
		}
		else if(filter[0].equals("SelectAccount")) {
			int number = Integer.parseInt( filter[1]);
			return Manager.getInstance().SelectAccount(number);
		}else if(filter[0].equals("InputAccount")) {
			String[] filter1 = filter[1].split(msg);
			int number = Integer.parseInt(filter1[0]);
			int money = Integer.parseInt(filter1[1]);
			return Manager.getInstance().InputAccount(number,money);
		}else if(filter[0].equals("OutputAccount")) {
			String[] filter1 = filter[1].split(msg);
			int number = Integer.parseInt(filter1[0]);
			int money = Integer.parseInt(filter1[1]);
			return Manager.getInstance().OutputAccount(number,money);
		}else if(filter[0].equals("DeleteAccount")) {
			int number = Integer.parseInt( filter[1]);
			return Manager.getInstance().DeleteAccount(number);
		}else if(filter[0].equals("SelectAllAccount")) {
			return Manager.getInstance().SelectAllAccount();
		}
		return "";
	}
	
	public static void RecvData_switch(String msg) {
		String[] filter = msg.split("@");
		switch(filter[0]) {
			case "MakeAccount":
				break;
			case "SelectAccount":
				break;
			case "InputAccount":
				break;
			case "OutputAccount":
				break;
			case "DeleteAccount":
				break;
			case "SelectAllAccount":
				break;
				
		}
	}
}
