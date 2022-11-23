package net.gymbay.baychat;

import jakarta.websocket.DeploymentException;
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
        map.put(GrizzlyClientProperties.SELECTOR_THREAD_POOL_CONFIG, ThreadPoolConfig.defaultConfig().setCorePoolSize(0));
        map.put(GrizzlyClientProperties.WORKER_THREAD_POOL_CONFIG, ThreadPoolConfig.defaultConfig().setCorePoolSize(0));

        //create websocket server
        var server = new Server("BayChat", 8025, "/client", map);
        server.start();

        //stop ws server on force quit or crash
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        while (true);
    }

}
