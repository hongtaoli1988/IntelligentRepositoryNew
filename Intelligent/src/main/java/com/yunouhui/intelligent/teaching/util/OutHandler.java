package com.yunouhui.intelligent.teaching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * 用于输出命令行主进程的消息线程（必须开启，否则命令行主进程无法正常执行） 重要：该类重写了destroy方法，用于安全的关闭该线程
 * 
 * @author eguid
 * @see OutHandler
 * @since jdk1.7
 */
 
public class OutHandler extends Thread
{
	private static final Logger logger = LoggerFactory.getLogger(OutHandler.class);  
    // 控制线程状态
    volatile boolean status = true;
 
    BufferedReader br = null;
 
    String type = null;
 
    public OutHandler(InputStream is, String type)
    {
        br = new BufferedReader(new InputStreamReader(is));
        this.type = type;
    }
 
    /**
     * 重写线程销毁方法，安全的关闭线程
     */
    @Override
    public void destroy()
    {
        status = false;
    }
 
    /**
     * 执行输出线程
     */
    @Override
    public void run()
    {
        String msg = null;
        try
        {
            while (status)
            {
 
                if ((msg = br.readLine()) != null)
                {
                	logger.info(type + "消息：" + msg);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
 
}
