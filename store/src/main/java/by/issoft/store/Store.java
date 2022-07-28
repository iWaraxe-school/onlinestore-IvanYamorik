package by.issoft.store;

import by.issoft.Category.Category;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Store {
    private List<Category> categoryList;


    private static Store uniqueInstance;

    /* private Store(){
        categoryList = new ArrayList<>();
    } */

    private Store() {
        categoryList = Collections.synchronizedList(new ArrayList<>());

        List<Category> categories = new ArrayList<>();
        Reflections reflections = new Reflections("categories", new SubTypesScanner());
        //Get all existing subtypes of category class
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> type : subTypes) {
            try {
                Category category = type.getConstructor().newInstance();
                categories.add(category);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                    NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        this.categoryList = categories;
    }

    public static Store getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Store();

        return uniqueInstance;
    }

   public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public String toString() {

        StringBuilder storeStringBuilder = new StringBuilder();

        storeStringBuilder.append("Store contains the following categories and products: ");
        storeStringBuilder.append("\n");

        if (categoryList == null) {
            storeStringBuilder.append("Empty");
            return storeStringBuilder.toString();
        }

        for (int index = 0; index < categoryList.size(); index++){
            Category currentCategory = categoryList.get(index);

            storeStringBuilder.append(currentCategory);
            storeStringBuilder.append("\n");

        }
        return storeStringBuilder.toString();

    }

}

