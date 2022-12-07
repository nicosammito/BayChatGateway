package net.gymbay.baychat.util;

import jakarta.websocket.Session;

public class ClientSession {

    private byte sadHello = 0;
    private byte isAuthenticated = 0;
    private final Session session;
    public ClientSession(Session session){
        this.session = session;
    }
    public boolean sadHello() {
        return this.sadHello != 0;
    }

    public void setHello() {
        this.sadHello = 1;
    }

    public void setAuthenticated() {
        this.isAuthenticated = 1;
    }

    public boolean isAuthenticated() {
        return isAuthenticated != 0;
    }

    public Session getSession() {
        return this.session;
    }

    public void send(String text) {
        var future = this.getSession().getAsyncRemote().sendText(text);
        if (future.isCancelled()) this.send(text);
     }


}
