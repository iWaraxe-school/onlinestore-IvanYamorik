package by.issoft.store;


import by.issoft.Category.Category;
import com.github.javafaker.Faker;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DBFiller implements StoreFiller {

    static Connection CONNECTION = null;
    static Statement STATEMENT = null;
    static ResultSet RESULTSET = null;
    static final String URL = "jdbc:mysql://localhost:3306/onlinestore";
    static final String USER = "user";
    static final String PASSWORD = "";

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
                "ID INT PRIMARY KEY AUTO_INCREMENT" +
                "CATEGORY_ID INT" +
                "NAME VARCHAR(255) NOT NULL" +
                "RATE DECIMAL(10, 2) NOT NULL" +
                "PRICE DECIMAL(10, 2) NOT NULL" +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
        try {
            STATEMENT.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public void fillStoreRandomly() throws SQLException {

        List<Category> categories = Store.getInstance().getCategoryList();
        for (int i = 0; i < categories.size(); i++) {
            PreparedStatement insertCategories = connection.prepareStatement("INSERT INTO categories(id, name) VALUES (?, ?)");
            insertCategories.setInt(1, i + 1);
            insertCategories.setString(2, categories.get(i).getName());
            insertCategories.execute();

            Faker faker = new Faker();
            RandomStorePopulator populator = new RandomStorePopulator(faker);




            int randomProductAmountToAdd = new Random().nextInt(10) + 1;
            for (int j = 0; j < randomProductAmountToAdd; j++) {
                PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products(category_id, name, rate, price) VALUES(?, ?, ?, ?)");
                insertProduct.setInt(1, i + 1);
                insertProduct.setString(2, populator.getProductName(categories.get(i).getName()));
                insertProduct.setInt(3, populator.getRate());
                insertProduct.setDouble(4, populator.getPrice());
                insertProduct.execute();
            }



        }

    }
}
