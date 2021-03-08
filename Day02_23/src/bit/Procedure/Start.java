package bit.Procedure;

public class Start {
	
	public static void exam1() {
		AccountDB db = new AccountDB();
		db.Run();
		
		db.MakeAccount(80, "ccm12", 3000);
		Account acc = db.SelectAccount(80);
		acc.Println();
	}
	
	public static void main(String[] args) {
		exam1();
	}
}
