import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class AccountDB {
	Connection con;
	Statement stmt;
	
	public void AccoutDB() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로딩 성공");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","20132991");
			System.out.println("데이터베이스 연결성공");
			stmt = con.createStatement();
			System.out.println("연결객체 획득 성공");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//메서드
	public boolean Insert(int id, String name) {
		try {
			String quere = String.format("insert into Account(accid, name) values(%d,%s);", id,name);
			ExcuteUpdate(quere);
			return true;
		}
		catch(Exception e) {
			System.out.println("오류 : " + e.getMessage());
			return false;
		}
	}
	
	public boolean Insert(int id, String name, int money) {
		try {
			String quere = String.format("insert into Account(accid, name,balance) values(%d,%s,%d);", id,name,money);
			ExcuteUpdate(quere);
			return true;
		}
		catch(Exception e) {
			System.out.println("오류 : " + e.getMessage());
			return false;
		}
	}
	
	public boolean Select(int id, Account acc, ArrayList<AccountIO> accio) {
		try{
			String query = String.format("select * from account where acc = %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int accid = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Date ndate = rs.getDate(4);
			acc = new Account(accid,name,balance,ndate);
			
			query = String.format("select * from accountio where acc = %d;", id);
			 rs = stmt.executeQuery(query);
			while (rs.next()) {
				int accnum = rs.getInt(2);
				int input = rs.getInt(3);
				int output = rs.getInt(4);
				balance = rs.getInt(5);
				accio.add(new AccountIO(accnum, input, output, balance));

			}
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean IDCheck(int id) {
		// TODO Auto-generated method stub
		try {
			String query = String.format("select id from Account where accid = %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
		
	}

	private void ExcuteUpdate(String query) throws Exception {
		try {
			int i = stmt.executeUpdate(query); //영향을 미친 row data갯수
			if( i <= 0)
				throw new Exception("변경된 로우데이터가 없음");
		}
		catch(Exception ex) {
			throw new Exception("에러 - " + ex.getMessage());
		}
	}

	public ArrayList<Account> Select() {
		try {
			ArrayList<Account> acc = new ArrayList<Account>();
			String query = String.format("select * from account;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Date ndate = rs.getDate(4);
				acc.add(new Account(accid,name,balance,ndate));
			}
			return acc;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean Update(int id, boolean b, int money) {
		// TODO Auto-generated method stub
		try {String query =null;
			if(b == true)
				query = String.format("update account set balance = balance + %d where accid = %d",money,id);
			else
				query = String.format("update account set balance = balance - %d where accid = %d",money,id);
			ExcuteUpdate(query);
			
//=======================================================================
			
			//AccountIO 계좌에 대한 Update =============================================
			if(b == true)// 입금
				query = String.format("insert into accountio(accnum,input,output,balance) "+
						"values(%d,%d,%d, (select balance from account where accid = %d));",id,money,0,id);
			else {//출금
				query = String.format("insert into accountio(accnum,input,output,balance) "+
						"values(%d,%d,%d, (select balance from account where accid = %d));",id,0,money,id);
			}
			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

}
