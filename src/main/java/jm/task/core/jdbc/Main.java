package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dima", "Mir", (byte) 36);
        userService.saveUser("Valera", "Chur", (byte) 28);
        userService.saveUser("Leo", "Messi", (byte) 36);
        userService.saveUser("Alex", "Ovechkin", (byte) 38);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
