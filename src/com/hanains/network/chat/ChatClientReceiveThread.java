package com.hanains.network.chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiveThread extends Thread{

	private BufferedReader bufferedReader;
	
	public ChatClientReceiveThread(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//reader를 통해 데이터 콘솔에 출력하기 (message 처리r
		try {
			while( true ) {
				String data = bufferedReader.readLine();
				if( data == null ) {
					break;
				}
				System.out.println( data );
			}
		} catch( IOException ex ) {
			ChatClient.consolLog( "error:" + ex );
		}
	}
	

}