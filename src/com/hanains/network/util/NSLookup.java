package com.hanains.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		//scanner.nextLine();



		while(true){

			System.out.println("\n도메인네임으로 입력해주세요");
			String hostname = scanner.nextLine();

			if("exit".equals(hostname)){
				break;
			}

			try {
				InetAddress[] inetAddress = InetAddress.getAllByName(hostname);

				for(int i=0; i<inetAddress.length; i++){
					
					System.out.print(inetAddress[i]); //양의 정스로 바꾸저ㅜㄹ 필요 (2의 보수 관련))

//					if(i+1<inetAddress.length){
//						System.out.print(".");
//					}
					
					
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.out.println("왜안됨");
				e.printStackTrace();
			}
		}
	}

}
