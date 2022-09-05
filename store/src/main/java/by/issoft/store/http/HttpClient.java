package by.issoft.store.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class HttpClient {
    java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
    HttpURLConnection connection;

    public HttpURLConnection getConnection(String file, String method) {
        try {
            URL address = new URL("http", "localhost", 8080, file);
            String encoding = Base64.getEncoder().encodeToString(("user:password").getBytes("UTF-8"));
            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error to get connection", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("Error to get connection", e);
        }
        return connection;
    }

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class.getName());

    public HttpClient() throws IOException, InterruptedException, URISyntaxException {
    }

    //authentication
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
    //logger.info("Status using headers: {}", response.statusCode());
}
