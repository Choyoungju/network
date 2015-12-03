package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient extends Thread{

	private static final int PORT = 5050;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = null;
		Socket socket = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;


		try {

			//1. 키보드연결
			scanner = new Scanner(System.in);
			//2 소켓 생성
			socket = new Socket();

			// 3 연결
			String localhost = InetAddress.getLocalHost().getHostAddress();


			socket.connect( new InetSocketAddress( localhost, PORT));

			consolLog( "바인딩 " + localhost + ":" + PORT );

			//while( true ) {


			//4 reader/writer 생성
			bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			printWriter = 
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true);

			//5 join 프로토콜
			System.out.println("닉네임>>");
			String nickname = scanner.nextLine();
			System.out.println(nickname);
			printWriter.println("join:"+nickname);
			
			printWriter.flush();

			//입력받음
			bufferedReader.readLine();

			//6 ChatClientReceiveThread 시작
			Thread thread = new ChatClientReceiveThread(bufferedReader);
			thread.start();

			//7. 키보드 입력 처리
			while(true){
				System.out.println(">>");
				String input=scanner.nextLine();

				if("quit".equals(input)==true){
					//8. quit 프로토콜 처리
					printWriter.print("quit");
					printWriter.flush();
					break;

				}else {
					//9. 메시지 처리
					printWriter.println("message:" + input );
					 
					printWriter.flush();
				}
				//}


			}

		} catch( IOException ex ) {
			consolLog( "에러:" + ex );
		} finally {
			// 10. 자원정리
			try {
				if( socket != null && socket.isClosed() == false  ) {
					socket.close();
				}
				if( printWriter != null  ) {
					printWriter.close();
				}
				if( scanner != null  ) {
					scanner.close();
				}
				if( bufferedReader != null  ) {
					bufferedReader.close();
				}
				//				if( socket != null && socket.isClosed() == false  ) {
				//					socket.close();
				//				}

			} catch( IOException ex ) {
				consolLog( "에러:" + ex );
			}
		}		

	}

	public static void consolLog( String message ) {
		System.out.println(  "[클라쪽 콘솔] " + message );
	}	

}