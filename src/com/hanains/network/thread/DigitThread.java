package com.hanains.network.thread;

import java.util.ArrayList;
import java.util.List;

public class DigitThread  extends Thread {

//	private List list = new ArrayList();
//	public DigitThread(){
//	
//	}
//	
//	public DigitThread(List list){
//		this.list = list;
//	}
	
	
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
