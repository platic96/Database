package bit.Client;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TcpIpMultiClient t = new TcpIpMultiClient();
//		t.Run();
		
		App app = App.getInstance();
		app.Run();
		app.Exit();
	}
}
	