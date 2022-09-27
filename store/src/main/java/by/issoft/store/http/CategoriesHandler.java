package by.issoft.store.http;

import by.issoft.Category.Category;
import by.issoft.store.InMemoryStoreFiller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

public class CategoriesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String authToken = httpExchange.getRequestHeaders().getFirst("Authorization");
        if (authToken != null && verifyAuth(authToken)) {
            InMemoryStoreFiller msf = new InMemoryStoreFiller();
            List<Category> categories = msf.getListOfCategories();
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonInBytes = mapper.writeValueAsBytes(categories);
            Headers headers = httpExchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, jsonInBytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(jsonInBytes);
            os.close();
        }
        else {
            byte[] response = "403 - Forbidden".getBytes();
            httpExchange.sendResponseHeaders(403, response.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response);
            os.close();
        }


    }

    private static final boolean verifyAuth(String token) {
        String extractedToken = token.substring(6);
        String userData = new String(Base64.getDecoder().decode(extractedToken));
        return ("user:password".equals(userData));
    }
}
