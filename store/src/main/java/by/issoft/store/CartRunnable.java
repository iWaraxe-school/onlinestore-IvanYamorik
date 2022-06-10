package by.issoft.store;

import by.issoft.Product;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class CartRunnable implements Runnable {
    private CopyOnWriteArrayList<Product> cart;
    private List<Product> readOnlyProductList;



    @SneakyThrows
    @Override
    public void run() {
        System.out.println("New Thread");
        System.out.println(String.format("createOrder started Thread %s", Thread.currentThread().getName()));

        int threadTime = new Random().nextInt(30);

        System.out.println(String.format("Sleeping for " + threadTime));
        TimeUnit.SECONDS.sleep(threadTime);

        cart.add(readOnlyProductList.get(0));
        cart.add(readOnlyProductList.get(1));
        cart.add(readOnlyProductList.get(2));

        System.out.println(cart.toString());

        System.out.println(String.format("createOrder finished Thread %s", Thread.currentThread().getName()));


    }
    public CartRunnable(CopyOnWriteArrayList<Product> cart, List<Product> allProducts){
        this.cart = cart;
        this.readOnlyProductList = allProducts;
    };
}
