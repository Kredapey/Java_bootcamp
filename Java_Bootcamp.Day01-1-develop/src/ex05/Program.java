package ex05;

public class Program {
    public static void main(String[] args) {
        Menu menu;
        if (args[0].equals("--profile=dev")) {
            menu = new Menu(Menu.Status.DEV);
        } else {
            menu = new Menu(Menu.Status.PROD);
        }
        menu.start();
    }
}
