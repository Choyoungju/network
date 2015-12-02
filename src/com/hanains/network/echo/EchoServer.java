package com.hanains.network.echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static final int PORT = 5050;
	
	public static void main( String[] args ) {
		
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩
			String localhost = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind( new InetSocketAddress( localhost, PORT  ) );
			consolLog( "바인딩 " + localhost + ":" + PORT );
			
			while( true ) {
				//3. 연결 요청 대기( accept )
				Socket socket = serverSocket.accept();
				
				//4. 연결 성공 / 스레드 생성
				Thread thread = new EchoServerReceiveThread( socket );
				thread.start();
			}
			
		} catch( IOException ex ) {
			consolLog( "에러:" + ex );
		} finally {
			// 5. 자원정리
			try {
				if( serverSocket != null && serverSocket.isClosed() == false  ) {
					serverSocket.close();
				}
			} catch( IOException ex ) {
				consolLog( "에러:" + ex );
			}
		}		
	}
	
	public static void consolLog( String message ) {
		System.out.println(  "[에코서버] " + message );
	}	
}