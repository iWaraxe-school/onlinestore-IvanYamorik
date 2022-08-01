package by.issoft.storeapp;


import by.issoft.Category.Category;
import by.issoft.Product;
import by.issoft.store.*;
import org.xml.sax.SAXException;
import sortProperties.UnitedComparator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.exit;

public class StoreApp {
    private static CopyOnWriteArrayList<Product> cart = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            ParserConfigurationException, IOException, SAXException, SQLException {
        System.out.println("Main Thread");
        Store.getInstance();

        Store onlineStore = Store.getInstance();

        DBFiller dbFiller = new DBFiller(onlineStore);
        dbFiller.connectToDB();
        dbFiller.clearDB();
        dbFiller.createCategoryTable();
        dbFiller.createProductTable();
        dbFiller.fillStoreRandomly();
        dbFiller.printFilledStore();

        //StoreFiller storeFiller = new InMemoryStoreFiller(Store.getInstance());
        //storeFiller.fillStoreRandomly();

        Map<String, String> getPropertiesToSortMap = XmlReader.getPropertiesToSort();

        UnitedComparator comparator = new UnitedComparator();
        List<Category> categoryList = Store.getInstance().getCategoryList();
        Category firstCategory = categoryList.get(0);
        List<Product> firstCategoryProducts = firstCategory.getProductList();

        //Cleanup purchased product list products every 120 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerCleanupTask(cart), 0,120000);

        String[] options = {"1- sort",
                            "2- top",
                            "3- create order",
                            "4- quit",

        };
        Scanner console = new Scanner(System.in);
        int option = 1;
        while (option != 4) {
            printMenu(options);
            try {
                option = console.nextInt();
                switch (option) {
                    case 1:
                        List<Product> sortedProductList = comparator.sortProducts(firstCategoryProducts, getPropertiesToSortMap);
                        System.out.println(sortedProductList);
                        break;
                    case 2:
                        List<Product> listTopProducts = comparator.topProducts(firstCategoryProducts);
                        System.out.println(listTopProducts);
                        break;
                    case 3:
                        createOrder(firstCategoryProducts);

                        break;
                    case 4:
                        timer.cancel();
                        exit(0);
                        break;
                    default:
                        System.out.println("Please enter an integer value between 1 and " + options.length);
                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                console.next();
            }

        }

        //printStoreWithoutReflection();

        //printStoreWithReflection();
    }

    private static void createOrder(List<Product> allProducts) {


        new Thread(new CartRunnable(cart, allProducts)).start();
    }

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }



    public static void printStoreWithoutReflection() {
        System.out.println(" My store: ");

        Store.getInstance();

        InMemoryStoreFiller storeFiller = new InMemoryStoreFiller(Store.getInstance());
        storeFiller.fillStoreRandomly();

        String storeString = Store.getInstance().toString();

        System.out.println(storeString);
    }

    public static void printStoreWithReflection()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        //reflections
        Class<?> storeClass = Class.forName("by.issoft.store.Store");
        Constructor<?> storeConstructor = storeClass.getConstructor();
        Store newStore2 = (Store) storeConstructor.newInstance();

        Class<?> storeFillerClass = Class.forName("by.issoft.store.InMemoryStoreFiller");
        Constructor<?> storeFillerConstructor = storeFillerClass.getConstructor(Store.class);
        InMemoryStoreFiller storeFiller2 = (InMemoryStoreFiller) storeFillerConstructor.newInstance(newStore2);

        //Method.fillStoreRandomly
        Method fillStoreRandomlyMethod = storeFillerClass.getDeclaredMethod("fillStoreRandomly");
        fillStoreRandomlyMethod.invoke(storeFiller2);

        Method toStringMethod = storeClass.getDeclaredMethod("toString");
        String storeString2 = (String) toStringMethod.invoke(newStore2);

        System.out.println(" My store with reflection: ");
        System.out.println(storeString2);
    }
}

