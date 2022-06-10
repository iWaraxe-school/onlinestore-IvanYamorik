package by.issoft.store;

import by.issoft.Category.Category;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;


    private static Store uniqueInstance;

    private Store(){
        categoryList = new ArrayList<>();
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

