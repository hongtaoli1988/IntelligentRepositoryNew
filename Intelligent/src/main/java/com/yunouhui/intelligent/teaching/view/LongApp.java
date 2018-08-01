package com.yunouhui.intelligent.teaching.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;

import com.yunouhui.intelligent.teaching.socket.WebsocketClient;
import com.yunpuhui.Intelligent.DAO;
import com.yunpuhui.Intelligent.base.BaseDAO;
import com.yunpuhui.Intelligent.dao.AdminDAO;
import com.yunpuhui.Intelligent.view.MainView;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LongApp {

	private JFrame frame;
	private JTextField username;
	private JLabel lblPassword;
	private JPasswordField password;
	private JLabel lblNewLabel_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LongApp window = new LongApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					//释放资源
					WebsocketClient.getWebSocketClientInstance().close();
					System.out.println("The JVM Hook is execute");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LongApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("智能教鞭");
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		
		username = new JTextField();
		username.setFont(new Font("宋体", Font.PLAIN, 20));
		username.setColumns(10);
		
		lblPassword = new JLabel("PassWord");
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 20));
		
		password = new JPasswordField();
		password.setFont(new Font("宋体", Font.PLAIN, 20));
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		lblNewLabel_1 = new JLabel("智能教鞭客户端");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(77, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(168, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(89))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(101)
					.addComponent(lblNewLabel)
					.addGap(303))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(7))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(password, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addComponent(btnNewButton)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private void check() {
		//AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if ("admin".equals(username.getText()) && "admin".equals(password.getText())) {
			frame.dispose();
			new ConnectApp();
		} else {
			username.setText("");
			password.setText("");
		}
	}
}
