import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Meny {
    public void menyRun() {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.connect();

        UserManager userManager = new UserManager(dbManager);
        Scanner sc = new Scanner(System.in);

        while (true) {

            try {

                System.out.println("Choose and option\n1. Add credentials\n2. Show credentials by URL\n3. Cancel\n");
                int userInput = sc.nextInt();
                if (userInput == 1) {
                    System.out.println("Write url: ");
                    String uUrl = sc.next();
                    System.out.println("Write username: ");
                    String uUser = sc.next();
                    System.out.println("Write password: ");
                    String uPass = sc.next();

                    boolean inserted = userManager.addCredentials(uUrl, uUser, uPass);

                } else if (userInput == 2) {
                    System.out.println("Please write a url: ");
                    String uUrl = sc.next();
                    userManager.printCredentialsbyUrl(uUrl);

                } else if (userInput == 3) {
                    System.out.println("You choose to exit!");
                    dbManager.disconnect();
                    sc.close();
                    break;
                } else {
                    System.out.println("Please put in a integer between 1-3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please put in a integer not a String");
                sc.nextLine();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
