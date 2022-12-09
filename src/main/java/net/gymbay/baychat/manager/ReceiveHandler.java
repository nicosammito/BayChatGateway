package net.gymbay.baychat.manager;

import net.gymbay.baychat.util.ClientSession;
import org.json.JSONObject;

public interface ReceiveHandler {

    void handleReceive(ClientSession session, JSONObject message);

}
