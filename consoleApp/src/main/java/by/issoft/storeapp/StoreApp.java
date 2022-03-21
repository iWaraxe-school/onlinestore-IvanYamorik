package by.issoft.storeapp;


import by.issoft.store.Store;
import by.issoft.store.StoreFiller;

public class StoreApp {
    public static void main(String[] args) {

        System.out.println(" My store: ");

        Store newStore = new Store();

        StoreFiller storeFiller = new StoreFiller(newStore);
        storeFiller.fillStoreRandomly();

        String storeString = newStore.toString();

        System.out.println(storeString);
    }
}
