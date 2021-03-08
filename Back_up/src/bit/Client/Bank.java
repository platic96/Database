////bank.java
//package bit.Client;
//
//public class Bank {
//	//통신 모듈
//	//client의 Run메서드 호출시 서버 접속 연결!
//	private TcpIpMultiClient2 client; 
//	
//	//Bank생성시 서버 접속 요청!
//	public Bank() {
//		client= new TcpIpMultiClient2(this); //자신을 넘긴다................
//		client.Run(); 
//	}
//	
//	//---------------------------- 사용자 요청에 의해서 서버로 메시지를 전송---------
//	public void PrintAll() {
//		try {		
//			String msg = Packet.SelectAllAccount();
//			client.SendMessage(msg);
//			System.out.println("서버로 전체계좌리스트 정보를 요청");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}
//	}
//			
//	public void MakeAccount() {
//		try {
//			int number = Global.InputNumber("계좌번호");
//			String name = Global.InputString("이름");
//			int money = Global.InputNumber("입금액");
//					
//			String msg = Packet.MakeAccount(number, name, money);
//			System.out.println(msg);
//			client.SendMessage(msg);
//			System.out.println("서버로 신규계좌 정보를 전송");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}
//	}
//		
//	public void SelectAccount() {
//		try {
//			int number = Global.InputNumber("계좌번호");
//					
//			String msg = Packet.SelectAccount(number);
//			client.SendMessage(msg);
//			System.out.println("서버로 검색할 계좌번호를 정보를 전송");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}	
//	}
//		
//	public void InputMoney() {
//		try {
//			int number = Global.InputNumber("계좌번호");
//			int money = Global.InputNumber("입금액");
//			
//			String msg = Packet.InputAccount(number, money);
//			client.SendMessage(msg);
//			System.out.println("서버로 계좌입금 정보를 전송");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}	
//	}
//		
//	public void OutputMoney() {
//		try {
//			int number = Global.InputNumber("계좌번호");
//			int money = Global.InputNumber("출금액");
//			
//			String msg = Packet.OutputAccount(number, money);
//			client.SendMessage(msg);
//			System.out.println("서버로 계좌출금 정보를 전송");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}	
//	}
//	
//	public void DeleteAccount() {	
//		try {
//			int number = Global.InputNumber("계좌번호");
//			
//			String msg = Packet.DeleteAccount(number);
//			client.SendMessage(msg);
//			System.out.println("서버로 계좌삭제 정보를 전송");
//		}
//		catch(Exception ex) {
//			System.out.println("[전송실패] " + ex.getMessage());
//		}	
//	}
//	//-----------------------------------------------------------------------
//	
//	
//	//--------------------------- 통신 모듈에서 수신된 데이터를 받는 기능 --------------
//	public void RecvData(String msg) {
//		Parser.RecvData(msg, this);
//	}
//	
//	public void MakeAccount_Ack(int number, String result) {
//		// TODO Auto-generated method stub
//		if(result.equals("S"))
//			System.out.println(number + "계좌의 생성을 성공하였습니다.");
//		else
//			System.out.println(number + "계좌의 생성을 실패하였습니다.");
//	}
//
//	public void SelectAccount_Ack(String result, int number, String name, int balance) {
//		// TODO Auto-generated method stub
//		if(result.equals("F")) {
//			System.out.println(number + "계좌 번호는 없는 번호 입니다.");
//			return;
//		}
//		System.out.println("계좌번호 :"+ number);
//		System.out.println("이름 : "+ name);
//		System.out.println("잔액 : "+ balance);
//	}
//	
//}
