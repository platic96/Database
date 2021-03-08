package Test;

public class Member {
	String memberid;
	String name;
	String level;
	
	public Member(String memberid,String name,String level) {
		this.memberid = memberid;
		this.name = name;
		this.level = level;
	}
	
	public void Print() {
		System.out.println("*************************************************");
		System.out.println("[ 회원 아이디 ] " + this.memberid);
		System.out.println("[ 회원 번호 ] " + this.name);
		System.out.println("[ 회원 등급 ]" + this.level);
		System.out.println("*************************************************");
		System.out.println();
	}

}
