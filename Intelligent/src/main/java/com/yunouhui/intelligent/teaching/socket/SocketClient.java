package com.yunouhui.intelligent.teaching.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	public static final String STUDENT_NAME_TEST_TWO = "姓名";
	public int port = 8080;
	Socket socket = null;
	public static void main(String[] args) {
		new SocketClient();
	}
	public SocketClient() {
		try {
			socket = new Socket("127.0.0.1", port);
			new Cthread().start();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String msg1;
			while ((msg1 = br.readLine()) != null) {
				System.out.println(msg1);
			}
		} catch (Exception e) {
		}
	}
	class Cthread extends Thread {
		public void run() {
			try {
				BufferedReader re = new BufferedReader(new InputStreamReader(
						System.in));
				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				String msg2;
				while (true) {
					msg2 = re.readLine();
					pw.println(msg2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
