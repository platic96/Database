package bit.Server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class AccountDB {
	Connection con = null;
	
	//미리 쿼리문의 포맷을 미리 설정...
	PreparedStatement stmt_insert = null;
	
	public AccountDB(){

	}
	
	//DB 연결(connection 객체 생성)
	public boolean Run() {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","20132991");
			con.setAutoCommit(false);
			System.out.println("데이터베이스 연결성공");
			return true;
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류" + e.getMessage());
			return false;
		}
	}
		
	//기능
	public boolean Insert(int id, String name) {		
		return false;
	}

	public boolean MakeAccount(int id, String name, int balance) {
		// TODO Auto-generated method stub
		try {
			String Insert = "insert into account(accid,name,balance) values(?,?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setInt(1, id);
			sment.setString(2, name);
			sment.setInt(3, balance);
			int i= sment.executeUpdate();
			sment.close();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			return false;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public Account SelectAccount(int accnum) {
		try {
			Account acc= null;
			String select = "select * from account where accid = ?;";
			PreparedStatement sment = con.prepareStatement(select);
			sment.setInt(1, accnum);
			//----------------명령객체 처리 끝
			
			ResultSet rs = sment.executeQuery();
			
			rs.next();
			int accum = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Timestamp nDate = rs.getTimestamp(4);
			sment.close();
			acc.setData(accum, name, balance, nDate);
			return acc;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public boolean InputAccount(int number, int money) {
		// TODO Auto-generated method stub
		try {
			String Input= "update account set balance = balance + ? where accid = ?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, money);
			sment.setInt(2, number);
			//---------------------
			sment.close();
			int i = sment.executeUpdate();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			return false;
		}
		catch(Exception e) {

			return false;
		}
	}
	
	public boolean OutputAccount(int number, int money) {
		// TODO Auto-generated method stub
		try {
			
			String Input= "update account set balance = balance - ? where accid = ? and balance <= ?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, money);
			sment.setInt(2, number);
			sment.setInt(3, money);
			//---------------------
			sment.close();
			int i = sment.executeUpdate();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			return false;
		}
		catch(Exception e) {

			return false;
		}
	}
	
	public boolean DeleteAccount(int number) {
		try {
			String Delete = "delete from account where accid = ?;";
			PreparedStatement sment = con.prepareStatement(Delete);
			sment.setInt(1,number);
			//---------------------
			sment.close();
			int i=sment.executeUpdate();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			return false;
		}
		catch(Exception e) {

			return false;
		}
	}

	public ArrayList<Account> SelectAllAccount() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Account> acclist = new ArrayList<Account>();
			String sql = "select * from account;";
			PreparedStatement sment = con.prepareStatement(sql);
			ResultSet rs = sment.executeQuery();
			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Timestamp ndate = rs.getTimestamp(4);
				acclist.add(new Account(accid,name,balance,ndate));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}











