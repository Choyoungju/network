package com.hanains.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	private static final int PORT = 5050;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = null; // 이걸 왜 하냐면 try 안에서 선언해서 쓰면 try 밖에서 serverSocket.close()를 못함.

		try {
			//1. 서버소켓 생성
			serverSocket = new ServerSocket();

			//2. 바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhost = inetAddress.getHostAddress();

			serverSocket.bind(new InetSocketAddress(localhost,PORT));
			System.out.println("[서버] 바인딩 " + localhost  + " : " + PORT);


			//3. 연결 요청 대기(ACCEPT)
			Socket socket = serverSocket.accept();

			//4. 연결 성공
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			System.out.println("[서버] 연결됨 from " + remoteHostAddress + " : " + remoteHostPort);

			//// 다음날부터 하는거
			//5. IOStream 받아오기
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			
			
		



			/////////
			//7. 소켓닫기  -> 다음날 바뀜
			//			if(socket.isClosed()==false)
			//			socket.close();


			//			//7. 자원정리 -> 에서 또
			//			inputStream.close();
			//			outputStream.close();
			//			if(socket.isClosed()==false)
			//				socket.close();



			//6. 데이터 보내기
			String data = "Hello World\r\n"; // /r 은 라인피드

			try{
				byte[] buffer = new byte[256];

				while(true){
					int readByteCount = inputStream.read(buffer); // xshell 에서 탭 하나 더 만들어서 연결해도 여기에서 막혀있는것. 쓰레드를 만들어서 멀티되게해야됨.
					if(readByteCount<0){
						System.out.println("[서버] 클라이언트로부터 연결 끊김");
						break;
					}

					
					data = new String(buffer, 0, readByteCount);
					System.out.println("[서버] 수신 데이터 : " + data );
					
					//7 데이터보내기.
					outputStream.write(data.getBytes("UTF-8")); // EUC-KR 로 하고 있으면 여기 EUC-KR로 적어야됨
					outputStream.flush();// 아웃풋스트림의 버퍼를 슬라이딩 윈도우로. 버퍼있는 스트림을 사용하면 flush() 반드시 사용
				}
			}catch(IOException ex){
				System.out.println("[서버]에러 : " + ex);
			} finally{
				//8. 자원정리
				inputStream.close();
				outputStream.close();
				if(socket.isClosed()==false)
					socket.close();
			}

			


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} finally{  // fianlly 안에서 자원정리를 하는게 맞음. finally 는 리턴하더라도 불려짐
			//서버 소켓 닫기
			if(serverSocket!=null && serverSocket.isClosed()==false){
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
