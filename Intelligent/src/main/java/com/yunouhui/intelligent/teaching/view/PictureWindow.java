package com.yunouhui.intelligent.teaching.view;
//package com.mr.draw;
//
//import javax.swing.*;
//
//import com.mr.util.*;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//
///**
// * �?笔画展示窗体
// * 
// * @author Dell
// *
// */
//public class PictureWindow extends JWindow
//{
//	private static final long serialVersionUID = 1l;
//	private JButton changeButton; // 更换图片按钮
//	private JButton hiddenButton;// 隐藏按钮
//	private BackgroundPanel centerPanel;// 展示图片的带背景图面�?
//	File list[];// 图片文件数组
//	int index;// 当前选中的图片索�?
//	DrawPictureFrame frame;// 父窗�?
//
//	/**
//	 * 构�?�方�?
//	 * 
//	 * @param frame
//	 */
//	public PictureWindow(DrawPictureFrame frame)
//	{
//		this.frame = frame;
//		setSize(400, 400);
//		init();
//		registerListener();
//	}
//
//	private void init()
//	{
//		Container c = getContentPane();
//		File dir = new File("src/img/picture");// 创建�?笔画素材文件夹对�?
//		list = dir.listFiles();
//		centerPanel = new BackgroundPanel(getListImage());
//		c.add(centerPanel, BorderLayout.CENTER);
//
//		FlowLayout flow = new FlowLayout(FlowLayout.RIGHT);
//		flow.setHgap(20);
//		JPanel southPanel = new JPanel();
//		southPanel.setLayout(flow);
//		changeButton = new JButton("更换图片");
//		southPanel.add(changeButton);
//		hiddenButton = new JButton("隐藏");
//		southPanel.add(hiddenButton);
//		c.add(southPanel, BorderLayout.SOUTH);
//	}
//
//	private void registerListener()
//	{
//		hiddenButton.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				setVisible(false);
//				frame.initShowPicButton();
//			}
//		});
//
//		changeButton.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				centerPanel.setImage(getListImage());// 背景面板重新载入图片
//			}
//		});
//	}
//
//	/**
//	 * 获取图片文件夹下的图片，每次调用此方法，都会获得不同的文件对�?
//	 * 
//	 * @return 返回图片对象
//	 */
//	private Image getListImage()
//	{
//		String imgPath = list[index].getAbsolutePath();
//		ImageIcon image = new ImageIcon(imgPath);
//		index++;
//		if (index >= list.length)
//		{
//			index = 0;
//		}
//
//		return image.getImage();
//	}
//}
