package com.example;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class App {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        System.out.println("Server started on port " + port);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            // Read APP_VERSION environment variable
            String version = System.getenv("APP_VERSION");
            if (version == null) version = "UNKNOWN version";
            String response = "Hello from " + version;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}


