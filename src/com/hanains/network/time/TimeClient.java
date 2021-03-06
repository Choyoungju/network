package com.hanains.network.time;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeClient {

	private static String HOST_ADDRESS = "127.0.0.1";
	private static int PORT = 9090;
	private static int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		DatagramSocket datagramSocket = null;
		Scanner in = new Scanner(System.in);
		try{
			//1 udp 소켓 생성
			datagramSocket = new DatagramSocket();
			
			
			//2,. 전송패키생성
			String data = "";
			

				
				System.out.println("날짜입력");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
				data = format.format(new Date());
				
				byte[] sendData = data.getBytes("UTF-8");
				DatagramPacket sendPacket 
				= new DatagramPacket(sendData,sendData.length, new InetSocketAddress(HOST_ADDRESS,PORT) );
				
				//3. 데이터전송
				datagramSocket.send(sendPacket);
				
				//4. 데이터 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				datagramSocket.receive(receivePacket);
				
				//5. 데이터 출력
				data = new String(receivePacket.getData(), 0, receivePacket.getLength(),"UTF-8");
				log("데이터 수신" + data);
			
			
			
			
		}catch(Exception ex){
			log("error" + ex);
		}finally{
			if(datagramSocket != null){
				datagramSocket.close();
			}
		}
		
	}

	public static void log(String message){
		System.out.println("[UDP Echo client " + message);
	}
}
