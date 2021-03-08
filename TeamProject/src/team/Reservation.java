package team;

import java.sql.Timestamp;
import java.util.Calendar;

public class Reservation {

	int R_id;
	String User_id;
	int SRT_number;
	int seat_number;
	Timestamp datetime;
	
	public Reservation(int r_id, String user_id, int sRT_number, int seat_number, Timestamp datetime) {
		super();
		R_id = r_id;
		User_id = user_id;
		SRT_number = sRT_number;
		this.seat_number = seat_number;
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "Reservation [R_id=" + R_id + ", User_id=" + User_id + ", SRT_number=" + SRT_number + ", seat_number="
				+ seat_number + ", datetime=" + datetime + "]";
	}
	public void Print() {
		System.out.println("Reservation [R_id=" + R_id + ", User_id=" + User_id + ", SRT_number=" + SRT_number + ", seat_number="
				+ seat_number + ", datetime=" + datetime + "]");
	}
	
	// 회원 검색 시 조회될 정보
	public void PrintReservation() {
		System.out.println("[ 예약 번호 :  " + R_id + " , 예약차량-좌석번호 : "+ SRT_number+"-"+ seat_number +" ,  예약 날짜 : " + datetime +" ]");
	}
}
