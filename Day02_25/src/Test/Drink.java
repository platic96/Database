package Test;

public class Drink {
	int drinkid;
	String name;
	int price;
	int count;
	
	public Drink(int drinkid,String name, int price, int count) {
		this.drinkid = drinkid;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	public void Print() {
		System.out.println("*************************************************");
		System.out.println("[ 음료 아이디 ] " + this.drinkid);
		System.out.println("[ 음료 이름 ] " + this.name);
		System.out.println("[ 음료 가격 ]" + this.price);
		System.out.println("[ 음료 판매 개수 ]" + this.count);
		System.out.println("*************************************************");
		System.out.println();
	}

}
