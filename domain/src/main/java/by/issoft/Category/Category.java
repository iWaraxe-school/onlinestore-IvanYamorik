package by.issoft.Category;

import by.issoft.Product;

import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;
    public List<Product> productList;

    public Category(String name){

        this.name = name;
    }

    public void addProduct(Product product){
        if (productList == null) {
            productList = new ArrayList<>();
        }

        productList.add(product);
    }

    public void printProductsInfo() {
        System.out.println("Category " + name + " : " + productList);
    }

    @Override
    public String toString() {
        String resultString = "";
        if (productList == null) return "";
        for (int index = 0; index < productList.size(); index++){
            Product currentProduct = productList.get(index);
            resultString = resultString + currentProduct.toString() + " ";
        }
        return resultString;
    }
}

