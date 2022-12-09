package net.gymbay.baychat.send;

import net.gymbay.baychat.manager.ClientOperation;
import net.gymbay.baychat.manager.SendHandler;
import net.gymbay.baychat.util.ClientSession;
import org.json.JSONObject;

public class SendHeartbeat implements SendHandler {

    @Override
    public void handleSend(ClientSession session) {
        final var mainObject = new JSONObject();
        mainObject.append("op", ClientOperation.HEARTBEAT.getOpcode());
        mainObject.append("d", "");
        mainObject.append("t", "HEARTBEAT");

        session.send(mainObject.toString());
    }

}
