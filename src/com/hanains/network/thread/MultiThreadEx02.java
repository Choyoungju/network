package com.hanains.network.thread;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List list = new ArrayList();
		
		Thread thread1 = new DigitThread();
		Thread thread2 = new LowerAlaphaTread();
		Thread thread3 = new DigitThread();
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}
