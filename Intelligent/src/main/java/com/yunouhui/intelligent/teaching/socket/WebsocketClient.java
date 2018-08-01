package com.yunouhui.intelligent.teaching.socket;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
/**
 * Created by jack on 2017/10/25.
 */
public class WebsocketClient {
	private WebsocketClient(){}
    public static volatile WebSocketClient client;
    public static WebSocketClient getWebSocketClientInstance(){
    	 if(client==null){
             synchronized (WebsocketClient.class){
                 if(client==null){
                	 try {
                         	 client = new WebSocketClient(new URI("ws://localhost:8080/websocket"),new Draft_6455()) {
                        	 //client = new WebSocketClient(new URI("ws://yuzhi.site:7745/ws"),new Draft_6455()) {
                             @Override
                             public void onOpen(ServerHandshake serverHandshake) {
                                 System.out.println("打开链接");
                             }
                             @Override
                             public void onMessage(String s) {
                                 System.out.println("客户端收到消息"+s);
                             }
                             @Override
                             public void onClose(int i, String s, boolean b) {
                                 System.out.println("链接已关闭");
                             }
                             @Override
                             public void onError(Exception e) {
                                 e.printStackTrace();
                                 System.out.println("发生错误已关闭");
                             }
                         };
                     } catch (URISyntaxException e) {
                         e.printStackTrace();
                     }
                     client.connect();
                 }
             }
         }
         return client;
    }
    public static void main(String[] args) {
    	WebSocketClient webSocketClientInstance = WebsocketClient.getWebSocketClientInstance();
    }
    public static void send(byte[] bytes){
        client.send(bytes);
    }
}
