package by.issoft.store;

import by.issoft.Category.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryStoreFiller implements Filler {
    private Store store;

    public InMemoryStoreFiller() {}
    public InMemoryStoreFiller(Store store) {
        this.store = store;
    }

    @Override
    public List<Category> getListOfCategories() {
        List<Category> categories = new ArrayList<>();
        Set<Map.Entry<Category, Integer>> entries = StoreFiller.createCategoryToIntegerMap().entrySet();
        for (Map.Entry<Category, Integer> entry: entries) {
            categories.add(entry.getKey());
        }

        return categories;
    }
/**
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
    } */


}
