package net.gymbay.baychat.util.provider;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ClientLoginProvider implements MessageBodyReader<ClientLogin> {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.valueOf(MediaType.APPLICATION_JSON));
    }

    @Override
    public ClientLogin readFrom(Class<ClientLogin> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws WebApplicationException {
        var text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));

        var json = new JSONObject(text);

        return new ClientLogin(json.has("email") ? json.get("email").toString() : null,
                json.has("password") ? json.get("password").toString() : null);
    }
}
