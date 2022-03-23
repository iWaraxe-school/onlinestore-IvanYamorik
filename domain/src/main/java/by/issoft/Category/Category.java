package by.issoft.Category;

import by.issoft.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private List<Product> productList;

    public Category(String name){

        this.categoryName = name;
    }

    public void addProduct(Product product){
        if (productList == null) {
            productList = new ArrayList<>();
        }

        productList.add(product);
    }

    public String getName() {
        return categoryName;
    }

    @Override
    public String toString() {

        StringBuilder categoryStringBuilder = new StringBuilder();

        categoryStringBuilder.append("Category: ");
        categoryStringBuilder.append(categoryName);
        categoryStringBuilder.append("\n");

        if (productList == null) {
            categoryStringBuilder.append("Empty ");
            return categoryStringBuilder.toString();
        }

        for (int index = 0; index < productList.size(); index++){
            Product currentProduct = productList.get(index);

            categoryStringBuilder.append(currentProduct);
            categoryStringBuilder.append("\n");

        }
        return categoryStringBuilder.toString();
    }

}

