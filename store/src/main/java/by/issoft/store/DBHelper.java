package by.issoft.store;

import lombok.experimental.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class DBHelper implements Helper {
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
}
