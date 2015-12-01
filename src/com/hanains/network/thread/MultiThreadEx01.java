package com.hanains.network.thread;

public class MultiThreadEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread digitThread = new DigitThread();
		digitThread.start();
		
		//포문 뒤에 쓰레드 실행하면 다 나오고 쓰레드 실행되니 의미가 없음.
		for(char c= 'A'; c<'Z'; c++){
			System.out.print(c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
