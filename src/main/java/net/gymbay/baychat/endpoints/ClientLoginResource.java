package net.gymbay.baychat.endpoints;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.gymbay.baychat.util.provider.ClientLogin;
import org.json.JSONObject;

@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientLoginResource extends Application {

    private static final String SECRETE_TOKEN = "";

    @POST
    public Response postLogin(ClientLogin clientLogin) {

        if (clientLogin.getEmail() == null) {
            return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "email is missing").append("t", "error").toString()).build();
        }

        if (clientLogin.getPassword() == null) {
            return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "password is missing").append("t", "error").toString()).build();
        }

        var token = Jwts.builder()
                .setSubject(clientLogin.getEmail())
                .compact();

        //TODO: Db connection
        if (!clientLogin.getEmail().equals("admin@admin.com") && !clientLogin.getPassword().equals("admin"))
            return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "email or password is wrong").append("t", "error").toString()).build();

        return Response.ok().build();
    }

}
