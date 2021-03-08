package Test;

public class BIT_BuyDrink {
	int buydrinkid;
	String memberid;
	int drinkid;
	int count;
	int totalmoney;
	
	public BIT_BuyDrink(int buydrinkid,	String memberid,int drinkid,int count,int totalmoney) {
		this.buydrinkid =buydrinkid;
		this.memberid = memberid;
		this.drinkid = drinkid;
		this.count = count;
		this.totalmoney = totalmoney;
	}
	
	public void Print() {
		System.out.println("*************************************************");
		System.out.println("[ 구매 번호 ] " + this.buydrinkid);
		System.out.println("[ 회원 아이디 ] " + this.memberid);
		System.out.println("[ 음료 번호 ]" + this.drinkid);
		System.out.println("[ 개수 ]" + this.count);
		System.out.println("[ 전체 가격 ]" + this.totalmoney);
		System.out.println("*************************************************");
		System.out.println();
	}

}
