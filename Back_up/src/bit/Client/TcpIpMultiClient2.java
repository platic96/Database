//package bit.Client;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class TcpIpMultiClient2 {
//	 public final int PORT = 4000;
//	 public final String SERVER_IP = "192.168.0.4";
//	 private Socket socket;
//	 private Bank bank;
//	 private ClientSender2 client;
//	 
//	 public TcpIpMultiClient2(Bank bank) {
//		 this.bank = bank;
//	 }
//	 
//	 void Run(){
//	  try{
//		  socket = new Socket(SERVER_IP, PORT);
//		  System.out.println("서버 연결 됨");
//	   
//		  client = new ClientSender2(socket);
//		  Thread receiver = new ClientReceiver2(socket,bank); 
//		  
//		  receiver.start();
//	  	}catch(ConnectException e){
//	  		e.printStackTrace();
//	  	}catch(Exception e) { }
//	 }
//
//	public void SendMessage(String msg) {
//		// TODO Auto-generated method stub
//		 try{
//			 client.SendMessage(msg);
//		 }
//		 catch(Exception e) {
//			 System.out.println(e.getMessage());
//			 return;
//		 }
//	}
//}
//	 
//class ClientSender2{
//		 PrintWriter writer;
//		 
//		 ClientSender2(Socket socket){
//		  //6.소켓으로부터 인풋스크림, 아웃풋 스크림 얻음
//			 try{
//				 writer = new PrintWriter(socket.getOutputStream());
//			 }catch(Exception e) {}
//		 }
//		 
//		 public void SendMessage(String msg) {
//			 writer.println(msg);
//			 writer.flush();
//		 }
//	 }
//	 
//class ClientReceiver2 extends Thread{
//	 Socket socket;
//	 BufferedReader reader;
//	 Bank bank;
//	 
//	 ClientReceiver2(Socket socket,Bank bank){
//		 this.socket=socket;
//		 this.bank = bank;
//		 try{
//		   //6. 소켓으로부터 인풋, 아웃풋 스크림 얻음
//			 reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		  }catch(IOException e) {}
//	}
//	public void run(){
//		  //7.인풋,아웃풋 이용한 통신
//		  //8. 연결이 끊어질때까지 통신
//			  while(reader !=null){
//				   try{
//					   String msg =null;
//					   msg= reader.readLine();
//					   bank.RecvData(msg);
//				   	}catch(IOException e){}
//			  }
//	}
//}