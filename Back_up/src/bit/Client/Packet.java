package bit.Client;

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
	
	public static String MakeAccount(int id, String name, int balance) {
		String msg = "";
		msg += "MakeAccount" +"@";
		msg += id +"#";
		msg += name +"#";
		msg += balance;
		
		return msg;
	}
}
