package fileSystem;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        Admin admin = new Admin("admin", "admin");
        controller.userList.add(admin);
        
        Ihm ihm = new Ihm(controller);
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Username: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            User currentUser = authenticate(controller, username, password);
            if (currentUser == null) {
                System.out.println("Incorrect input");
                continue;
            }

            Folder currentFolder = currentUser.getHome();
            ihm.clearConsole();
            ihm.show(currentFolder);
            ihm.userMenu(currentFolder,currentUser);

            System.out.println("Signed out.");
            ihm.clearConsole();
        }
    }

    private static User authenticate(Controller controller, String username, String password) {
        for (User user : controller.userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

}
