package com.yunouhui.intelligent.teaching.view;

import java.awt.*;

/**
 * �?笔画展示窗体
 * 
 * @author Dell
 *
 */
public class DrawPictureCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	private Image image = null;// 创建画板中展示的图片对象

	/**
	 * 设置画板中的图片
	 * 
	 * @param img
	 *            画板中展示的图片的对�?
	 */
	public void setImage(Image img) {
		this.image = img;
	}

	/**
	 * 重写paint()方法，在画布上回值图�?
	 */
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);// 在画布上绘制图像
	}
	

	/**
	 * 重写update()方法,这样可以解决屏幕闪烁的问�?
	 */
	public void update(Graphics g) {
		paint(g);
	}
}
