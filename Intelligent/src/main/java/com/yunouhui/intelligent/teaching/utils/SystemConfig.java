package com.yunouhui.intelligent.teaching.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;

public class SystemConfig {
	public static String getConfig(){  
		String sMAC = "";  
        try{  
            InetAddress address = InetAddress.getLocalHost();  
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
            //ni.getInetAddresses().nextElement().getAddress();  
            byte[] mac = ni.getHardwareAddress();  
            //String sIP = address.getHostAddress();  
            Formatter formatter = new Formatter();  
            for (int i = 0; i < mac.length; i++) {  
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],  
                        (i < mac.length - 1) ? "-" : "").toString();  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return sMAC;
    }  
	
	public static void main(String[] args) {
		String config = getConfig();
		System.out.println(config);
	}
}
