package Test;

import java.util.Scanner;

public class Global {
	static Scanner sc = new Scanner(System.in);

	//메서드
	public static void Logo() {
		System.out.println("***********************");
		System.out.println("프로그램을 시작합니다.");
		System.out.println("2021.02.23");
		System.out.println("서버 클라이언트를 이용한 프로그램");
		System.out.println("정민용");
		System.out.println("***********************");
	}
	
	public static void Ending() {
		System.out.println("***********************");
		System.out.println("프로그램을 종료합니다.");
		System.out.println("***********************");
	}
	
	public static char MenuPrint() {
		System.out.println("***********************");
		System.out.println("[0]. 프로그램종료\n");
		System.out.println("[1]. 회원 등록\n");
		System.out.println("[2]. 회원전체출력\n");
		System.out.println("[3]. 음료수 등록\n");
		System.out.println("[4]. 음료수 전체출력\n");
		System.out.println("[5]. 등급테이블 전체 출력\n");
		System.out.println("[6]. 구매테이블 출력\n");
		System.out.println("[7]. 음료수 구매\n");
		System.out.println("[8]. 회원 구매 정보 검색 \n");
		System.out.println("[9]. 가장 많이 판매된 음료수 정보 출력력\n");
		System.out.println("***********************");
		System.out.println(" >> ");
		return sc.nextLine().charAt(0);
	}
	
	//pause 멈추는 기능
	public static void Pause() {
		System.out.println("엔터키를 누르세요.");
		sc.nextLine();
	}

	public static int InputNumber(String msg) {
		System.out.println(msg);
		return Integer.parseInt(sc.nextLine());
	}
	
	public static String InputString(String msg) {
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public static char InputChar(String msg) {
		System.out.println(msg);
		return sc.nextLine().charAt(0) ;
	}
}
