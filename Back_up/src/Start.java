
public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			App app = App.getInstance();
			app.Run();
			app.Exit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
