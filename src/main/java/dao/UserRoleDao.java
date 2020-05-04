package dao;

import model.UserRole;
import model.enums.Role;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRoleDao {
    private Connection connection;
    private UserRoleDao userRoleDao = new UserRoleDao();
    private final String databaseName = "people";
    private final String tableName = "users";
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

    public List<UserRole> getAllUserRoles() {
        List<UserRole> userRoles = new LinkedList<UserRole>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Integer age = resultSet.getInt("age");
                Integer userRoleId = resultSet.getInt("user_role_id");
                UserRole userRole = userRoleDao.getRoleById(userRoleId);
                User user = new User(id, name, lastname, age, userRole);
                users.add(user);
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