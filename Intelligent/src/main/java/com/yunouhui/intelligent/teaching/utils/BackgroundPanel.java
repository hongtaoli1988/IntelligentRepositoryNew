package com.yunouhui.intelligent.teaching.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public class BackgroundPanel
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Image image;
  
  public BackgroundPanel(Image image)
  {
    this.image = image;
  }
  
  public BackgroundPanel() {}
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    if (this.image != null)
    {
      int width = getWidth();
      int height = getHeight();
      
      g2.drawImage(this.image, 0, 0, width, height, this);
    }
  }
  
  public void setImage(Image image)
  {
    this.image = image;
    repaint();
  }
}

