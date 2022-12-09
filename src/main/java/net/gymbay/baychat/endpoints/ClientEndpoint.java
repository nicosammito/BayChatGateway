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
        session.setMaxIdleTimeout(1000*60);
        ClientOperation.HELLO.getReceiveHandler().handleReceive(clientSession, null);

    }

    @OnMessage
    public void onMessage(Session session, String message) {

        try {
            var json = new JSONObject(message);
            var clientSession = ClientHandler.getClientSession(session);

            if (clientSession == null) {
                ClientHandler.closeSession(session, CloseCode.INVALID_SESSION);
                return;
            }

            if (!json.has("op")) {
                ClientHandler.closeSession(session, CloseCode.UNKNOWN_OPERATION);
                return;
            }

            if (json.getInt("op") != ClientOperation.IDENTIFY.getOpcode() && !clientSession.isAuthenticated()) {
                ClientHandler.closeSession(session, CloseCode.NOT_AUTHENTICATED);
                return;
            }

            if (ClientOperation.getClientOperation(json.getInt("op")) == null) {
                ClientHandler.closeSession(session, CloseCode.UNKNOWN_OPERATION);
                return;
            }

            ClientOperation.getClientOperation(json.getInt("op")).getReceiveHandler().handleReceive(clientSession, json);

        } catch (JSONException exception) {
            ClientHandler.closeSession(session, CloseCode.WRONG_FORMAT);
        }


    }


}
