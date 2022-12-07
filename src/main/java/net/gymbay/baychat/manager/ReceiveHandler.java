package net.gymbay.baychat.manager;

import net.gymbay.baychat.util.ClientSession;

public interface ReceiveHandler {

    void handleReceive(ClientSession session);

}
