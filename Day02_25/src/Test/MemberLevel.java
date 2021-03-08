package Test;

public class MemberLevel {

	String levelname;
	int low;
	int high;
	
	public MemberLevel(String levelname, int low, int high) {
		// TODO Auto-generated constructor stub
		this.levelname = levelname;
		this.low = low;
		this.high = high;
	}

	public void Print() {
		// TODO Auto-generated method stub
		System.out.println("*************************************************");
		System.out.println("[ 레벨 이름 ] " + this.levelname);
		System.out.println("[ 최소 가격 ] " + this.low);
		System.out.println("[ 최대 가격 ]" + this.high);
		System.out.println("*************************************************");
		System.out.println();
	}
}
