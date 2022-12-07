package net.gymbay.baychat;

import jakarta.websocket.DeploymentException;
import jakarta.ws.rs.SeBootstrap;
import net.gymbay.baychat.endpoints.ClientAPI;
import net.gymbay.baychat.endpoints.ClientEndpoint;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.container.grizzly.client.GrizzlyClientProperties;
import org.glassfish.tyrus.server.Server;

import java.util.HashMap;
import java.util.Map;

public class Gateway {

    public static void main(String[] args) throws DeploymentException {
        Map<String, Object> map = new HashMap<>();

        //Thread Pool Configuration
        map.put(ClientProperties.SHARED_CONTAINER, true);
        map.put(GrizzlyClientProperties.SELECTOR_THREAD_POOL_CONFIG, ThreadPoolConfig.defaultConfig().setCorePoolSize(1));
        map.put(GrizzlyClientProperties.WORKER_THREAD_POOL_CONFIG, ThreadPoolConfig.defaultConfig().setCorePoolSize(1));

        //create websocket server
        var server = new Server("localhost", 8025, "", map, ClientEndpoint.class);
        server.start();

        var configuration = SeBootstrap.Configuration.builder().host("localhost").port(8080).protocol("http").build();
        SeBootstrap.start(ClientAPI.class, configuration).thenAccept(instance -> {
            instance.stopOnShutdown(stopResult -> System.out.printf("Stopped container (%s)", stopResult.unwrap(Object.class)));
        });

        //stop ws server on force quit or crash
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        while (true);
    }

}
