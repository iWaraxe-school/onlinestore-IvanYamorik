package by.issoft.store;

import by.issoft.Category.BeerCategory;
import by.issoft.Category.Category;
import by.issoft.Category.FoodCategory;
import by.issoft.Category.MilkCategory;
import by.issoft.Product;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class StoreFiller {
    Store store;

    public StoreFiller (Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        Faker faker = new Faker();

        RandomStorePopulator populator = new RandomStorePopulator(faker);
        Map<Category, Integer> categoryIntegerMap = createCategoryToIntegerMap();

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

    private static Map<Category, Integer> createCategoryToIntegerMap() {
        Map<Category, Integer> newCategoryIntegerMap = new HashMap<>();
        newCategoryIntegerMap.put(new BeerCategory(), 10);
        newCategoryIntegerMap.put(new FoodCategory(), 10);
        newCategoryIntegerMap.put(new MilkCategory(), 10);
        return newCategoryIntegerMap;
    }
}
