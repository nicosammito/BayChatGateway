package net.gymbay.baychat.endpoints;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import net.gymbay.baychat.manager.ClientHandler;
import net.gymbay.baychat.manager.ClientOperation;

@ServerEndpoint("/client")
public class ClientEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        var clientSession = ClientHandler.addModuleSession(session);
        ClientOperation.HELLO.getReceiveHandler().handleReceive(clientSession);

    }


}
