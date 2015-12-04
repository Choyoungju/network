package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChatServerThread extends Thread{

	private String nickname;
	private Socket socket;
	//List<Writer> listWriters = new ArrayList<Writer>();

	List<PrintWriter> listPrintWriters ;
	
	private Set set;
	


	public ChatServerThread(Socket socket, List<PrintWriter> listPrintWriters, Set set){
		this.socket= socket;
		this.listPrintWriters = listPrintWriters;
		this.set = set;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		//1. remote host info
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;

		//2. 스트림 얻기
		try {
			bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = 
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8), true);

			///추가
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress  = inetSocketAddress.getHostName();
			int remoteHostPort = inetSocketAddress.getPort();
			ChatServer.consolLog("from" + remoteHostAddress +":" + remoteHostPort);


			while(true){
				String request = bufferedReader.readLine();
				if(request == null){
					consolLog("클라이언트로부터 연결 끊김");
					doQuit(printWriter);
					break;
				}

				String[] tokens = request.split(":");
				if("join".equals(tokens[0])){
					
					doJoin(tokens[1], printWriter);

				}else if("message".equals(tokens[0])){
					doMessage(tokens[1]);
				}else if("quit".equals(tokens[0])){
					doQuit(printWriter);
					break;

				}else{
					//Cghat.consolLog(" 에러:알수없는 요청(" + tokens[0] + ")" );
					ChatClient.consolLog("왜안됨 클라?");
					ChatServer.consolLog("왜안됨 서버?");
				}
			}
			//자원 정리
			bufferedReader.close();
			printWriter.close();
			if(socket.isClosed()==false){
				socket.close();
			}


			///
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void broadcast(String data){
		synchronized (listPrintWriters) {

			for(Writer writer : listPrintWriters){
				PrintWriter printWriter = (PrintWriter)writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void doQuit(PrintWriter printWriter){
		removeWriter(printWriter);
		String data = nickname + "님이 퇴장하였습니다.";
		broadcast(data);
	}



	private void doJoin(String nickname, PrintWriter printWriter){
		
		
		if(set.contains(nickname)){
			System.out.println("join:fail:");
			printWriter.println("join:fail:");
			printWriter.flush();
			return;
		}

		set.add(nickname);
		this.nickname = nickname;
		String da = (set.contains(nickname) + "가 추가됨");
		broadcast(da);
		
		String data = nickname + "님이 참여하였습니다.";
		broadcast(data);


		//뒤에 writer pool에 저장
		addWriter(printWriter);

		//ack
		printWriter.println("join:ok");
		printWriter.flush();

	}

	private void addWriter(PrintWriter printWriter){
		synchronized(listPrintWriters){
			listPrintWriters.add(printWriter);
		}
	}

	private void removeWriter(PrintWriter printWriter){
		//구현 잘해보기
		synchronized(listPrintWriters){
			listPrintWriters.remove(printWriter);
		}
	}

	private void doMessage(String message){
		String data = nickname + ":" + message;
		broadcast( data );

	}

	

	public static void consolLog( String message ) {
		System.out.println(  "[에코서버] " + message );
	}	

}