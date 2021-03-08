package Test;
//app.java

//실행의 흐름을 담당
public class App {

	//싱글톤 패턴 코드 -------------------------------------------------
	//생성자 은닉!
	private App() {
		Init();
	}
	//자신의 static 객체 생성
	private static App Singleton = new App();
	
	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static App getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	Manager mg = Manager.getInstance();
	//메서드
	//초기화 영역
	private void Init() {
		Global.Logo();
	}
	
	//반복적 실행 - 엔진
	public void Run() {
		while(true) {		
			switch(Global.MenuPrint()) {
			case '0': return;
			case '1': mg.MakeMemeber();	break;
			case '2': mg.PrintAllMember(); break;
			case '3': mg.MakeDrink(); 	break;
			case '4': mg.PrintAllDrink(); 	break;
			case '5': mg.PrintLevel(); break;
			case '6': mg.PrintAllBuyList();		break; 
			case '7': mg.BuyDrink();		break; 
			case '9': mg.ManySellDrink();		break; 
			}
			Global.Pause();
		}
	}
	
	//종료처리 영역
	public void Exit() {
		Global.Ending();
	}
}












