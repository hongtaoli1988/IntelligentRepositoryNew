package com.yunouhui.intelligent.teaching.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JWindow;

public class ShapeWindow
  extends JWindow
{
  private Shapes shapes;
  private FrameGetShape frame;
  
  public ShapeWindow(FrameGetShape frame)
  {
    this.frame = frame;
    init();
  }
  
  public ShapeWindow(int x, int y, FrameGetShape frame)
  {
    this.frame = frame;
    setLocation(x, y);
    init();
  }
  
  private void init()
  {
    setSize(200, 100);
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel();
    
    Ellipse2D e = new Ellipse2D.Double(0.0D, 0.0D, 14.0D, 14.0D);
    ShapeButton yuan = new ShapeButton(e);
    centerPanel.add(yuan);
    
    Rectangle2D r = new Rectangle2D.Double(0.0D, 0.0D, 14.0D, 14.0D);
    ShapeButton fang = new ShapeButton(r);
    centerPanel.add(fang);
    
    c.add(centerPanel, "Center");
    
    FlowLayout flow = new FlowLayout(2);
    southPanel.setLayout(flow);
    JButton cancel = new JButton("È¡Ïû");
    southPanel.add(cancel);
    cancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        ShapeWindow.this.dispose();
      }
    });
    c.add(southPanel, "South");
    pack();
  }
  
  class ShapeButton
    extends JPanel
  {
    public ShapeButton(final Shape shape)
    {
      setSize(20, 20);
      setLayout(new BorderLayout());
      
      BufferedImage img = new BufferedImage(15, 15, 
        4);
      Graphics2D g = img.createGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, img.getWidth(), img.getHeight());
      g.setColor(Color.BLACK);
      g.draw(shape);
      
      JButton btnNewButton = new JButton();
      btnNewButton.setIcon(new ImageIcon(img));
      JPanel p = new JPanel();
      p.add(btnNewButton);
      add(p, "Center");
      
      JPanel south = new JPanel();
      south.setLayout(new FlowLayout());
      
      final JSpinner spinnerLeft = new JSpinner();
      spinnerLeft.setValue(Integer.valueOf(50));
      south.add(new JLabel("¿í"));
      south.add(spinnerLeft);
      
      final JSpinner spinnerRigth = new JSpinner();
      spinnerRigth.setValue(Integer.valueOf(50));
      south.add(new JLabel("¸ß"));
      south.add(spinnerRigth);
      
      add(south, "South");
      
      btnNewButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          if ((shape instanceof Ellipse2D)) {
            ShapeWindow.this.shapes = new Shapes(25377, 
              ((Integer)spinnerLeft.getValue()).intValue(), ((Integer)spinnerRigth.getValue()).intValue());
          }
          if ((shape instanceof Rectangle2D)) {
            ShapeWindow.this.shapes = new Shapes(25637, 
              ((Integer)spinnerLeft.getValue()).intValue(), ((Integer)spinnerRigth.getValue()).intValue());
          }
          ShapeWindow.this.frame.getShape(ShapeWindow.this.shapes);
          ShapeWindow.this.dispose();
        }
      });
    }
  }
}

