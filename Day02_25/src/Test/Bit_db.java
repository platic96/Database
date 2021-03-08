package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Bit_db {
	Connection con = null;

	// 미리 쿼리문의 포맷을 미리 설정...
	PreparedStatement stmt_insert = null;

	public Bit_db() {

	}

	// DB 연결(connection 객체 생성)
	public boolean Run() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root",
					"20132991");
			con.setAutoCommit(false);
			System.out.println("데이터베이스 연결성공");
			return true;
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류" + e.getMessage());
			return false;
		}
	}

	public boolean MakeMemeber(String id, String name) {
		// TODO Auto-generated method stub
		try {
			String Insert = "insert into bit_member(memberid ,name,level) values(?,?,\"normal\");";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setString(1, id);
			sment.setString(2, name);
			int i = sment.executeUpdate();
			sment.close();
			if (i > 0) {
				con.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<Member> PrintAllMember() {
		try {
			String select = "select * from bit_member;";
			PreparedStatement sment = con.prepareStatement(select);
			// ----------------명령객체 처리 끝

			ResultSet rs = sment.executeQuery();
			ArrayList<Member> mem = new ArrayList<Member>();

			while (rs.next()) {
				String Id = rs.getString(1);
				String name = rs.getString(2);
				String level = rs.getString(3);
				mem.add(new Member(Id, name, level));
			}
			sment.close();
			return mem;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean MakeDrink(String name, int price) {
		// TODO Auto-generated method stub
		try {
			String Insert = "insert into bit_drink(name ,price) values(?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setString(1, name);
			sment.setInt(2, price);
			int i = sment.executeUpdate();
			sment.close();
			if (i > 0) {
				con.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<Drink> PrintAllDrink() {
		try {
			String select = "select * from bit_drink;";
			PreparedStatement sment = con.prepareStatement(select);
			// ----------------명령객체 처리 끝

			ResultSet rs = sment.executeQuery();
			ArrayList<Drink> dr = new ArrayList<Drink>();

			while (rs.next()) {
				int Id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int count = rs.getInt(4);
				dr.add(new Drink(Id, name, price, count));
			}
			sment.close();
			return dr;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<MemberLevel> PrintAllLevel() {
		try {
			String select = "select * from bit_memberlevel;";
			PreparedStatement sment = con.prepareStatement(select);
			// ----------------명령객체 처리 끝

			ResultSet rs = sment.executeQuery();
			ArrayList<MemberLevel> ml = new ArrayList<MemberLevel>();

			while (rs.next()) {
				String levelname = rs.getString(1);
				int low = rs.getInt(2);
				int high = rs.getInt(3);
				ml.add(new MemberLevel(levelname, low, high));
			}
			sment.close();
			return ml;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<BIT_BuyDrink> PrintAllBuyList() {
		try {
			String select = "select * from bit_Buydrink;";
			PreparedStatement sment = con.prepareStatement(select);
			// ----------------명령객체 처리 끝

			ResultSet rs = sment.executeQuery();
			ArrayList<BIT_BuyDrink> ml = new ArrayList<BIT_BuyDrink>();

			while (rs.next()) {
				int buydrinkid =rs.getInt(1);
				String memberid = rs.getString(2);
				int drinkid = rs.getInt(3);
				int count = rs.getInt(4);
				int totalmoney = rs.getInt(5);
				ml.add(new BIT_BuyDrink(buydrinkid, memberid, drinkid,count,totalmoney));
			}
			sment.close();
			return ml;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean MakeBuyList(String memid, int drinkid, int count) {
		// TODO Auto-generated method stub
		try{
			//--------------------15번 문제
			String select = "select price from bit_drink where drinkid = ?;";
			PreparedStatement sment = con.prepareStatement(select);
			sment.setInt(1, drinkid);
			//-------------------------------명령 객체 끝
			ResultSet rs = sment.executeQuery();
			if(rs.next())
			{	int price = rs.getInt(1);
				int totalmoney = price * count;
				
				select = "insert into bit_buydrink(memberid,drinkid,count,totalmoney) values(?,?,?,?);";
				PreparedStatement sment2 = con.prepareStatement(select);
				sment2.setString(1, memid);
				sment2.setInt(2, drinkid);
				sment2.setInt(3, count);
				sment2.setInt(4, totalmoney);
				sment2.executeUpdate();

				sment.close();
				CountUp(drinkid,count) ;
				return true;
				//-----------------------------------------------------
			}
			return false;
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}

		// ----------------명령객체 처리 끝
	}
//
//	private void LevelUp(String memid) {
//		// TODO Auto-generated method stub
//		String select = "select level"
//		
//	}

	private boolean CountUp(int drinkid,int count) {
		try {
			String update = "update bit_drink set count = count + ? where drinkid = ?;";
			PreparedStatement sment = con.prepareStatement(update);
			sment.setInt(1, count);
			sment.setInt(2, drinkid);
			//------------------------------------
			int i=sment.executeUpdate();
			if (i > 0) {
				con.commit();
				return true;
			}
			return false;
			
		}
		catch(Exception e) {
			System.out.println("카운트 에러"+e.getLocalizedMessage());
			return false;
		}
	}

	public Drink ManySellDrink() {
		try {
			String select = "select Max(count) from bit_drink;";
			PreparedStatement sment = con.prepareStatement(select);
			// ----------------명령객체 처리 끝
			
			ResultSet rs = sment.executeQuery();

			rs.next();
			int Id = rs.getInt(1);
			String name = rs.getString(2);
			int price = rs.getInt(3);
			int count = rs.getInt(4);
			sment.close();
			return new Drink(Id,name,price,count);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public Member SelectMember(String memid) {
		try {
			String select = "select * from bit_member where memberid = ?;";
			PreparedStatement sment = con.prepareStatement(select);
			sment.setString(1, memid);
			// ----------------명령객체 처리 끝

			ResultSet rs = sment.executeQuery();

			rs.next();
			String Id = rs.getString(1);
			String name = rs.getString(2);
			String level = rs.getString(3);
			sment.close();
			return new Member(Id, name, level);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean SelectMember_drink(String memid) {
		try {
			String select = "select l.count from bit_buydrink l,bit_member m where m.memberid = ?;";
			PreparedStatement sment = con.prepareStatement(select);
			sment.setString(1, memid);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
