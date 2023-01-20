package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        while (true) {
            System.out.println("""
                    1-> create Users Table:
                    2-> drop Users Table:
                    3-> save User:
                    4-> remove user by id:
                    5-> get all users:
                    6-> cleanUsersTable:
                    """);
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1:
                    userService.createUsersTable();
                    break;
                case 2:
                    userService.dropUsersTable();
                    break;
                case 3:
                    userService.saveUser("Adilet", "Ismailov", (byte) 21);
                    userService.saveUser("Kuban", "Kelsinbekov", (byte) 20);
                    userService.saveUser("Janyshbek bratan", "Akbalaev", (byte) 19);
                    break;
                case 4:
                    userService.removeUserById(1);
                    break;
                case 5:
                    System.out.println(userService.getAllUsers());
                    break;
                case 6:
                    userService.cleanUsersTable();
                    break;
                default:
                    System.out.println("No num!!!");
            }
        }
    }
}
