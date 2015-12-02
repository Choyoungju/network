package com.hanains.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoServerReceiveThread extends Thread {
	
	private Socket socket;
	
	public EchoServerReceiveThread( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			
			//1. 리모트 호스트 정보 출력
			InetSocketAddress inetSocketAddress = ( InetSocketAddress ) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			EchoServer.consolLog( "연결됨 from " + remoteHostAddress + ":" + remoteHostPort );

			//2. IOStream 받아오기
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		
			//3. 데이터 읽기
			byte[] buffer = new byte[256];
			while( true ) {
				int readByteCount = inputStream.read( buffer );
				if( readByteCount < 0 ) {
					EchoServer.consolLog( "클라이언트로 부터 연결 끊김" );
					break;
				}
				
				String data = new String( buffer, 0, readByteCount );
				EchoServer.consolLog( "수신 데이터:" + data );
				
				//4. 데이터 보내기
				outputStream.write( data.getBytes( "UTF-8" ) );
				outputStream.flush();
			}
		} catch( IOException ex ) {
			EchoServer.consolLog( "에러:" + ex );
		} finally {
			try {
				//5. 자원정리
				if( inputStream != null ) {
					inputStream.close();
				}
				if( outputStream != null ) {
					outputStream.close();
				}
				if( socket.isClosed() == false ) {
					socket.close();
				}
			} catch( IOException ex ) {
				EchoServer.consolLog( "에러:" + ex );
			}
		}
	}
}