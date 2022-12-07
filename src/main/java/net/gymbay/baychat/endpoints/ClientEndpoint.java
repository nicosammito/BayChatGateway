package net.gymbay.baychat.endpoints;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import net.gymbay.baychat.manager.ClientHandler;
import net.gymbay.baychat.manager.ClientOperation;
import net.gymbay.baychat.util.CloseCode;
import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/client")
public class ClientEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        var clientSession = ClientHandler.addModuleSession(session);
        ClientOperation.HELLO.getReceiveHandler().handleReceive(clientSession, null);

    }

    @OnMessage
    public void onMessage(Session session, String message){

        try{
            var json = new JSONObject(message);

        }catch(JSONException exception){
          ClientHandler.closeSession(session, CloseCode.WRONG_FORMAT);



        }



    }


}
