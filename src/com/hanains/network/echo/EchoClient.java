package com.hanains.network.echo;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	
	private static final String SERVER_IP = "192.168.1.13";
	private static final int SERVER_PORT = 5050;
	
	public static void main( String[] args ) {
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		Socket socket = null;
		
		Scanner scanner = new Scanner( System.in );
		
		try {
			// 소켓 생성
			socket = new Socket();
			
			// 서버 연결
			socket.connect( new InetSocketAddress( SERVER_IP, SERVER_PORT ) );
			consolLog( "서버연결 성공" );
			
			// IOStream 받아오기
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			
			while( true ) {
				
				// 키보드 입력 받기
				System.out.print( ">>" );
				String message = scanner.nextLine();
				
				// "exit"이면 프로그램 종료
				if( "exit".equals( message ) ) {
					break;
				}
				
				// 소켓  쓰기/읽기
				outputStream.write( message.getBytes( "UTF-8" ) );
				outputStream.flush();
				
				byte[] buffer = new byte[ 128 ];
				int readByteCount = inputStream.read( buffer );
				
				message = new String( buffer, 0, readByteCount, "UTF-8" );
				System.out.println( "<<" + message );
			}
			
		} catch( Exception ex ) {
			consolLog( "에러:" + ex );
		} finally {
			try {
				scanner.close();
				
				if( inputStream != null ) {
					inputStream.close();
				}
				
				if( outputStream != null ) {
					outputStream.close();
				}
				
				if( socket != null && socket.isClosed() == false ) {
						socket.close();
				}
				
			} catch( Exception ex ) {
				ex.printStackTrace();
			}
		}		
	}
	
	public static void consolLog( String message ) {
		System.out.println(  "[클라이언트] " + message );
	}
} 
