import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        // Set the port for the HTTP server
        int port = 8000;

        // Create the HTTP server and bind it to the port
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Define the context and its handler
        server.createContext("/", new RootHandler());

        // Start the server
        server.start();
        System.out.println("Server started on port " + port);
    }

    // Define a handler for the root context
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, World!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
