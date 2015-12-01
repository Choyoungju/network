package com.hanains.network.thread;

public class DigitRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for(int i=0 ; i<10; i++){
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
