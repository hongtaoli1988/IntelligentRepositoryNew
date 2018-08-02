package com.yunpuhui.Intelligent.app;

import java.util.concurrent.ConcurrentMap;

import com.yunouhui.intelligent.teaching.util.JConlementService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	JConlementService app = new JConlementService();
    	String ffmpegExeResources = app.getFfmpegExeResources();
    	String screenCom = app.getScreenCom(ffmpegExeResources,"D:\\lihongtaoScreen\\");
    	ConcurrentMap<String, Object> push = app.push(screenCom);
    	app.handlerMap.put("1224564", push);
    	try {
			Thread.sleep(10*1000);
			System.out.println("准备销毁。。。。。");
			app.removePush("1224564");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
