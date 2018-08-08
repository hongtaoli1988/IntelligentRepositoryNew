package com.yunouhui.intelligent.teaching.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.channels.NotYetConnectedException;
import java.util.UUID;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunouhui.intelligent.teaching.socket.WebsocketClient;
import com.yunouhui.intelligent.teaching.utils.SystemConfig;

import java.awt.Font;

public class ConnectApp {
	private static final Logger logger = LoggerFactory.getLogger(ConnectApp.class);     
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectApp window = new ConnectApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConnectApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setVisible(true);
		frame.setBounds(100, 100, 751, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("本机信息");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        JLabel label_13 = new JLabel("连接准备已就绪");
		label_13.setFont(new Font("宋体", Font.PLAIN, 15));
		JButton btnNewButton_1 = new JButton("发送");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WebSocketClient client = WebsocketClient.getWebSocketClientInstance();
				System.out.println("发送一次");
				client.sendPing();
				client.send("hello word");
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("设备编号");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		//设备编号
		JLabel label = new JLabel(SystemConfig.getConfig());
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("本机ID");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("密码");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		//本机ID
		JLabel lblNewLabel_3 = new JLabel(SystemConfig.getConfig());
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		//本次密码
		String pass = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		System.out.println(pass);
		JLabel label_2 = new JLabel(pass);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_3 = new JLabel("设备连接");
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("设备编号");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_5 = new JLabel("001");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_6 = new JLabel("连接时间");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_7 = new JLabel("2018-07-01 15：15：15");
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_8 = new JLabel("上次连接");
		label_8.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JLabel label_9 = new JLabel("设备编号");
		label_9.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_10 = new JLabel("连接时间");
		label_10.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_11 = new JLabel("2018-07-01 15：15：15");
		label_11.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel label_12 = new JLabel("001");
		label_12.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JButton button = new JButton("断开连接");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//int a = JOptionPane.showConfirmDialog(null, "设备001正在连接此设备", "choose one", JOptionPane.YES_NO_OPTION);
				Object[] options = {"确认","取消"};
				int response=JOptionPane.showOptionDialog(null, "设备001正在连接此设备", "选项对话框标题",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//0--确认 1--取消
				System.out.println(response);
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(284))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1))
									.addGap(31)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(90)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
							.addGap(167)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
									.addGap(37)))
							.addGap(96))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(375)
					.addComponent(label_8, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING)
						.addComponent(label_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)
							.addGap(23))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1)
							.addGap(21))))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
