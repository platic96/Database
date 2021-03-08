package bit.Server;

public class Start {
	//main : 시스템에서 제공하는 primary thread
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager mag = Manager.getInstance();
		mag.Run();
//		try {
//			AccountDB db = new AccountDB();
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

}
