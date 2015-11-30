package com.hanains.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String args[]){
		
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("Host 이름 : " + inetAddress.getHostName()); // 이 컴퓨터 이름
			System.out.println("Host IP Address : " + inetAddress.getHostAddress());
			
			byte[] addresses = inetAddress.getAddress();
			
			for(int i=0; i<addresses.length; i++){
				//System.out.println(addresses[i]);
				System.out.print(addresses[i] & 0xff); //양의 정스로 바꾸저ㅜㄹ 필요 (2의 보수 관련))
				
				if(i+1<addresses.length){
					System.out.print(".");
				}
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
