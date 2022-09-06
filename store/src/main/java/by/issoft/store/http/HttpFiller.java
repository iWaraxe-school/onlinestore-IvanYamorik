package by.issoft.store.http;

import by.issoft.Category.Category;
import by.issoft.store.Filler;
import by.issoft.store.Store;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.List;

public class HttpFiller implements Filler {
    Store store;

    @Override
    public List<Category> getListOfCategories() throws IOException, URISyntaxException, InterruptedException {
        HttpURLConnection connection = new HttpClient().getConnection("/categories", "GET");
        String contentType = connection.getHeaderField("Content-type");
        if (contentType.equals("application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            try{
                return mapper.readValue(connection.getInputStream(), new TypeReference<List<Category>>() {});
            } catch (IOException e) {
                throw new RuntimeException("Error to get categories", e);}
            } else {
                System.out.println("Unexpected content-type");
            }
        connection.disconnect();
        return null;
    }

    public HttpFiller() throws URISyntaxException, IOException, InterruptedException {
    }

}
