package team;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;



public class DBQuery {


	Connection con = null;

	PreparedStatement stmt_insert = null;

	public DBQuery() {

	}

	public boolean Run() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/teamproject?serverTimezone=UTC&useSSL=false", "root", "1234");
			con.setAutoCommit(false);
			System.out.println("db 연동  완료");
			return true;
		} catch (Exception e) {
			System.out.println("연동실패" + e.getMessage());
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean Insert(int id, String name) {
		return false;
	}

	//User Query--------------------------------------------------------------------------------------------------
	public boolean MakeUser(String id, int balance) {
		// TODO Auto-generated method stub
		try {
			String Insert = "insert into user(user_id, balance) values(?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setString(1, id);
			sment.setInt(2, balance);
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

	public boolean PayCharge(String id, int money) {
		try {
			String Input= "update user set balance = balance - ? where user_id = ? and balance>=?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, money);
			sment.setString(2, id);
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

	// 예약 추가와 돈 인출이 동시에 일어남
	public boolean Reservation(String id, int money, int srt_number, int seat_number, String seat_status) {
		try {
			// 구매하려는 회원의 잔액이 충분하지 않으면 구매 실패
			String Input= "update user set balance = balance - ? where user_id = ? and balance>=?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, money);
			sment.setString(2, id);
			sment.setInt(3, money);
			int i = sment.executeUpdate();
			sment.close();
			if(i <= 0) {
				System.out.println("잔액이 부족합니다.");
				return false;
			}
			// 이용하려는 좌석이 구매가 불가능한 상태이면 구매 실패
			String sql = "insert Reservation(user_id, srt_number, seat_number, time) values(?, ?, ?, now());";
			PreparedStatement sment2 = con.prepareStatement(sql);
			sment2.setString(1, id);
			sment2.setInt(2, srt_number);
			sment2.setInt(3, seat_number);
			i = sment2.executeUpdate();
			sment2.close();
			if (i > 0) {

				sql = "update srt set seat_status = ? where srt_number = ?;";
				PreparedStatement sment3 = con.prepareStatement(sql);
				sment3.setString(1, seat_status);
				sment3.setInt(2, srt_number);

				i=sment3.executeUpdate();

				if(i > 0) {
					con.commit();
					return true;
				}

			}
			con.rollback();
			return false;

		}catch(Exception e) {		
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {}
			return false;
		}
	}

	// 예약 취소 시 예약테이블에서 삭제 + 돈 환불이 동시에 일어남
	public boolean CancleReservation(String id, int money, int r_id, String seatStatus, int srt_number) {
		try {

			String sql = "delete from reservation where r_id = ? and user_id = ?";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setInt(1, r_id);
			sment.setString(2, id);
			int i = sment.executeUpdate();
			sment.close();
			if (i<= 0) {
				return false;
			}

			String Input= "update user set balance = balance + ? where user_id = ?;";
			PreparedStatement sment2= con.prepareStatement(Input);
			sment2.setInt(1, money);
			sment2.setString(2, id);
			i = sment2.executeUpdate();
			sment2.close();
			if(i > 0)
			{
				sql = "update srt set seat_status = ? where srt_number = ?;";
				PreparedStatement sment3 = con.prepareStatement(sql);
				sment3.setString(1, seatStatus);
				sment3.setInt(2, srt_number);

				i=sment3.executeUpdate();

				if(i > 0) {
					con.commit();
					return true;
				}
			}
			con.rollback();
			return false;
		}
		catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {}
			return false;
		}
	}

	public boolean refundCharge(String id, int money) {
		try {
			String Input= "update user set balance = balance + ? where user_id = ?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, money);
			sment.setString(2, id);
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

	public User SelectUser(String id) {
		try {
			String Input= "select * from user where user_id = ?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setString(1, id);
			ResultSet rs = sment.executeQuery();
			rs.next();
			String user_id = rs.getString(1);
			int balance = rs.getInt(2);
			User us = new User(user_id,balance);
			sment.close();
			return us;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public ArrayList<User> SelectAllUser() {
		try {
			String Input= "select * from user;";
			PreparedStatement sment = con.prepareStatement(Input);
			ResultSet rs = sment.executeQuery();

			ArrayList<User> uslist = new ArrayList<User>();

			while(rs.next())
			{
				String user_id = rs.getString(1);
				int balance = rs.getInt(2);
				User us = new User(user_id,balance);

				uslist.add(us);
			}
			sment.close();
			return uslist;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public boolean DeleteUser(String id) {
		try {
			String Delete = "delete from user where user_id = ?;";
			PreparedStatement sment = con.prepareStatement(Delete);
			sment.setString(1,id);
			//---------------------

			int i=sment.executeUpdate();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			sment.close();
			return false;
		}
		catch(Exception e) {

			return false;
		}
	}

	//SRT Query--------------------------------------------------------------------------------------------------
	public boolean MakeSRT(int srt_number, int max_seat, int Charge) {
		try {
			String Insert = "insert srt(srt_number, max_seat, seat_status,charge) values (?,?,?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setInt(1, srt_number);
			sment.setInt(2, max_seat);

			String repeated = new String(new char[max_seat]).replace("\0", "0");
			sment.setString(3, repeated);
			sment.setInt(4,Charge);
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
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean SelectSrtSeat(int srt_number, int seat_number) {
		try {
			String sql = "select seat_status,max_seat from srt where srt_number = ?;";
			PreparedStatement sment = con.prepareStatement(sql);	
			sment.setInt(1, srt_number);
			//--------------------------------------------------------
			ResultSet rs = sment.executeQuery();
			rs.next();
			String seat_state = rs.getString(1);
			int max_seat = rs.getInt(2);
			sment.close();

			if(seat_state.charAt(seat_number) == '0') {
				String msg = seat_state.substring(0, seat_number-1) +"1" + seat_state.substring(seat_number,max_seat-1);
				String Input= "update srt set seat_status = ? where srt_number = ?;";
				sment = con.prepareStatement(Input);
				sment.setString(1, msg);
				sment.setInt(2, srt_number);
				//------------------------------------
				sment.close();
				int i = sment.executeUpdate();
				if(i > 0)
				{
					con.commit();
					return true;
				}
				else {
					con.rollback();
					return false;
				}
			}
			else {
				throw new Exception("이미 예약된 좌석입니다.");
			}

			//					PreparedStatement sment = con.prepareStatement(Input);
			//					sment.setInt(1, money);
			//					sment.setString(2, id);
			//					sment.setInt(3, money);
			//					//---------------------
			//					sment.close();
			//				
			//					return false;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public SRT SelectSRT(int srt_number) {
		try {
			String Input= "select * from SRT where srt_number = ?;";
			PreparedStatement sment = con.prepareStatement(Input);
			sment.setInt(1, srt_number);
			ResultSet rs = sment.executeQuery();
			rs.next();
			int srt_num = rs.getInt(1);
			int max_seat = rs.getInt(2);
			String seat_sate = rs.getString(3);
			int charge = rs.getInt(4);
			SRT srt = new SRT(srt_num,max_seat,seat_sate,charge);
			sment.close();
			return srt;
		}
		catch(Exception e)
		{
			return null;
		}
	}


	public ArrayList<SRT> SelectAllSRT() {
		try {
			String sql= "select * from srt;";
			PreparedStatement sment = con.prepareStatement(sql);
			ResultSet rs = sment.executeQuery();

			ArrayList<SRT> SRTList = new ArrayList<SRT>();

			while(rs.next())
			{
				int num = rs.getInt(1);
				int cnt = rs.getInt(2);
				String status = rs.getString(3);
				int charge = rs.getInt(4);

				SRTList.add(new SRT(num, cnt, status, charge));
			}
			sment.close();
			return SRTList;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public boolean DeleteSRT(String id) {
		//보류 추후 생각
		//				try {
		//					String Delete = "delete from user where user_id = ?;";
		//					PreparedStatement sment = con.prepareStatement(Delete);
		//					sment.setString(1,id);
		//					//---------------------
		//					
		//					int i=sment.executeUpdate();
		//					if(i > 0)
		//					{
		//						con.commit();
		//						return true;
		//					}
		//					sment.close();
		//					return false;
		//				}
		//				catch(Exception e) {

		return false;
		//				}
	}

	public boolean UpdateSRT(int srtNumber, String seatStatus) {
		try {
			String sql = "update srt set seat_status = ? from srt  where srt_number = ?;";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setString(1, seatStatus);
			sment.setInt(2, srtNumber);

			int i=sment.executeUpdate();
			if(i > 0)
			{
				con.commit();
				return true;
			}
			sment.close();
			return false;
		}
		catch(Exception e) {
			return false;
		}
	}

	public boolean ReservationSeat(String id, int srt_number, int seat_number) {
		try {
			String sql = "insert Reservation(user_id, srt_number, seat_number, time) values(?, ?, ?, now())";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setString(1, id);
			sment.setInt(2, srt_number);
			sment.setInt(3, seat_number);
			// ---------------------
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

	public boolean ChangeSeatRId(int r_id, String user_id, int srt_number, int seat_number) {
		try {
			String sql = "update Reservation set srt_number = ?, seat_number = ?, time = now() where r_id = ? and user_id = ?;";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setInt(1, srt_number);
			sment.setInt(2, seat_number);
			sment.setInt(3, r_id);
			sment.setString(4, user_id);
			// ---------------------
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

	public boolean DeleteReservationRId(String user_id, int r_id) {
		try {
			String sql = "delete from reservation where r_id = ? and user_id = ?";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setInt(1, r_id);
			sment.setString(2, user_id);
			// ---------------------
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


	public ArrayList<Reservation> SelectReservationUserId(String id) {
		try {
			String sql = "select r_id, user_id, srt_number, seat_number, time from reservation where user_id = ?;";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setString(1, id);
			// --------------------------------------------------------
			ArrayList<Reservation> reservationList = new ArrayList();
			ResultSet rs = sment.executeQuery();

			while (rs.next()) {
				int rId = rs.getInt(1);
				String UserId = rs.getString(2);
				int strNum = rs.getInt(3);
				int seatNum = rs.getInt(4);
				Timestamp time = rs.getTimestamp(5);
				Reservation rev = new Reservation(rId, UserId, strNum, seatNum, time);
				reservationList.add(rev);
			}
			sment.close();

			return reservationList;
		} catch (Exception ex) {
			return null;
		}
	}
	public Reservation SelectReservationRId(int id) {
		try {
			String sql = "select r_id, user_id, srt_number, seat_number, time  from reservation where r_id = ?;";
			PreparedStatement sment = con.prepareStatement(sql);
			sment.setInt(1, id);
			// --------------------------------------------------------
			ResultSet rs = sment.executeQuery();

			rs.next();
			int rId = rs.getInt(1);
			String UserId = rs.getString(2);
			int strNum = rs.getInt(3);
			int seatNum = rs.getInt(4);
			Timestamp time = rs.getTimestamp(5);
			Reservation rev = new Reservation(rId, UserId, strNum, seatNum, time);
			sment.close();

			return rev;
		} catch (Exception ex) {
			return null;
		}
	}

	public ArrayList<Reservation> SelectReservationAll() {
		try {
			String sql = "select * from reservation;";
			PreparedStatement sment = con.prepareStatement(sql);
			// --------------------------------------------------------
			ArrayList<Reservation> reservationList = new ArrayList();
			ResultSet rs = sment.executeQuery();

			while (rs.next()) {
				int rId = rs.getInt(1);
				String UserId = rs.getString(2);
				int srtNum = rs.getInt(3);
				int seatNum = rs.getInt(4);
				Timestamp time = rs.getTimestamp(5);
				Reservation rev = new Reservation(rId, UserId, srtNum, seatNum, time);
				reservationList.add(rev);
			}
			sment.close();

			return reservationList;
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return null;
		}
	}
}
