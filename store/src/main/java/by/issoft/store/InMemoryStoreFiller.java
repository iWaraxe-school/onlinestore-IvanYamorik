package by.issoft.store;

import by.issoft.Category.Category;
import by.issoft.Product;
import com.github.javafaker.Faker;

import java.util.Map;

public class InMemoryStoreFiller implements StoreFiller {
    Store store;

    public InMemoryStoreFiller(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        Faker faker = new Faker();

        RandomStorePopulator populator = new RandomStorePopulator(faker);
        Map<Category, Integer> categoryIntegerMap = StoreFiller.createCategoryToIntegerMap();

        for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populator.getProductName(entry.getKey().getName()),
                        populator.getRate(),
                        populator.getPrice());
                entry.getKey().addProduct(product);
            }
            this.store.getCategoryList().add(entry.getKey());
        }
    }


}
