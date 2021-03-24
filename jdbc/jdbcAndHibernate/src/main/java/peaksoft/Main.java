package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
       // userService.createUsersTable();

        User user = new User("Aisanat","Kolbaieva",(byte)23);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());

        User user1 = new User("Begimai","Kaibalieva", (byte) 22);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Alymkul","Jolomaniv",(byte) 25);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Baktiyar","Kaparov",(byte) 34);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Elnura","Kaparova",(byte) 23);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        User user5 = new User("Kanat","Joldoshev",(byte) 25);
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());

        User user6 = new User("Edil","Moldoakmatov",(byte) 29);
        userService.saveUser(user6.getName(), user6.getLastName(), user6.getAge());

         List<User> userList = userService.getAllUsers();
         for (User users : userList ) {
             System.out.println(users);

         }

        //userService.dropUsersTable();
       // userService.removeUserById(1);
        //userService.cleanUsersTable();


    }
}
