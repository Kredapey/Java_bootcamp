package ex02;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        UsersList users = new UsersArrayList();
        User us1 = new User("Ivan", 500);
        User us2 = new User("Dmitry", 250);
        User us3 = new User("Nikita", 300);
        User us4 = new User("Kate", 400);
        User us5 = new User("John", 900);
        User us6 = new User("Mike", 1500);
        User us7 = new User("Ignat", 3700);
        User us8 = new User("Pavel", 100);
        User us9 = new User("Regina", 480);
        User us10 = new User("Marina", 960);
        User us11 = new User("Alex", 5400);
        users.addUser(us1);
        users.addUser(us2);
        users.addUser(us3);
        users.addUser(us4);
        users.addUser(us5);
        users.addUser(us6);
        users.addUser(us7);
        users.addUser(us8);
        users.addUser(us9);
        users.addUser(us10);
        users.addUser(us11);
        System.out.println(users.getUserByIndex(0).toString());
        System.out.println(users.getUserById(11).toString());
        System.out.println(users.getCountUsers());
        User[] usersConstructorArray = new User[]{us1, us2, us3, us4, us5,
                us6, us7, us8, us9, us10, us11};
        UsersList users2 = new UsersArrayList(usersConstructorArray);
        System.out.println(users2.getUserByIndex(3).toString());
        System.out.println(users2.getUserById(8).toString());
        System.out.println(users2.getCountUsers());
    }
}
