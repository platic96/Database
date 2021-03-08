package bit.Server.pro;

import java.util.ArrayList;

public class Manager {
	//싱글톤 패턴 코드 -------------------------------------------------
	//생성자 은닉!
	private Manager() { 	}		
	//자신의 static 객체 생성
	private static Manager Singleton = new Manager();
	
	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static Manager getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	
	//통신
	private TcpIpMultiServer server = new TcpIpMultiServer(); //<===================
	
	//데이터 베이스
	private AccountDB db = new AccountDB();
	
	//DB와 Network을 연동
	public void Run() {				//<=================================
		if(db.Run()==false) {
			System.out.println("서버 종료");
			System.exit(0);		//강제 종료 함수
		}
		server.Run();
	}
	
	//통신모듈에서 전달 ---> 파서에게 전달
	public String RecvData(String msg) {
		return Parser.RecvData(msg);
	}
	
	//파서에서 분석된 결과에 따라 해당 함수를 호출 -----------------------------------
	public String MakeAccount(int id, String name, int balance) {
		//저장!
		String msg = null;
		
		if( db.MakeAccount(id,name,balance) == true)
			msg = Packet.MakeAccount_ack(id, true);
		else
			msg = Packet.MakeAccount_ack(id, false);
		
		//클라이언트에 전송!
		return msg;
	}

	public String SelectAccount(int id) {
//	
		String msg = null;
		Account acc = db.SelectAccount(id);
		
		if( acc != null)
			{
				msg = Packet.SelectAccount_ack(acc.getAccid(),acc.getName(),acc.getBalance(),acc.GetDate(),acc.GetTime(),true);
			}
		else
			msg = Packet.SelectAccount_ack(id,"-",0,"-","-",false);
		
		//클라이언트에 전송!
		return msg;
	}

	public String InputAccount(int id, int balance) {
		// TODO Auto-generated method stub
		
		String msg = null;

		if( db.InputAccount(id,balance) == true)
			msg = Packet.InputAccount_ack(id, balance,true);
		else
			msg = Packet.InputAccount_ack(id, balance,false);
		
		return null;
	}
	
	public String OutputAccount(int id, int balance) {
		// TODO Auto-generated method stub
		
		String msg = null;

		if( db.InputAccount(id,balance) == true)
			msg = Packet.OutputAccount_ack(id, balance,true);
		else
			msg = Packet.OutputAccount_ack(id, balance,true);
		
		return null;
	}

	public String DeleteAccount(int id) {
		// TODO Auto-generated method stub
		String msg = null;
		
		if(db.DeleteAccount(id))
			{
				msg = Packet.DeleteAccount_ack(id, true);
			}
		else
			msg = Packet.DeleteAccount_ack(id,false);
		
		//클라이언트에 전송!
		return msg;
	}

	public String SelectAllAccount() {
		// TODO Auto-generated method stub
		ArrayList<Account> acclist = db.SelectAllAccount();
		return null;
	}
}












