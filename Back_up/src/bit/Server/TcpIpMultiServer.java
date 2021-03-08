package bit.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultiServer {
	 public final int PORT = 4000;
	 public HashMap<String, PrintWriter>clients;
	 ServerSocket serverSocket;
	 Socket socket;
	 
	 public TcpIpMultiServer(){
	  //1. ServerSocket 생성
		 try {
		 clients = new HashMap<String, PrintWriter>();
		 serverSocket = null;
		 socket = null;
		 }
		 catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	void Run()
	{		
		try{
			serverSocket = new ServerSocket(PORT);
			System.out.println("서버시작,접속 기다림");
	  
			while(true){
				socket = serverSocket.accept();
				System.out.println("[클라이언트 접속]"+socket.getInetAddress()+":"+socket.getPort());
				ServerReceiver thread = new ServerReceiver(socket, clients);
				thread.start(); }
	   
		  //2. ServerSocket 의 accept() 실행 및 대기(클라이언트가 접속할 때까지)
		  //5. 클라이언트가 접속을 시도하면 accept() 메소드가 클라이언트의 socket을 리턴
		 	}catch(Exception e){
		 		System.out.println("exception: "+e.getMessage());
		 	}
	 }
}


class ServerReceiver extends Thread{   //이 안에는 Receiver 서버만 받는다.
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	HashMap<String, PrintWriter> clients;
 
	public ServerReceiver(Socket socket,HashMap<String, PrintWriter>clients) {
		//6. Socket으로붙 InputStream, OutputStream을 얻음
		this.socket= socket;
		this.clients=clients;
		try{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
		}catch(Exception e){}
	
 }
	
	void sendToAll(String msg){
		Iterator<String> itr = clients.keySet().iterator();
		while(itr.hasNext()){
			PrintWriter writer = clients.get(itr.next());
			writer.println(msg);
			writer.flush();
		}
	}
	@Override
	public void run(){
		String name="";
		try{
			name = reader.readLine();
			clients.put(name,writer);
			sendToAll(name+"들어옴");
			System.out.println("현재 접속자수"+clients.size()+"이다.");
			//7 InputStream, OutputStream을 이용한 통신
			//8 연결이 끊어질 때까지 통신
			while(reader!=null){
				sendToAll(reader.readLine());
			}
		}catch(Exception e){e.printStackTrace();}
		finally {
			sendToAll(name+"나갔어");
			clients.remove(name);
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+"에서 접속 종료함");
			System.out.println("현재 서버접속자수"+clients.size());
		}
		try{
			//9 소켓 닫음.
			socket.close();
		}catch(Exception e){e.printStackTrace();}
 	}
}
