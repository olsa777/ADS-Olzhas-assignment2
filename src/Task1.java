import java.util.*;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

public class Task1 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1 -- Add account");
            System.out.println("2 -- Display all accounts");
            System.out.println("3 -- Search by username");
            System.out.println("0 -- Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addAccount();
            else if (choice == 2) displayAccounts();
            else if (choice == 3) searchAccount();

        } while (choice != 0);
    }

    static void addAccount() {
        System.out.print("Account number: ");
        String num = scanner.nextLine();
        System.out.print("Username: ");
        String name = scanner.nextLine();
        System.out.print("Balance: ");
        double bal = scanner.nextDouble();
        scanner.nextLine();

        accounts.add(new BankAccount(num, name, bal));
        System.out.println("Account added successfully.");
    }

    static void displayAccounts() {
        if (accounts.isEmpty()) { System.out.println("No accounts."); return; }
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i++ + ". " + acc.username + " -- Balance: " + acc.balance);
        }
    }

    static void searchAccount() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) {
                System.out.println("Found: " + acc.accountNumber + " - " + acc.username + " - Balance: " + acc.balance);
                return;
            }
        }
        System.out.println("Account not found.");
    }
}
