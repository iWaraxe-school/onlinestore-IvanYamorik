package by.issoft.store;

import by.issoft.Category.Category;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface Filler {
    List<Category> getListOfCategories() throws IOException, URISyntaxException, InterruptedException;
}
