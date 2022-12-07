package net.gymbay.baychat.send;

import net.gymbay.baychat.manager.SendHandler;
import net.gymbay.baychat.util.ClientSession;
import org.json.JSONObject;

public class SendHello implements SendHandler {

    @Override
    public void handleSend(ClientSession session) {
        final var mainObject = new JSONObject();
        mainObject.append("op", 2);
        mainObject.append("d", "");
        mainObject.append("t", "HELLO");

        session.send(mainObject.toString());
    }
}
