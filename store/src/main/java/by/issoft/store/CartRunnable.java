package by.issoft.store;

import by.issoft.Product;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CartRunnable implements Runnable {
    private CopyOnWriteArrayList<Product> cart;
    private List<Product> readOnlyProductList;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("New Thread");


    }
    public CartRunnable(CopyOnWriteArrayList<Product> cart, List<Product> allProducts){
        this.cart = cart;
        this.readOnlyProductList = allProducts;
    };
}
