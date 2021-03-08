package team;


import java.util.Scanner;

public class Global_Menu {
	
	static Scanner sc = new Scanner(System.in);

	
	public static void logo() {
		System.out.println("*************************************************");
		System.out.println(" SRT 관리 프로그램 ");
		System.out.println(" 2021.02.09");
		System.out.println(" C조 ");
		System.out.println("*************************************************\n");
		pause();
	}
	public static void pause() {
		System.out.print("Enter를 입력하세요.");
		sc.nextLine();		
	}
	public static void  ending() {
		System.out.println("*************************************************");
		System.out.println(" 프로그램을 종료합니다.");
		System.out.println("*************************************************");
	}
	
	public static void main_Menu() {
		System.out.println("*************************************************");
		System.out.println(" 프로그램 명령어");
		System.out.println(" 회원 등록 : Insert <사용자ID> <잔고>");
		System.out.println(" 회원 검색 : Select <사용자ID>");
		System.out.println(" 전체 회원 조회 : SelectAllUser");
		System.out.println(" 회원 삭제 : Delete <사용자ID>");
		System.out.println(" 예약 or 취소: <사용자ID>");
		System.out.println(" SRT 등록 : InsertSrt <SRT 차량번호> <총좌석수> <이용요금> ");
		System.out.println(" SRT 검색 : SelectSrt <SRT_Number>");
		System.out.println(" 전체 SRT 조회 : SelectAllSrt");
		System.out.println("*************************************************");
	}
	
	public static void Book_Print() {
		System.out.println("*************************************************");
		System.out.println(" 좌석 예약하기 : Book <SRT차량번호> <좌석번호>");
		System.out.println(" 예약 취소하기 : Cancle <예약번호>");
		System.out.println("*************************************************");
	}
	public static int InputNumber(String msg) {
		System.out.print(msg + " : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public static String InputString(String msg) {
		System.out.print(msg + " : ");
		return sc.nextLine();
	}
	
	public static char InputChar(String msg) {
		System.out.print(msg + " : ");
		return sc.nextLine().charAt(0);
	}

}
