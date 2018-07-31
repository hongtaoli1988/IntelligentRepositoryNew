package com.yunouhui.intelligent.teaching.screencap;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Recoder extends JFrame {
	JButton start, stop;
	Boolean begin = false;
	File file;

	public Recoder() {
		setTitle("视屏录制 v1.0");
		setSize(300, 80);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(300, 100);
		start = new JButton("开始");
		// 添加事件监听
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 测验是否监听成功
				System.out.println("点击了开始按钮");
				// 获取文件选择器
				JFileChooser chooser = new JFileChooser();
				// 设置文件选择器只能选择文件夹
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// 显示文件选择器窗口
				int cnt = chooser.showSaveDialog(null);
				System.out.println(cnt);
				if (cnt == 0)// 0表示选择了保存按钮
				{
					// 获取用户选择的文件夹
					file = chooser.getSelectedFile();
					// 判断文件夹是否存在
					if (!file.exists()) {
						// 如果不存在就创建一个文件夹
						file.mkdirs();
					}
					begin = true;
					// 开启线程
					new getScreen().start();
				}
			}
		});
		stop = new JButton("停止");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				begin = false;
			}
		});
		add(start);
		add(stop);
		setLayout(new FlowLayout());

		setVisible(true);

	}

	private class getScreen extends Thread {
		public void run() {

			try {
				// 创建一个机器人
				Robot robot = new Robot();
				while (begin == true) {
					// 创建一个默认工具
					Toolkit tool = Toolkit.getDefaultToolkit();
					int x = tool.getScreenSize().width;
					int y = tool.getScreenSize().height;
					// 获得一个x y值范围的矩形图形
					Rectangle rectangle = new Rectangle(x, y);
					// 缓存得到一张图片
					BufferedImage bufferedImage = robot
							.createScreenCapture(rectangle);
					// 根据指定文件夹保存
					File subfile = new File(file, new Date().getTime() + ".jpg");
					// ImageIo进行写到指定文件夹下
					ImageIO.write(bufferedImage, "jpeg", subfile);
					Thread.sleep(250);
				}
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new Recoder();
	}

}