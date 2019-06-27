package edson.MyTemplate.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 消息服务
 */
@Component
@ServerEndpoint("/websocket")
public class WebSocketService {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketService> webSockets=new CopyOnWriteArraySet<WebSocketService>();

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSockets.add(this);
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    /**
     * 发送消息给页面
     * @param message
     */
    public void sendMessage(String message){

        for(WebSocketService webSocketService:webSockets){
            try {
                webSocketService.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
