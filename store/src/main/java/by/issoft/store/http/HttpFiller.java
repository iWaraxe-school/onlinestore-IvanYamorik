package by.issoft.store.http;

import by.issoft.store.Store;
import by.issoft.store.StoreFiller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class HttpFiller implements StoreFiller {
    Store store;

    public HttpFiller() throws URISyntaxException, IOException, InterruptedException {
    }



    @Override
    public void fillStoreRandomly() throws SQLException {

    }
}
