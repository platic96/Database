package team;



public class MyQueue {
	private int max;
	private Object[] arr;
	private int rear;
	private int front;
	
	public MyQueue() {
		this(5);
	}
	
	public MyQueue(int _max) {
		this.max = _max;
		this.arr = new Object[_max];
		this.rear  = 0;
		this.front = 0;
	}
	
	public boolean IsOverflow() {
		return (front == (rear+1)%5);
	}
	
	public boolean Put(Object data) {
		if(IsOverflow()) {
			System.out.println("오버플로우가 발생했습니다.");
			return false;
		}
		arr[rear] = data;
		rear = (rear+1)%max;
		return true;
	}
	
	public boolean IsEmpty() {
		return front == rear;
	}
	
	public Object get() {
		if(IsEmpty())
			return null;
		else
		{
			Object obj = arr[front];
			front = (front+1)%max;
			return obj;
		}
		
	}
	
	public void PrintAll() {
		System.out.println("[front]");
		for(int i=front;i!=rear;i=(i+1)%max)
			System.out.print(arr[i]+" ");
		System.out.println("[rear]");
	}
	
	public void Clear() {
		rear = front = 0;
	}
	
	public int getMax() {
		return this.max;
	}
}
