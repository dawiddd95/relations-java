package dao;

import model.User;
import model.UserRole;
import model.enums.Role;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRoleDao {
    private Connection connection;
    private final String databaseName = "people";
    private final String tableName = "roles";
    private final String user = "postgres";
    private final String password = "m2svs4";

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


    public List<UserRole> getAllUserRoles() {
        List<UserRole> userRoles = new LinkedList<UserRole>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("role");

                userRoles.add(new UserRole(id, Role.valueOf(name)));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }

    public UserRole getRoleById(Integer id) {
        Statement statement = null;
        try {
            String query = "select * from " + tableName + " where id = '" + id + "'";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String role = resultSet.getString("role");
                Role userRole = Role.valueOf(role);
                return new UserRole(id, userRole);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getRoleIdByName(String roleName) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName + " where role = '" + roleName + "'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                return id;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
