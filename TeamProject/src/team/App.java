package team;

import java.util.ArrayList;

public class App {
	private Manager man = new Manager();


	public App() {
		Init();
	}

	private void Init() {
		Global_Menu.logo();
	}

	public static void  PrintUserReservation(User user, Manager man) {
		System.out.println(user.toString());
		ArrayList<Reservation> lists = man.SelectReservationUserId(user.getUserId());

		if (lists != null && !lists.isEmpty()) {
			for(Reservation rv : lists) {
				rv.PrintReservation();
			}
		}else
			System.out.println("현재 예약된 좌석은 없습니다.");
	}

	public void run() {

		while (true) {
			try {
				Global_Menu.main_Menu();
				// 명령어를 받아서 나눈다.
				String input = Global_Menu.InputString("명령어 입력");
				String[] sp = input.split(" ");

				switch (sp[0]) {

				case "Insert":
					if (man.MakeUser(sp[1], Integer.parseInt(sp[2])))
						System.out.println("회원 등록이 완료되어 있습니다.");
					else
						System.out.println("이미 존재하는 계정입니다.");
					break;

				case "Select":
					User user = man.SelectUser(sp[1]);
					if (user == null)
						System.out.println("등록되지 않은 계정입니다");
					else 	PrintUserReservation(user, man);
					break;

				case "Delete":
					if (man.DeleteUser(sp[1]))
						System.out.println("삭제가 완료 되었습니다");
					else
						System.out.println("등록되지 않은 계정입니다.");
					break;

				case "SelectAllUser":
					ArrayList<User> userlist = man.SelectAllUser();
					System.out.println("[전체 회원 수 ] "+ userlist.size()+"명");
					if (userlist.isEmpty())
						System.out.println("등록된 회원 정보가 없습니다");
					else {
						for(User us :userlist) {
							PrintUserReservation(us, man);
						}
					}
					break;

				case "InsertSrt":
					if (man.MakeSRT(Integer.parseInt(sp[1]), Integer.parseInt(sp[2]), Integer.parseInt(sp[3])))
						System.out.println("SRT 차량 등록이 완료되어 있습니다.");
					else
						System.out.println("이미 존재하는 차량입니다.");
					break;

				case "SelectSrt":
					SRT srt = man.selectSrt(Integer.parseInt(sp[1]));
					if (srt == null)
						System.out.println("등록되지 않은 SRT 차량입니다");
					else
						srt.Print();
					break;

				case "SelectAllSrt":
					ArrayList<SRT> srtlist = man.selectSrtAll();
					System.out.println("[전체 SRT 수 ] "+ srtlist.size()+"대");
					if (srtlist.isEmpty())
						System.out.println("등록된 SRT 차량 정보가 없습니다");
					else {
						for(SRT  sr : srtlist) {
							sr.Print();
						}
					}
					break;

				case "ShowRerv":
					System.out.println("좌석현황입니다.");
					break;


				default:
					// id를 이용해서 객체를 가져온다.
					user = man.SelectUser(sp[0]);

					if (user == null)
						System.out.println("등록되지 않은 계정입니다.");

					// 예약 또는 취소 명령어 입력 ex) Book <좌석번호>
					else {
						ArrayList<SRT> srtlists = man.selectSrtAll();
						if (srtlists.isEmpty()) {
							System.out.println("등록된 SRT 차량 정보가 없습니다");
							break;
						}
						else 	
							for(SRT  sr : srtlists) 	
								sr.Print();
						Global_Menu.Book_Print();


						String[] book_sp = Global_Menu.InputString("명령어 입력").split(" ");
						switch(book_sp[0]) {
						case "Book":
							int cost = man.selectSrt(Integer.parseInt(book_sp[1])).getCharge();
							if (man.Reservation(user.getUserId(), cost, Integer.parseInt(book_sp[1]), Integer.parseInt(book_sp[2]))) {
								System.out.println(user.getUserId()+"회원님의 예약이 완료되었습니다.");
							}
							else {
								System.out.printf("%d번 SRT 차량에 %d번 좌석은 현재 예약할 수 없습니다.\n",Integer.parseInt(book_sp[1]) ,Integer.parseInt(book_sp[2]));
							}
							break;

						case "Cancle":
							Reservation rev = man.SelectReservationRId(Integer.parseInt(book_sp[1])); 

							if(rev == null) {
								System.out.println("해당 예약 번호는 없는 번호입니다.");
								break;
							}
							int srtnumber = rev.SRT_number;
							int srt_cost = man.selectSrt(srtnumber).getCharge();
							if (man.CancleReservation(user.getUserId(), srt_cost, Integer.parseInt(book_sp[1]), srtnumber)) {
								System.out.println("예약 번호 "+Integer.parseInt(book_sp[1])+"티켓이 취소되었습니다.");
								user = man.SelectUser(user.getUserId());
								PrintUserReservation(user, man);
							}
							break;
						}
					}
					break;
				}
			}catch(Exception ex) {
				System.out.println("잘못 입력하셨습니다." + ex.getMessage());
			}
		}
	}
}

