package com.hanains.network.echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerReceiveThread {
	private static final int PORT = 5050;
	
	public static void main(String args[] ){
		
		try {
			
			
//			//1. 서버소켓 생성
//		//	serverSocket = new ServerSocket();
//
//			//2. 바인딩
////			InetAddress inetAddress = InetAddress.getLocalHost();
////			String localhost = inetAddress.getHostAddress();
////
////			serverSocket.bind(new InetSocketAddress(localhost,PORT));
////			System.out.println("[서버] 바인딩 " + localhost  + " : " + PORT);
////
////
////			//3. 연결 요청 대기(ACCEPT)
////			Socket socket = serverSocket.accept();
			
			
			
			
			
			ServerSocket server = new ServerSocket(5050);
			System.out.println("접속 대기요");
			
			while(true){
				Socket socket = server.accept();
				EchoThread echothread = new EchoThread(socket);
				echothread.start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
