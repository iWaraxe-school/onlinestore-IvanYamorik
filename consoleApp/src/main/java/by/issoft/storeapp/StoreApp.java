package by.issoft.storeapp;


import by.issoft.store.Store;

public class StoreApp {
    public static void main(String[] args) {

        System.out.println(" My store");

        Store newStore = new Store();
        String storeString = newStore.toString();

        System.out.println(storeString);
    }
}
