package by.issoft.store;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Base64;

public class HttpFiller implements StoreFiller {
    Store store;

    HttpClient client = HttpClient.newHttpClient();

    public HttpFiller() throws URISyntaxException, IOException, InterruptedException {
    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(new URI("https://postman-echo.com/basic-auth"))
            .header("Authorization", getBasicAuthenticationHeader("postman", "password"))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    @Override
    public void fillStoreRandomly() throws SQLException {

    }
}
