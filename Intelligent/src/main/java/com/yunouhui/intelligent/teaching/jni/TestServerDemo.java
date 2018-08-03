package com.yunouhui.intelligent.teaching.jni;

public class TestServerDemo {
	public static void main(String[] args) {
		TestServerJNA.INSTANCE.startPipeServer();
		while(true){
			try {
				Thread.sleep(1000*5);
				TestServerJNA.INSTANCE.sendCmd(1541);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
