package team;

public class User {
	private String userId; 
	private int balance;
	
	public User(String userId, int balance) {
		this.userId = userId;
		this.balance = balance;
	}
	
	// --------------------------------------------------- method ------------------------------------------

	@Override
	public String toString() {
		return "[ 회원Id ] "+this.getUserId() + "\t [ 잔액 ] "+this.getBalance();
	}
	
	public boolean equals(String userId) {
		return this.userId.equals(userId);
	}
	
	public void print() {
		System.out.println("[user_id] "+userId+" \t [잔액] "+balance);
	}
	// --------------------------------------------------- Getter ------------------------------------------
	public String getUserId()	{
		return this.userId;
	}
	
	public int  getBalance()	{
		return balance;
	}
	
}
