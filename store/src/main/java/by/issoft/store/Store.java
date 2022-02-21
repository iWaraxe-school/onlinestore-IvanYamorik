package by.issoft.store;

import by.issoft.Category.Category;
import by.issoft.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public List<Category> categoryList;

    public Store(){
        categoryList = new ArrayList<>();

        Category bike = new Category("Bike");
        Product firstBikeProduct = new Product("Honda", 1, 5000);
        Product secondBikeProduct = new Product("bmw", 1, 20_000);
        bike.addProduct(firstBikeProduct);
        bike.addProduct(secondBikeProduct);

        Category phone = new Category("Phone");
        Product firstPhoneProduct = new Product("ios", 1, 500);
        phone.addProduct(firstPhoneProduct);
        Category milk = new Category("Milk");


        categoryList.add(bike);
        categoryList.add(phone);
        categoryList.add(milk);


    }

    @Override
    public String toString() {
        String resultCategory = "";
        for (int index = 0; index < categoryList.size(); index++) {
            Category currentCategory = categoryList.get(index);
            resultCategory = resultCategory + currentCategory.toString() + "\n";
        }
        return resultCategory;
    }
}

