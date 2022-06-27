package by.issoft.store;


import by.issoft.Category.Category;
import by.issoft.Product;
import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DBFiller implements StoreFiller {
    Store store;

    public static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test; INIT=runscript from 'store/src/main/resources/init.sql'", "sa", "sa");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBFiller (Store store) {
        this.store = store;
    }

    @Override
    public void fillStoreRandomly() throws SQLException {
        List<Category> categories = Store.getInstance().getCategoryList();
        for (int i = 0; i < categories.size(); i++) {
            PreparedStatement insertCategories = connection.prepareStatement("INSERT INTO categories(id, name) VALUES (?, ?)");
            insertCategories.setInt(1, i+1);
            insertCategories.setString(2, categories.get(i).getName());
            insertCategories.execute();

            Faker faker = new Faker();
            RandomStorePopulator populator = new RandomStorePopulator(faker);
            Map<Category, Integer> categoryIntegerMap = StoreFiller.createCategoryToIntegerMap();

            for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    Product product = new Product(
                            populator.getProductName(entry.getKey().getName()),
                            populator.getRate(),
                            populator.getPrice());
                    //entry.getKey().addProduct(product);
                }
                //this.store.getCategoryList().add(entry.getKey());
            }

            int randomProductAmountToAdd = new Random().nextInt(10) + 1;
            for (int j = 0; j < randomProductAmountToAdd; j++) {
                PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products(category_id, name, rate, price) VALUES(?, ?, ?, ?)");
                insertProduct.setInt(1, i + 1);
                insertProduct.setString(2, populator.getProductName(entry.getKey().getName()));
                insertProduct.setInt(3, populator.getRate());
                insertProduct.setDouble(4, populator.getPrice());
                insertProduct.execute();
            }


        }

    }
}
