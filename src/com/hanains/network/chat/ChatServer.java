package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ChatServer {

	private static final int PORT = 5050;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		ServerSocket serverSocket = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();


		Set set;
		
		try {

			set = new HashSet();
			
			//1 소켓 생성
			serverSocket = new ServerSocket();

			// 2바인딩
			String localhost = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind( new InetSocketAddress( localhost, PORT  ) );
			consolLog( "바인딩 " + localhost + ":" + PORT );

			//3. 연결 요청 기다림
			while( true ) {
				Socket socket = serverSocket.accept();

				Thread thread = new ChatServerThread( socket, listPrintWriters,set );
				thread.start();
			}
		} catch( IOException ex ) {
			consolLog( "error:" + ex );
		} finally {
			if( serverSocket != null && serverSocket.isClosed() == false ) {
				try {
					serverSocket.close();
				} catch( IOException ex ) {
					consolLog( "error:" + ex );
				}
			}

		}
	}


	public static void consolLog(String message) {
		System.out.println(  "[서버쪽 콘솔] " + message );

	}


}