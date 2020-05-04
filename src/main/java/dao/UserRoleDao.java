package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserRoleDao {
    private Connection connection;
    private final String databaseName = "people";
    private final String tableName = "roles";
    private final String user = "root";
    private final String password = "admin";

    public UserRoleDao() {
        init();
    }

    private void init() {
        try {
            // dla mysql => "com.mysql.jdbc.Driver"
            Class.forName("org.postgresql.Driver");
            // dla mysql => "jdbc:mysql://localhost/"+databaseName+"?useSSL=false"
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/"+databaseName+"?useSSL=false", user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
