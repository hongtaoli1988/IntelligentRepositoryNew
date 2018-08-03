package com.yunouhui.intelligent.teaching.jni;

import com.sun.jna.Pointer;

public class TestClientDemo {
	public static void main(String[] args) {
		int startPipeConnect = TestClientJNA.INSTANCE.startPipeConnect();
		while(true){
			try {
				Thread.sleep(1000);
				System.out.println(startPipeConnect);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
