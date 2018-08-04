package com.yunouhui.intelligent.teaching.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunouhui.intelligent.teaching.util.JConlementService;
import com.yunouhui.intelligent.teaching.util.TaskWithResult;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OperationApp {
	private static final Logger logger = LoggerFactory.getLogger(OperationApp.class);  
	
	private JFrame frame;
	private HashMap<String,String> filePathMap = new HashMap<String,String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperationApp window = new OperationApp();
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
	public OperationApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon luzhiImg = new ImageIcon("images/bofang.jpg");// 创建图片对象
		ImageIcon jietuImg = new ImageIcon("images/bofang.jpg");// 创建图片对象
		ImageIcon banshuImg = new ImageIcon("images/bofang.jpg");// 创建图片对象
		JButton luzhiButton = new JButton("录制");
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		String screenComFile = "";
		JConlementService app = new JConlementService();
		luzhiButton.setIcon(luzhiImg);
		luzhiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 测验是否监听成功
				logger.info("点击了开始录制按钮");
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
					File file = chooser.getSelectedFile();
					// 判断文件夹是否存在
					if (!file.exists()) {
						// 如果不存在就创建一个文件夹
						file.mkdirs();
					}
					String ffmpegExeResources = app.getFfmpegExeResources();
					String biaozhi = String.valueOf(new Date().getTime());
					//视频地址
					String screenComFile = file.getAbsolutePath()+"\\screen_"+biaozhi+".avi";
					logger.info("视频地址:{}",screenComFile);
			    	//音频地址
					String videoComFile = file.getAbsolutePath()+"\\video_"+biaozhi+".mp3";
					//合成地址
					String resultComFile = file.getAbsolutePath()+"\\screen_result_"+biaozhi+".avi";
					logger.info("音频地址:{}",videoComFile);
					String screenCom = app.getScreenCom(ffmpegExeResources,file.getAbsolutePath(),screenComFile);
			    	String videoCom = app.getVideoCom(ffmpegExeResources, file.getAbsolutePath(),videoComFile);
			    	List<Future<ConcurrentMap<String, Object>>> resultList = new ArrayList<Future<ConcurrentMap<String, Object>>>();  
			    	Future<ConcurrentMap<String, Object>> submitOne = executorService.submit(new TaskWithResult(app, screenCom));
			    	Future<ConcurrentMap<String, Object>> submitTwo = executorService.submit(new TaskWithResult(app, videoCom));
			    	resultList.add(submitOne);
			    	resultList.add(submitTwo);
			    	for(int i = 0;i<resultList.size();i++){
			    		try {
							ConcurrentMap<String, Object> concurrentMap = resultList.get(i).get();
							app.handlerMap.put(biaozhi+i, concurrentMap);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			    	}
			    	filePathMap.put("screen_address", screenComFile);
			    	filePathMap.put("video_address", videoComFile);
			    	filePathMap.put("result_address", resultComFile);
				}
			}
		});
		JButton jieshuButton = new JButton("结束");
		jieshuButton.setIcon(jietuImg);
		jieshuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//屏幕和音频录制结束
				for (String key : JConlementService.handlerMap.keySet()) {
					logger.info(key);
					app.removePush(key);
				}
				//文件合成--String ffmpeg_path,String ScreenPath,String VideoPath,String fileName
				String screenAndVideoCom = app.getScreenAndVideoCom(app.getFfmpegExeResources(), filePathMap.get("screen_address"), filePathMap.get("video_address"), filePathMap.get("result_address"));
				ConcurrentMap<String, Object> push = app.push(screenAndVideoCom);
			}
		});
		
		
		
		
		
		JButton banshuButton = new JButton("板书");
		banshuButton.setIcon(banshuImg);
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(banshuButton, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(luzhiButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(110)
							.addComponent(jieshuButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(luzhiButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addComponent(jieshuButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(banshuButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
