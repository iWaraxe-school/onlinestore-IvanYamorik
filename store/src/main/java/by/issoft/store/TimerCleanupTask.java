package by.issoft.store;

import by.issoft.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimerCleanupTask extends TimerTask {
    private CopyOnWriteArrayList<Product> cart;

    public void run() {
        cart.clear();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Cart is cleared - " + dtf.format(now));

    }

    public TimerCleanupTask(CopyOnWriteArrayList<Product> cart){
        this.cart = cart;
    }
}
