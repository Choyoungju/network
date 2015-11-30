package com.hanains.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		
		
		while(true){
			
			String hostname = scanner.nextLine();
			
			if("exit".equals(hostname)){
				break;
			}
			
			try {
				InetAddress.getAllByName(hostname);
				System.out.println();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
