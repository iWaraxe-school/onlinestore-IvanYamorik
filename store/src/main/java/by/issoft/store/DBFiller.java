package by.issoft.store;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBFiller implements StoreFiller {
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

    @Override
    public void fillStoreRandomly() {

    }
}
