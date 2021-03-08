import java.util.ArrayList;

public class Bank {
	private AccountDB db; // 계좌리스트 관리

	// 생성자
	public Bank() {
		try {
			db = new AccountDB();
		} catch (Exception e) {
			System.out.println("데이터 베이스 접속 에러");
		}
	}

	// 메서드
	public void PrintAll() {

		ArrayList<Account> accounts = db.Select();
		System.out.println("[저장개수] " + accounts.size() + "개");
		for (Account ac : accounts) {
			ac.Print();
		}
		System.out.println();
	}

	private boolean IsAccNumberCheck(int number) {
		return db.IDCheck(number);
	}

	public void MakeAccount() {
		try {
			// 1. 요청 정보
			int number = BitGlobal.InputNumber("계좌번호");
			if (IsAccNumberCheck(number) == true)
				throw new Exception("중복된 계좌번호가 존재합니다.");

			String name = BitGlobal.InputString("이름");
			int money = BitGlobal.InputNumber("입금액");

			// 2. 저장
			if (db.Insert(number, name, money) == false)
				throw new Exception();

			// 3.결과
			System.out.println("저장 성공!");
		} catch (Exception ex) {
			System.out.println("[저장실패] " + ex.getMessage());
		}
	}

	public void SelectAccount() {
		// 1. client
		int number = BitGlobal.InputNumber("계좌번호 입력");

		// 2. server
		Account acc = new Account(0, "");
		ArrayList<AccountIO> acciolist = new ArrayList<AccountIO>();
		if (db.Select(number, acc, acciolist) == false) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}

		// 3. client
		acc.Println();
		System.out.println("----------------------");
		for (AccountIO aio : acciolist) {
			aio.Print();
		}
		System.out.println();
	}

	public void InputMoney() {
		try {
			// 1. client
			int accnum = BitGlobal.InputNumber("계좌번호 입력");
			int money = BitGlobal.InputNumber("입금액 입력");

			// 2. server
			boolean result = db.Update(accnum, true, money);

			// 3. client
			if (result = false) {
				throw new Exception("");
			} else
				System.out.println(money + "원이 입금되었습니다.");
		} catch (Exception ex) {
			System.out.println("[입금오류]" + ex.getMessage());
		}
	}

	public void OutputMoney() {
		try {
			// 1. client
			int accnum = BitGlobal.InputNumber("계좌번호 입력");
			int money = BitGlobal.InputNumber("입금액 입력");

			// 2. server
			boolean result = db.Update(accnum, false, money);

			// 3. client
			if (result = false) {
				throw new Exception("");
			} else
				System.out.println(money + "원이 출금되었습니다.");
		} catch (Exception ex) {
			System.out.println("[출금오류]" + ex.getMessage());
		}
	}

//	public void DeleteAccount() {
//		try {
//			// 1. client
//			int accnum = BitGlobal.InputNumber("계좌번호 입력");
//
//			// 2. server
//			boolean isreturn = db.Delete(accnum);
//
//			// 3. client
//			if (isreturn == true)
//				System.out.println("삭제되었습니다.");
//			else
//				throw new Exception("");
//		} catch (Exception ex) {
//			System.out.println("[삭제 에러] " + ex.getMessage());
//		}
//	}
////	private int NumberToIdx(int number) {
//		for(int i=0; i< arr.getSize(); i++) {
//			Account acc = (Account)arr.getData(i);
//			if( acc.getAccid() == number)
//				return i;
//		}
//		return -1;
//	}
//	
//	//특정계좌의 거래리스트 개수 반환
//	private int getAccountIOCount(int accnumber) {
//		int sum = 0;
//		for(int i=0; i< acciolist.getSize(); i++)	{
//			AccountIO accio = (AccountIO)acciolist.getData(i); 
//			if( accio.getAccNum() == accnumber)
//				sum++;
//		}
//		return sum;
//	}
//	
//	//거래리스트 출력 함수
//	private void PrintAccountIO(int accnumber) {
//		System.out.println("[저장개수] " + getAccountIOCount(accnumber) + "개");
//		for(int i=0; i< acciolist.getSize(); i++)	{
//			AccountIO accio = (AccountIO)acciolist.getData(i); 
//			if( accio.getAccNum() == accnumber)
//				accio.Print();
//		}
//	}

}
