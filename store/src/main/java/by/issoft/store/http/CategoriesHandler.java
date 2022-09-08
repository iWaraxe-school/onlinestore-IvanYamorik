package by.issoft.store.http;

import by.issoft.Category.Category;
import by.issoft.store.InMemoryStoreFiller;
import com.github.lbovolini.mapper.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CategoriesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InMemoryStoreFiller msf = new InMemoryStoreFiller();
        List<Category> categories = msf.getListOfCategories();
        ObjectMapper mapper = new ObjectMapper();
        byte[] jsonInBytes = mapper.writeValueAsBytes(categories);
        Headers headers = httpExchange.getResponceHeaders();
        headers.add("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, jsonInBytes.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(jsonInBytes);
        os.close();

    }
}
