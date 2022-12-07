package net.gymbay.baychat.manager;

import jakarta.websocket.CloseReason;
import jakarta.websocket.Session;
import net.gymbay.baychat.util.ClientSession;
import net.gymbay.baychat.util.CloseCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ClientHandler {

    private static final Collection<ClientSession> moduleSessionList = Collections.synchronizedCollection(new ArrayList<>());

    public static ClientSession addModuleSession(Session session) {
        var clientSession = new ClientSession(session);
        moduleSessionList.add(clientSession);
        return clientSession;
    }

    public static void removeSession(Session session) {
        moduleSessionList.forEach(clientSession -> {
            if (clientSession.getSession() == session) moduleSessionList.remove(clientSession);
        });

        moduleSessionList.removeIf(clientSession -> clientSession.getSession() == session);

    }

    public static void closeSession(Session session, CloseCode closeCode) {
        try {
            if (session.isOpen()) {
                session.close(new CloseReason(closeCode, closeCode.name()));
            } else {
                removeSession(session);
            }
        } catch (IOException e) { /*TODO: handle exception*/}
    }


    public static ClientSession getClientSession(Session session){

        for(ClientSession clientSession : moduleSessionList){

            if(clientSession.getSession()== session){

              return clientSession;


            }

        }

        return null;

    }

}
