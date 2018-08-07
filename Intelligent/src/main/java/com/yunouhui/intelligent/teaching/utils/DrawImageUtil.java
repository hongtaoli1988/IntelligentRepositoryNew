package com.yunouhui.intelligent.teaching.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class DrawImageUtil
{
  public static void saveImage(JFrame frame, BufferedImage saveImage)
  {
    JFileChooser jfc = new JFileChooser();
    jfc.setDialogTitle("保存图片");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", new String[] {
      "jpg" });
    jfc.setFileFilter(filter);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
    String fileName = sdf.format(new Date());
    FileSystemView view = FileSystemView.getFileSystemView();
    File filePath = view.getHomeDirectory();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    //jfc.setSelectedFile(saveFile);
    int flag = jfc.showSaveDialog(null);
    if (flag == 0) {
      try
      {
    	// 获取用户选择的文件夹
    	filePath = jfc.getSelectedFile();
    	if(!filePath.getPath().endsWith(".jpg") && !filePath.getPath().endsWith(".JPG")){
    		JOptionPane.showMessageDialog(frame, "文件格式错误,无法保存！", "错误",JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	File file = new File(filePath.getParent());
    	// 判断文件夹是否存在
		if (!file.exists()) {
			file.mkdirs();
		}

        ImageIO.write(saveImage, "jpg", filePath);
      }
      catch (Exception e)
      {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "文件无法保存！", "错误",JOptionPane.WARNING_MESSAGE);
      }
    }
  }
}