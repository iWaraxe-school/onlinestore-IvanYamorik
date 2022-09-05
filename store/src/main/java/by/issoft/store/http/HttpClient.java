package by.issoft.store.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class HttpClient {
    java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public HttpClient() throws IOException, InterruptedException {
    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(new URI("https://localhost/basic-auth"))
            .header("Authorization", getBasicAuthenticationHeader("user", "password"))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    HttpHeaders responseHeaders = response.headers();
    logger.info("Status using headers: {}", response.statusCode());
}
