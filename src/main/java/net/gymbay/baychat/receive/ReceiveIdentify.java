package net.gymbay.baychat.receive;

import io.jsonwebtoken.Jwts;
import net.gymbay.baychat.Gateway;
import net.gymbay.baychat.manager.ClientHandler;
import net.gymbay.baychat.manager.ClientOperation;
import net.gymbay.baychat.manager.ReceiveHandler;
import net.gymbay.baychat.util.ClientSession;
import net.gymbay.baychat.util.CloseCode;
import org.json.JSONObject;

public class ReceiveIdentify implements ReceiveHandler {


    @Override
    public void handleReceive(ClientSession session, JSONObject message) {

        if (!message.has("d")) {
            ClientHandler.closeSession(session.getSession(), CloseCode.WRONG_FORMAT);
            return;
        }

        if (!message.getJSONObject("d").has("token")) {
            ClientHandler.closeSession(session.getSession(), CloseCode.WRONG_FORMAT);
            return;
        }

        if (!message.getJSONObject("d").has("mail")) {
            ClientHandler.closeSession(session.getSession(), CloseCode.WRONG_FORMAT);
            return;
        }

        var isTokenCorrect = Jwts.parserBuilder()
                .setSigningKey(Gateway.SECRETE_TOKEN)
                .build().parseClaimsJws(message.getJSONObject("d").getString("token"))
                .getBody().getSubject().equals(message.getJSONObject("d").getString("mail"));

        if (!isTokenCorrect) {
            ClientHandler.closeSession(session.getSession(), CloseCode.AUTHORIZATION_FAILED);
            return;
        }


        session.setAuthenticated();
        ClientOperation.IDENTIFY.getSendHandler().handleSend(session);

    }
}
