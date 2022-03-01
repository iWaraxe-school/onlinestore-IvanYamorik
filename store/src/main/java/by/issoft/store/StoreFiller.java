package by.issoft.store;

import by.issoft.Category.Category;
import by.issoft.Product;

import java.util.Map;

public class StoreFiller {
    Store store;

    public StoreFiller (Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {

        RandomStorePopulator populator = new RandomStorePopulator();
        Map<Category, Integer> categoryProductsMapToAdd = createProductListToAdd();

        for (Map.Entry<Category, Integer> entry : categoryProductsMapToAdd.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populator.getProductName(entry.getKey().name),
                        populator.getRate(),
                        populator.getPrice());
                entry.getKey().addProduct(product);
            }
            this.store.categoryList.add(entry.getKey());
        }
    }

    private static Map<Category, Integer> createProductListToAdd() {};
}
