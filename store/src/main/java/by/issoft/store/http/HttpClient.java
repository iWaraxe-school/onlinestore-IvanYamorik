package by.issoft.store.http;

import by.issoft.Category.Category;
import com.github.lbovolini.mapper.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

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
            throw new RuntimeException("Error to get connection. URL problem", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error to get connection. Encoding problem", e);
        } catch (IOException e) {
            throw new RuntimeException("Error to get connection", e);
        }
        return connection;
    }

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class.getName());

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


    public void addToCart(List<Category> categories, int categoryId, int productId) {
        connection = getConnection("/cart", "POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        try {
            OutputStream os = connection.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            byte[] postData = mapper.writeValueAsBytes(categories.get(categoryId).getProductList().get(productId));
            os.write(postData);
        } catch (IOException e) {
            throw new RuntimeException("Error to add to cart", e);
        }
        try
    }


}
