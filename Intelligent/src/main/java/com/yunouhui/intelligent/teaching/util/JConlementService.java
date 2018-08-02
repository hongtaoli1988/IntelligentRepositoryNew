package com.yunouhui.intelligent.teaching.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class JConlementService {
	public static ConcurrentMap<String, ConcurrentMap<String, Object>> handlerMap = new ConcurrentHashMap<String, ConcurrentMap<String, Object>>(20);
	//录制屏幕命令
	public String getScreenCom(String ffmpeg_path,String filePath)
    {
		File file = new File(filePath);
        if (!file.exists()) {
        	file.mkdir();
        }
        // -i：输入流地址或者文件绝对地址
        StringBuilder comm = new StringBuilder();
        //一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数
        comm.append(ffmpeg_path).append(" -f gdigrab -i desktop -f h264 -qp 20 ").append(filePath).append("000000.avi");
        System.out.println(comm.toString());
        return comm.toString();
    }
	//录制音频命令
	public String getVideoCom(String ffmpeg_path,String filePath)
    {
		File file = new File(filePath);
        if (!file.exists()) {
        	file.mkdir();
        }
        // -i：输入流地址或者文件绝对地址
        StringBuilder comm = new StringBuilder();
        //一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数
        comm.append(ffmpeg_path).append(" -f dshow -i audio="+"virtual-audio-capturer"+" -acodec libmp3lame ").append(filePath).append("000000.mp3");
        System.out.println(comm.toString());
        return comm.toString();
    }
	//视频音频合成命令
	public String getScreenAndVideoCom(String filePath,String ffmpeg_path,String ScreenPath,String VideoPath)
    {
		File file = new File(filePath);
        if (!file.exists()) {
        	file.mkdir();
        }
        // -i：输入流地址或者文件绝对地址
        StringBuilder comm = new StringBuilder();
        //一个视频源，可以有多个输出，第二个输出为拷贝源视频输出，不改变视频的各项参数
        comm.append(ffmpeg_path).append(" -i "+ScreenPath+" -i "+VideoPath+" ").append(filePath).append("outResult.avi");
        System.out.println(comm.toString());
        return comm.toString();
    }	
	//执行命令
	public ConcurrentMap<String, Object> push(String str){
		ConcurrentMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		 // 执行命令行
        Process proc;
		try {
			proc = Runtime.getRuntime().exec(str);
	        System.out.println("执行命令----start commond");
	        OutHandler errorGobbler = new OutHandler(proc.getErrorStream(), "Error");
	        OutHandler outputGobbler = new OutHandler(proc.getInputStream(), "Info");
	        errorGobbler.start();
	        outputGobbler.start();
			resultMap.put("info", outputGobbler);
	        resultMap.put("error", errorGobbler);
	        resultMap.put("process", proc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	//销毁
	public void removePush(String pushId) {
        if (handlerMap.containsKey(pushId))
        {
            ConcurrentMap<String, Object> map = handlerMap.get(pushId);
            //关闭两个线程
            ((OutHandler)map.get("error")).destroy();
            ((OutHandler)map.get("info")).destroy();
            
            System.out.println("停止命令-----end commond");
            //关闭命令主进程
            ((Process)map.get("process")).destroy();
            handlerMap.remove(pushId);
        }
    }
	//返回ffmpeg.exe地址
	public String getFfmpegExeResources(){
    	String is = this.getClass().getResource("/ffmpeg.exe").getPath();
        return is;
    }
	//POWERPNT.EXE
	public static void open(String fileName) {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			p = r.exec("cmd /c start " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
