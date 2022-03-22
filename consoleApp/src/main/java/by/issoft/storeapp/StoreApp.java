package by.issoft.storeapp;


import by.issoft.store.Store;
import by.issoft.store.StoreFiller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StoreApp {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        printStoreWithoutReflection();

        printStoreWithReflection();
    }

    public static void printStoreWithoutReflection() {
        System.out.println(" My store: ");

        Store newStore = new Store();

        StoreFiller storeFiller = new StoreFiller(newStore);
        storeFiller.fillStoreRandomly();

        String storeString = newStore.toString();

        System.out.println(storeString);
    }

    public static void printStoreWithReflection()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        //reflections
        Class<?> storeClass = Class.forName("by.issoft.store.Store");
        Constructor<?> storeConstructor = storeClass.getConstructor();
        Store newStore2 = (Store) storeConstructor.newInstance();

        Class<?> storeFillerClass = Class.forName("by.issoft.store.StoreFiller");
        Constructor<?> storeFillerConstructor = storeFillerClass.getConstructor(Store.class);
        StoreFiller storeFiller2 = (StoreFiller) storeFillerConstructor.newInstance(newStore2);

        //Method.fillStoreRandomly
        Method fillStoreRandomlyMethod = storeFillerClass.getDeclaredMethod("fillStoreRandomly");
        fillStoreRandomlyMethod.invoke(storeFiller2);

        Method toStringMethod = storeClass.getDeclaredMethod("toString");
        String storeString2 = (String) toStringMethod.invoke(newStore2);

        System.out.println(" My store with reflection: ");
        System.out.println(storeString2);
    }
}
