package net.gymbay.baychat.receive;

import net.gymbay.baychat.manager.ClientHandler;
import net.gymbay.baychat.manager.ClientOperation;
import net.gymbay.baychat.manager.ReceiveHandler;
import net.gymbay.baychat.util.ClientSession;
import net.gymbay.baychat.util.CloseCode;

public class ReceiveHello implements ReceiveHandler {

    @Override
    public void handleReceive(ClientSession session, String message) {

        if (session.sadHello()) {
            ClientHandler.closeSession(session.getSession(), CloseCode.INVALID_SESSION);
            return;
        }

        if (session.isAuthenticated()) {
            ClientHandler.closeSession(session.getSession(),CloseCode.INVALID_SESSION);
            return;
        }


        ClientOperation.HELLO.getSendHandler().handleSend(session);

    }


}
