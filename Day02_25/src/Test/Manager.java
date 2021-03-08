package Test;

import java.util.ArrayList;

public class Manager {
	//싱글톤 패턴 코드 -------------------------------------------------
	//생성자 은닉!
	private Manager() { 	
		db.Run();
		}		
	//자신의 static 객체 생성
	private static Manager Singleton = new Manager();
	
	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static Manager getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	Bit_db db = new Bit_db();

	public void Run() {
	}


	public void MakeMemeber() {
		// TODO Auto-generated method stub
		String memberid = Global.InputString("회원 아이디를 입력하세요.");
		String memberName = Global.InputString("회원 이름을 입력하세요.");
		db.MakeMemeber(memberid, memberName);
	}


	public void PrintAllMember() {
		// TODO Auto-generated method stub
		ArrayList<Member> mem = new ArrayList<Member>();
		mem	=db.PrintAllMember();
		for(Member m : mem)
			m.Print();
	}


	public void MakeDrink() {
		// TODO Auto-generated method stub
		String name = Global.InputString("음료 이름을 입력하세요.");
		int price = Global.InputNumber("가격을 입력하세요");
		db.MakeDrink(name,price);
		
	}


	public void PrintAllDrink() {
		// TODO Auto-generated method stub
		ArrayList<Drink> dr = new ArrayList<Drink>();
		dr	=db.PrintAllDrink();
		for(Drink m : dr)
			m.Print();
	}


	public void PrintLevel() {
		// TODO Auto-generated method stub
		ArrayList<MemberLevel> mem = new ArrayList<MemberLevel>();
		mem	=db.PrintAllLevel();
		for(MemberLevel m : mem)
			m.Print();
	}


	public void PrintAllBuyList() {
		// TODO Auto-generated method stub
		ArrayList<BIT_BuyDrink> mem = new ArrayList<BIT_BuyDrink>();
		mem	=db.PrintAllBuyList();
		for(BIT_BuyDrink m : mem)
			m.Print();
	}


	public void BuyDrink() {
		// TODO Auto-generated method stub
		String memid = Global.InputString("회원 id를 입력하세요.");
		int drinkid = Global.InputNumber("음료수 아이디를 입력하세요");
		int count = Global.InputNumber("갯수를 입력하세요.");
		db.MakeBuyList(memid,drinkid,count);
	}


	public void ManySellDrink() {
		// TODO Auto-generated method stub
		Drink dr = db.ManySellDrink();
		dr.Print();
	}

}












