package example;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Start {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Start() {
		Initdata();
	}
	
	private void Initdata() {
		accounts.add(new Account(10,"홍길동"));
		accounts.add(new Account(20,"홍길동"));
		accounts.add(new Account(30,"홍길동"));
		accounts.add(new Account(40,"홍길동"));
		accounts.add(new Account(50,"홍길동"));
		
	}
	
	public byte[] example() {
		String str = "";
		for(Account ac : accounts) {
			str += ac.getAccid() + "#";
			str += ac.getName() + "#";
			str += ac.getBalance() + "#";
			str += ac.GetDate() + "#";
			str += ac.GetTime() + "@";
		}
		System.out.println(str);
		
		return str.getBytes();
	}
	
	public void example2(byte[] buffer) {
		try {

			String str = new String(buffer);
			System.out.println(str);
			
			System.out.println("@를 이용한 문자열 자르기");
			String[] filter = str.split("@");
			for(String s : filter) {
				System.out.println(s);
				String[] filter2 = s.split("#");
				int accid = Integer.parseInt(filter2[0]);
				String naeme = filter2[1];
				int balance = Integer.parseInt(filter2[2]);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
				Date date = formatter.parse(filter2[3] + " "+ filter2[4]);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void example2_Print() {
		for(Account acc : accounts)
			acc.Print();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Start s =new Start();
		byte[] bstr = s.example();
		System.out.println(bstr);
		s.example2(bstr);
		s.example2_Print();
	}

}
