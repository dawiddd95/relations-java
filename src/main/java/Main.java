import dao.UserDao;
import dao.UserRoleDao;
import model.User;
import model.UserRole;
import model.enums.Role;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserDao userDao = new UserDao();
    static UserRoleDao userRoleDao = new UserRoleDao();

    public static void createUser() {
        String name, lastname, role;
        Integer age;

        System.out.println("Type a name: ");
        name = scanner.next();

        System.out.println("Type a lastname: ");
        lastname = scanner.next();

        System.out.println("Type your age: ");
        age = scanner.nextInt();

        System.out.println("Type user role: {USER, ADMIN) ");
        role = scanner.next();

        User user = new User(name, lastname, age, new UserRole(Role.valueOf(role)));
        userDao.createUser(user);
        System.out.println("Utworzono Usera: " + user.toString());
    }

    public static void deleteUser() {
        String lastname;

        System.out.println("Type a lastname: ");
        lastname = scanner.next();

        userDao.deleteUser(lastname);
        System.out.println("Usunięto usera: " + lastname);
    }

    public static void updateUser() {
        String lastname, name, role;
        Integer id, age;

        System.out.println("Type user id to update: ");
        id = scanner.nextInt();

        System.out.println("Type a new name: ");
        name = scanner.next();

        System.out.println("Type a new lastname: ");
        lastname = scanner.next();


        System.out.println("Type a new age: ");
        age = scanner.nextInt();

        System.out.println("Type user role: {USER, ADMIN) ");
        role = scanner.next();

        User user = new User(id, name, lastname, age, new UserRole(Role.valueOf(role)));
        userDao.updateUser(user);
        System.out.println("Update usera: " + user);
    }

    public static void main(String[] args) {
        System.out.println("User roles: " + userRoleDao.getAllUserRoles());
        createUser();
        deleteUser();

        UserDao userDao = new UserDao();
        System.out.println(userDao.getAllUsers());
        updateUser();
        System.out.println(userDao.getAllUsers());
    }
}
