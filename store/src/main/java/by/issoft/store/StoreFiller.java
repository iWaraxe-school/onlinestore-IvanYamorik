package by.issoft.store;

import by.issoft.Category.BeerCategory;
import by.issoft.Category.Category;
import by.issoft.Category.FoodCategory;
import by.issoft.Category.MilkCategory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface StoreFiller {
    void fillStoreRandomly() throws SQLException;
    public static Map<Category, Integer> createCategoryToIntegerMap() {
        Map<Category, Integer> newCategoryIntegerMap = new HashMap<>();
        newCategoryIntegerMap.put(new BeerCategory(), 10);
        newCategoryIntegerMap.put(new FoodCategory(), 10);
        newCategoryIntegerMap.put(new MilkCategory(), 10);
        return newCategoryIntegerMap;
    }
}
