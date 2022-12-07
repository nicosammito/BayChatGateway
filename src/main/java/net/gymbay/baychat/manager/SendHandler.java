package net.gymbay.baychat.manager;

import net.gymbay.baychat.util.ClientSession;

public interface SendHandler {

    void handleSend(ClientSession session);

}
