package by.issoft.store.http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public void createServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/categories", new CategoriesHandler());
            server.createContext("/cart", new CartHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Server started");

        } catch (IOException e) {
            throw new RuntimeException("Error to build server", e);
        }
    }
}
