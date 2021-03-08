package team;

public class SRT {
	private String seat_state = "";
	private int SRT_Number;
	private int Charge;
	private int max_Seat;

	public SRT(int srt_number, int max_seat, String seat_state, int Charge) {
		this.SRT_Number = srt_number;
		this.max_Seat = max_seat;
		this.seat_state = seat_state;
		this.Charge = Charge;
	}

	public void Print() {
		System.out.println("*************************************************");
		System.out.println("[ SRT 번호 ] " + this.SRT_Number);
		System.out.println("[ 좌석 개수 ] " + this.max_Seat);
		System.out.println("[ 좌석 현황 ]");

		char[] arr = seat_state.toCharArray();
		for(int i = 0; i<arr.length; i++) 
			System.out.print(" "+(i+1)+" ");
		System.out.println();

		for(int i = 0; i<arr.length; i++) 
			System.out.print(" "+ arr[i]+" ");
		System.out.println();
		System.out.println("[ 요금 ] " + Charge);

	}


	public boolean IsSoldout() {
		int cnt = 0;
		for (int i = 0; i < seat_state.length(); i++) {
			if (seat_state.charAt(i) == '1')
				cnt++;
		}
		return max_Seat <= cnt;
	}

	public boolean Choice_Seat(int Seat_number) {
		if (Seat_number <= 0)
			return false;

		seat_state = seat_state.substring(0, Seat_number-1) + "1"+seat_state.substring(Seat_number);
		return true;

	}

	// -------------- Getter & Setter
	public boolean Cancle_Seat(int seatNumber) {
		if (seatNumber <= 0)
			return false;
		String str1 = seat_state.substring(0, seatNumber-1);
		String str2 = seat_state.substring(seatNumber, seat_state.length());
		this.setSeat_state(str1+ "0"+str2);
		System.out.println(seat_state);
		return true;
	}

	public String getSeat_state() {
		return seat_state;
	}

	private void setSeat_state(String seat_state) {
		this.seat_state = seat_state;
	}

	public int getSRT_Number() {
		return SRT_Number;
	}

	public int getCharge() {
		return Charge;
	}

	public int getMax_Seat() {
		return max_Seat;
	}


}