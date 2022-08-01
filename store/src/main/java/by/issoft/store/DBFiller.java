package by.issoft.store;


import by.issoft.Category.Category;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.Random;
import java.util.Set;

public class DBFiller implements StoreFiller {

    static Connection CONNECTION = null;
    static Statement STATEMENT = null;
    static ResultSet RESULTSET = null;
    static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    static final String USER = "root";
    static final String PASSWORD = "1216256";

    Store store;

    public DBFiller(Store store) {
        this.store = store;
    }

    public void connectToDB() {
        try {
            //connect to DB
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("\nDatabase connected\n");
            //create a statement
            STATEMENT = CONNECTION.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearDB() {
        //drop tables if exist
        String query1 = "DROP TABLE IF EXISTS CATEGORIES";
        String query2 = "DROP TABLE IF EXISTS PRODUCTS";
        try {
            STATEMENT.executeUpdate(query2);
            STATEMENT.executeUpdate(query1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCategoryTable() {
        String query = "CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL);";
        try {
            STATEMENT.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createProductTable() {
        String query = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "CATEGORY_ID INT NOT NULL," +
                "NAME VARCHAR(255) NOT NULL," +
                "RATE DECIMAL(10, 2) NOT NULL," +
                "PRICE DECIMAL(10, 2) NOT NULL," +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
        try {
            STATEMENT.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillStoreRandomly() {
        Faker faker = new Faker();
        RandomStorePopulator populator = new RandomStorePopulator(faker);
        Set<Category> categorySet = createCategorySet();

        for (Category category : categorySet) {
            //add category to DB
            System.out.println("inserted category " + category.getName() + "into DB");
            try{
            PreparedStatement insertCategories = CONNECTION.prepareStatement("INSERT INTO CATEGORIES(NAME) VALUES (?)");
            insertCategories.setString(1, category.getName());
            insertCategories.execute();

            //execute SQL query
            PreparedStatement findCategoryID = CONNECTION.prepareStatement("SELECT ID FROM CATEGORIES WHERE NAME = ?");
            findCategoryID.setString(1, category.getName());
            RESULTSET = findCategoryID.executeQuery();

            //process result set
            int id = 0;
            while (RESULTSET.next()) {
                id = RESULTSET.getInt("ID");
            }

            Random randomProductAmountToAdd = new Random();
            for (int i = 0; i < randomProductAmountToAdd.nextInt(10); i++) {
                PreparedStatement insertProduct = CONNECTION.prepareStatement("INSERT INTO PRODUCTS(category_id, name, rate, price) VALUES(?, ?, ?, ?)");
                insertProduct.setInt(1, id);
                insertProduct.setString(2, populator.getProductName(category.getName()));
                insertProduct.setDouble(3, populator.getRate());
                insertProduct.setDouble(4, populator.getPrice());
                System.out.println(insertProduct);
                insertProduct.execute();
                System.out.println("New product inserted.");
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Set<Category> createCategorySet() {
        return null;
    }

    public void printFilledStore() {
        try{
            //execute SQL query
            System.out.println("\nPrint Store from DB\n");
            RESULTSET = STATEMENT.executeQuery("SELECT * FROM CATEGORIES");

            RESULTSET = STATEMENT.executeQuery("SELECT * FROM PRODUCTS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

    ;
