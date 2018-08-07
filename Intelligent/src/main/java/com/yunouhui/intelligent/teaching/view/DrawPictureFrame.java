package com.yunouhui.intelligent.teaching.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

import com.yunouhui.intelligent.teaching.utils.*;

/**
 * 画图主窗�?
 * 
 * @author Dell
 *
 */
public class DrawPictureFrame extends JFrame implements FrameGetShape
{
	private static final long serialVersionUID = 1L;

	// 创建�?�?8位的BGR颜色分量的图�?
	private BufferedImage image = new BufferedImage(570, 390, BufferedImage.TYPE_INT_BGR);
	private Graphics gs = image.getGraphics();// 获取图像的绘图对�?
	private Graphics2D g = (Graphics2D) gs;// 将绘图对象转换为Graphics2D类型
	private DrawPictureCanvas canvas = new DrawPictureCanvas();// 创建画布对象
	private Color foreColor = Color.BLACK;// 定义前景�?
	private Color backgroundColor = Color.WHITE;// 定义背景�?
	private int x = -1;// 上一次鼠标绘制点的横坐标
	private int y = -1;// 上一次鼠标绘制点的纵坐标
	private boolean rubber = false;// 橡皮标识变量
	private JToolBar toolBar;// 工具�?
	private JButton eraserButton;// 橡皮按钮
	private JToggleButton strokeButton1;// 细线按钮
	private JToggleButton strokeButton2;// 粗线按钮
	private JToggleButton strokeButton3;// 较粗按钮
	private JButton backgroundButton;// 背景色按�?
	private JButton foregroundButton;// 前景色按�?
	private JButton clearButton;// 清楚按钮
	private JButton saveButton;// 保存按钮
	private JButton shapeButton;// 图形按钮
	private boolean drawShape = false;// 画图形标识变�?
	private Shapes shape;// 绘画的图�?
//	private JMenuItem strokeMenuItem1;// 细线菜单
//	private JMenuItem strokeMenuItem2;// 粗线菜单
//	private JMenuItem strokeMenuItem3;// 较粗菜单
//	private JMenuItem clearMenuItem;// 清楚菜单
	private JMenuItem saveMenuItem;// 保存菜单
//	private JMenuItem foregroundMenuItem;// 前景色菜�?
//	private JMenuItem backgroundMenuItem;// 背景色菜�?
//	private JMenuItem eraserMenuItem;// 橡皮檫菜�?
	private JMenuItem exitMenuItem;// �?出菜�?
	//private JMenuItem shuiyinMenuItem;// 水印菜单
	private String shuiyin = "";// 水印字符内容
	//private PictureWindow picWindow;// �?笔画窗体
	private JButton showPicButton;// 展开�?笔画按钮

	public DrawPictureFrame()
	{
		this.setTitle("板书记录");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗口关闭时停止程�?
		this.setSize(574, 460);
		this.setResizable(false);// 设置不可放大
		this.setLocationRelativeTo(null);// 设置窗体居中

		init();
		registerListener();
	}

	private void init()
	{
		g.setColor(backgroundColor);// 用背景色设置绘图对象的颜�?
		g.fillRect(0, 0, 570, 390);// 用背景色填充整个画布
		g.setColor(foreColor);// 用前景色设置绘图对象的颜�?
		canvas.setImage(image);// 设置画布的图�?
		getContentPane().add(canvas);// 将画布添加到窗体容器默认布局的中部位�?

		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
//		showPicButton = new JButton();
//		showPicButton.setToolTipText("展开�?笔画");// 设置鼠标悬停提示
//		showPicButton.setIcon(new ImageIcon("images/展开.png"));
//		toolBar.add(showPicButton);

		saveButton = new JButton();
		saveButton.setToolTipText("保存");
		saveButton.setIcon(new ImageIcon("images/保存.png"));
		toolBar.add(saveButton);// 工具栏添加按�?
		toolBar.addSeparator();// 工具栏添加分割线

		strokeButton1 = new JToggleButton();
		strokeButton1.setToolTipText("细线");
		strokeButton1.setIcon(new ImageIcon("images/1像素线条.png"));
		strokeButton1.setSelected(true);// 细线按钮处于被�?�中状�??
		toolBar.add(strokeButton1);// 工具栏添加按�?

		strokeButton2 = new JToggleButton();
		strokeButton2.setToolTipText("粗线");
		strokeButton2.setIcon(new ImageIcon("images/2像素线条.png"));
		toolBar.add(strokeButton2);

		strokeButton3 = new JToggleButton();
		strokeButton3.setToolTipText("较粗");
		strokeButton3.setIcon(new ImageIcon("images/4像素线条.png"));
		ButtonGroup strokeGroup = new ButtonGroup();// 画笔粗细按钮组，保证同时只有�?个按钮被选中
		strokeGroup.add(strokeButton1);
		strokeGroup.add(strokeButton2);
		strokeGroup.add(strokeButton3);
		toolBar.add(strokeButton3);
		toolBar.addSeparator();// 工具栏添加分割条

		backgroundButton = new JButton();
		backgroundButton.setToolTipText("背景颜色");
		backgroundButton.setIcon(new ImageIcon("images/背景色.png"));
		toolBar.add(backgroundButton);

		foregroundButton = new JButton();
		foregroundButton.setToolTipText("前景颜色");
		foregroundButton.setIcon(new ImageIcon("images/前景色.png"));
		toolBar.add(foregroundButton);
		toolBar.addSeparator();

		shapeButton = new JButton();
		shapeButton.setToolTipText("图形");
		shapeButton.setIcon(new ImageIcon("images/形状.png"));
		toolBar.add(shapeButton);

		clearButton = new JButton();
		clearButton.setToolTipText("清除");
		clearButton.setIcon(new ImageIcon("images/清除.png"));
		toolBar.add(clearButton);

		eraserButton = new JButton();
		eraserButton.setToolTipText("橡皮");
		eraserButton.setIcon(new ImageIcon("images/橡皮.png"));
		toolBar.add(eraserButton);
		JMenuBar menuBar = new JMenuBar();// 创建菜单�?
		this.setJMenuBar(menuBar);

		JMenu systemMenu = new JMenu("系统");// 创建菜单
		menuBar.add(systemMenu);
//		shuiyinMenuItem = new JMenuItem("设置水印");
//		systemMenu.add(shuiyinMenuItem);
//		systemMenu.addSeparator();
		saveMenuItem = new JMenuItem("保存");
		systemMenu.add(saveMenuItem);
		systemMenu.addSeparator();// 设置分割�?
		exitMenuItem = new JMenuItem("退出");
		systemMenu.add(exitMenuItem);

		//JMenu strokeMenu = new JMenu("线型");
		//menuBar.add(strokeMenu);
//		strokeMenuItem1 = new JMenuItem("细线");
//		strokeMenu.add(strokeMenuItem1);
//		strokeMenuItem2 = new JMenuItem("粗线");
//		strokeMenu.add(strokeMenuItem2);
//		strokeMenuItem3 = new JMenuItem("较粗");
//		strokeMenu.add(strokeMenuItem3);

//		JMenu colorMenu = new JMenu("颜色");
//		menuBar.add(colorMenu);
//		foregroundMenuItem = new JMenuItem("前景�?");
//		colorMenu.add(foregroundMenuItem);
//		backgroundMenuItem = new JMenuItem("背景�?");
//		colorMenu.add(backgroundMenuItem);

//		JMenu editMenu = new JMenu("编辑");
//		menuBar.add(editMenu);
//		clearMenuItem = new JMenuItem("清楚");
//		editMenu.add(clearMenuItem);
//		eraserMenuItem = new JMenuItem("橡皮");
//		editMenu.add(eraserMenuItem);

		//picWindow = new PictureWindow(DrawPictureFrame.this);
	}

	private void registerListener()
	{
		// 画板添加鼠标移动事件监听
		canvas.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(final MouseEvent e)
			{// 当鼠标移动时
				if (rubber)
				{
					if (x > 0 && y > 0)// 如果x，y存在鼠标记录
					{
						g.setColor(backgroundColor);
						g.fillRect(x, y, 10, 10);
					}
					x = e.getX();// 获取鼠标在画布上的坐�?
					y = e.getY();
				} else
				{
					if (x > 0 && y > 0)
					{
						// 在鼠标划过的位置画直�?
						g.drawLine(x, y, e.getX(), e.getY());
					}
					x = e.getX();// 上一次鼠标绘制点的坐�?
					y = e.getY();
				}
				canvas.repaint();
			}

			// 当鼠标移动时
			public void mouseMoved(final MouseEvent arg0)
			{
				if (rubber)
				{
					// 设置鼠标指针的形状为图片
					Toolkit kit = Toolkit.getDefaultToolkit();// 获得系统默认的工具包
					Image img = kit.createImage("images/鼠标橡皮.png");// 利用工具包获取图�?
					// 利用工具包创建一个自定义的光标对�?
					// 参数为图片，光标热点（写�?0,0就行）和光标描述字符�?
					Cursor c = kit.createCustomCursor(img, new Point(0, 0), "clear");
					setCursor(c);// 使用自定义光�?
				} else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				}
			}
		});

		canvas.addMouseListener(new MouseAdapter()
		{// 画板添加鼠标单击事件
			public void mouseReleased(final MouseEvent arg0)
			{// 当按键抬起时
				x = -1;
				y = -1;
			}

			public void mousePressed(MouseEvent e)
			{// 当按键按下时
				if (drawShape)
				{// 如果此时鼠标画的是图�?
					switch (shape.getType())
					{
					case Shapes.YUAN:
						int yuanX = e.getX() - shape.getWidth() / 2;
						int yuanY = e.getY() - shape.getHeigth() / 2;
						Ellipse2D yuan = new Ellipse2D.Double(yuanX, yuanY, shape.getWidth(), shape.getHeigth());
						g.draw(yuan);
						break;
					case Shapes.FANG:
						int fangX = e.getX() - shape.getWidth() / 2;
						int fangY = e.getY() - shape.getHeigth() / 2;
						Rectangle2D fang = new Rectangle2D.Double(fangX, fangY, shape.getWidth(), shape.getHeigth());
						g.draw(fang);
						break;
					}
					canvas.repaint();
					// 画图形标识变量为false�? 说明现在鼠标画的是图形，而不是线�?
					drawShape = false;
				}

			}
		});

		// 细线按钮添加动作监听
		strokeButton1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				// 声明画笔的属性，粗细�?1像素，线条末端无修饰，折线处呈尖�?
				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);// 画图工具使用此画�?
			}
		});

		// 粗线按钮添加动作监听
		strokeButton2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		// 较粗按钮添加动作监听
		strokeButton3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g.setStroke(bs);
			}
		});

		// 背景颜色按钮添加了动作事件监�?
		backgroundButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				// 单机时打�?选择颜色对话框，参数依次�?:父窗体，标题，默认�?�中的颜�?(青色)
				Color bgColor = JColorChooser.showDialog(DrawPictureFrame.this, "选择颜色对话�?", Color.CYAN);

				if (bgColor != null)
				{
					backgroundColor = bgColor;
				}
				// 背景色按钮也要更换为这种背景颜色
				backgroundButton.setBackground(backgroundColor);
				g.setColor(backgroundColor);// 使绘图工具使用背景色
				g.fillRect(0, 0, 570, 390);// 画一个背景颜色的方形填满整个画布
				g.setColor(foreColor);// 绘图工具使用前景�?
				canvas.repaint();// 更新画布
			}
		});

		// 前景色按钮添加了动作事件监听
		foregroundButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				Color fColor = JColorChooser.showDialog(DrawPictureFrame.this, "选择颜色对话框", Color.CYAN);

				if (fColor != null)
				{
					foreColor = fColor;
				}

				foregroundButton.setBackground(foreColor);
				g.setColor(foreColor);
			}
		});

		// 清除按钮添加动作监听
		clearButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				g.setColor(backgroundColor);
				g.fillRect(0, 0, 570, 390);
				g.setColor(foreColor);
				canvas.repaint();
			}
		});

		// 橡皮按钮添加动作监听
		eraserButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				if (rubber)
				{
					eraserButton.setToolTipText("橡皮");
					eraserButton.setIcon(new ImageIcon("images/橡皮.png"));
					g.setColor(foreColor);
					rubber = false;
				} else
				{
					eraserButton.setToolTipText("画图");
					eraserButton.setIcon(new ImageIcon("images/橡皮.png"));
					g.setColor(backgroundColor);
					rubber = true;
				}
			}
		});

		// 图形按钮添加动作监听事件
		shapeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				// 创建图形选择组件
				ShapeWindow shapeWindow = new ShapeWindow(DrawPictureFrame.this);
				int shapeButtonWidth = shapeButton.getWidth();// 获取图形按钮的宽�?
				int shapeWindowWidth = shapeWindow.getWidth();// 获取图形按钮的高�?
				int shapeButtonX = shapeButton.getX();// 获取图形按钮的横坐标
				int shapeButtonY = shapeButton.getY();// 获取图形按钮的纵坐标
				// 计算图形组件横坐标， 让组件与“图形�?�按钮居中对�?
				int shapeWindowX = getX() + shapeButtonX - ((shapeWindowWidth - shapeButtonWidth) / 2);
				// 计算机图形纵坐标，让组件显示在�?�图形�?�按钮下�?
				int shapeWindowY = getY() + shapeButtonY + 100;
				shapeWindow.setLocation(shapeWindowX, shapeWindowY);
				shapeWindow.setVisible(true);
			}
		});

		// 保存按钮添加动作监听事件
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)// 鼠标单击�?
			{
				addWatermark();// 添加水印
				DrawImageUtil.saveImage(DrawPictureFrame.this, image);// 打印图片
			}
		});

		// �?出菜单栏添加动作监听
		exitMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				System.exit(0);
			}
		});

		// 橡皮菜单栏添加动作监�?
//		eraserMenuItem.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				if (rubber)
//				{
//					eraserButton.setToolTipText("橡皮");
//					eraserButton.setIcon(new ImageIcon("images/橡皮.png"));
//					eraserMenuItem.setText("橡皮");
//					g.setColor(foreColor);
//					rubber = false;
//				} else
//				{
//					eraserMenuItem.setText("画图");
//					eraserButton.setToolTipText("画图");
//					eraserButton.setIcon(new ImageIcon("images/橡皮.png"));
//					g.setColor(backgroundColor);
//					rubber = true;
//				}
//			}
//		});

//		// 清楚菜单添加监听事件
//		clearMenuItem.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				g.setColor(backgroundColor);
//				g.fillRect(0, 0, 570, 390);
//				g.setColor(foreColor);
//				canvas.repaint();
//			}
//		});

		// “细线�?�菜单添加监听事�?
//		strokeMenuItem1.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
//				g.setStroke(bs);
//				strokeButton1.setSelected(true);// 让这个按钮处于被选中的状�?
//			}
//		});

//		// "粗线"菜单添加监听事件
//		strokeMenuItem2.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
//				g.setStroke(bs);
//				strokeButton2.setSelected(true);
//			}
//		});
//
//		// "较粗"菜单添加监听事件
//		strokeMenuItem3.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
//				g.setStroke(bs);
//				strokeButton3.setSelected(true);
//			}
//		});

//		// 前景色菜单添加监听事�?
//		foregroundMenuItem.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				Color fColor = JColorChooser.showDialog(DrawPictureFrame.this, "选择颜色对话�?", Color.CYAN);
//				if (fColor != null)
//				{
//					foreColor = fColor;
//				}
//				foregroundButton.setBackground(foreColor);
//				g.setColor(foreColor);// 画笔的颜�?
//			}
//		});
//
//		// 背景色菜单添加监听事�?
//		backgroundMenuItem.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				Color bColor = JColorChooser.showDialog(DrawPictureFrame.this, "选择颜色对话�?", Color.CYAN);
//				if (bColor != null)
//				{
//					backgroundColor = bColor;
//				}
//				backgroundButton.setBackground(backgroundColor);
//				g.setColor(backgroundColor);
//				g.fillRect(0, 0, 570, 390);
//				g.setColor(foreColor);
//				canvas.repaint();
//			}
//		});

		// 保存菜单添加监听事件
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				addWatermark();
				DrawImageUtil.saveImage(DrawPictureFrame.this, image);
			}
		});

//		// 水印菜单添加监听事件
//		shuiyinMenuItem.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				shuiyin = JOptionPane.showInputDialog(DrawPictureFrame.this, "你想添加�?么水印？");
//
//				if (shuiyin == null)
//				{
//					shuiyin = "";
//				} else
//				{
//					setTitle("�?起来画画(水印内容�? " + shuiyin + "�?)");
//				}
//			}
//		});

		// 工具栏添加鼠标移动监�?
		toolBar.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(final MouseEvent e)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));// 设置鼠标指针的形状为默认
			}
		});

//		// 展示�?笔画按钮添加动作监听事件
//		showPicButton.addActionListener(new ActionListener()
//		{
//			@Override
//			public void actionPerformed(final ActionEvent e)
//			{
//				boolean isVisible = picWindow.isVisible();
//
//				if (isVisible) // 如果�?笔画窗体是可见的
//				{
//					showPicButton.setToolTipText("展开�?笔画");
//					picWindow.setVisible(false);
//				} else
//				{
//					showPicButton.setToolTipText("隐藏�?笔画");
//					// 重新指定�?笔画展开窗体的显示位�?
//					// 横坐�? = 主窗体横坐标 - �?笔画窗体宽度 - 5
//					// 纵坐�? = 主窗体纵坐标 - �?笔画窗体纵坐�?
//					picWindow.setLocation(getX() - picWindow.getWidth() - 5, getY());
//					picWindow.setVisible(true);
//				}
//			}
//		});

	}

	/**
	 * FrameGetShape接口实现类，用于获得图形空间返回的被选中的图�?
	 * 
	 * @param shape
	 */
	public void getShape(Shapes shape)
	{
		this.shape = shape;
		drawShape = true;
	}

	/**
	 * 添加水印
	 */
	private void addWatermark()
	{
		if (!"".equals(shuiyin.trim()))// 如果水印字段不是空字符串
		{
			g.rotate(Math.toRadians(-30));// 将图片旋�?-30弧度
			Font font = new Font("楷体", Font.BOLD, 72);
			g.setFont(font);
			g.setColor(Color.GRAY);
			AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 设置透明效果
			g.setComposite(alpha);// 使用透明效果
			g.drawString(shuiyin, 100, 500);
			canvas.repaint();
			g.rotate(Math.toRadians(30));
			alpha = AlphaComposite.SrcOver.derive(1f);
			g.setComposite(alpha);
			g.setColor(foreColor);
		}
	}
}