package bit.Server;

import java.util.ArrayList;

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
 * 	"MakeAccount_ck@111#S" "MakeAccount_ack@111#F"
 * 	"SelectAccount_ack@S#111#ccm#1000"	"SelectAccount_ack@F#111"
 */

public class Packet {

	public static String MakeAccount_ack(int id,boolean b) {
		String msg = "";
		msg += "MakeAccount"+"@";
		msg += id+"#";
		if(b)
			msg += "S";
		else
			msg += "F";
		return msg;
	}
	
	public static String SelectAccount_ack(int id,String name, int balance,String date,String time, boolean b) {
		String msg = "";
		msg += "SelectAccount_ack"+"@";
		if(b)
			msg += "S"+"#";
		else
			msg += "F"+"#";
		msg += id+"#";
		msg += name + "#";
		msg += balance+"#";
		msg += date +"#";
		msg += time;
		
		return msg;
	}

	public static String InputAccount_ack(int id,int balance, boolean b) {
		// TODO Auto-generated method stub	
		String msg = "";
		
		msg += "InputAccount"+"@";
		if(b)
			msg += "S";
		else
			msg += "F";
		msg += id+"#";
		msg += balance;
		return msg;
	}
	
	public static String OutputAccount_ack(int id,int balance, boolean b) {
		// TODO Auto-generated method stub	
		String msg = "";
		
		msg += "InputAccount"+"@";
		if(b)
			msg += "S"+"#";
		else
			msg += "F"+"#";
		msg += id+"#";
		msg += balance;
		return msg;
	}
	
	public static String DeleteAccount_ack(int id,boolean b) {
		String msg = "";
		msg += "MakeAccount"+"@";
		if(b)
			msg += "S" + "#";
		else
			msg += "F" + "#";;
		msg += id;
		return msg;
	}
	
	public static String SelectAllAcount_ack(ArrayList<Account> acclist,boolean b) {
		String msg = "";
		msg += "SelectAllAccount_ack"+"@";
		if(b)
			msg += "S"+"#";
		else
			msg += "F"+"#";
		for(Account ac : acclist) {
			msg += ac.getAccid() + "%";
			msg += ac.getName() + "%";
			msg += ac.getBalance() + "%";
			msg += ac.GetDate() + "%";
			msg += ac.GetTime();
		}
		return msg;
	}
}
