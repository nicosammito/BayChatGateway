package net.gymbay.baychat.endpoints;

import io.jsonwebtoken.Jwts;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.gymbay.baychat.Gateway;
import net.gymbay.baychat.util.provider.ClientLogin;
import org.json.JSONObject;

@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientLoginResource extends Application {

    @POST
    public Response postLogin(ClientLogin clientLogin) {

        if (clientLogin.getEmail() == null) {
            return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "email is missing").append("t", "error").toString()).build();
        }

        if (clientLogin.getPassword() == null) {
            return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "password is missing").append("t", "error").toString()).build();
        }

        var token = Jwts.builder().setSubject(clientLogin.getEmail()).signWith(Gateway.SECRETE_TOKEN).compact();

        //TODO: Db connection
        //if (!clientLogin.getEmail().equals("admin@admin.com") && !clientLogin.getPassword().equals("admin"))
            //return Response.status(400).entity(new JSONObject().append("op", "400").append("d", "email or password is wrong").append("t", "error").toString()).build();

        return Response.status(200).entity(new JSONObject().append("op", "200").append("d", new JSONObject().append("token", token)).append("t", "success").toString()).build();
    }

}
