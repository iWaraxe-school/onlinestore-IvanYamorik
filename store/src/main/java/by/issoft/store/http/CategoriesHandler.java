package by.issoft.store.http;

import by.issoft.Category.Category;
import by.issoft.store.RandomStorePopulator;
import com.github.javafaker.Faker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class CategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        RandomStorePopulator populator = new RandomStorePopulator(new Faker());
        List<Category> categories =

    }
}
