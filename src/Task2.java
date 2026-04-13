import java.util.*;

public class Task2 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1 -- Add account");
            System.out.println("2 -- Display all accounts");
            System.out.println("3 -- Search by username");
            System.out.println("4 -- Deposit");
            System.out.println("5 -- Withdraw");
            System.out.println("0 -- Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addAccount();
            else if (choice == 2) displayAccounts();
            else if (choice == 3) searchAccount();
            else if (choice == 4) deposit();
            else if (choice == 5) withdraw();

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

    static BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) return acc;
        }
        return null;
    }


    static void deposit() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        BankAccount acc = findAccount(name);
        if (acc == null) { System.out.println("Account not found."); return; }

        System.out.print("Deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        acc.balance += amount;
        System.out.println("New balance: " + acc.balance);
    }

    static void withdraw() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        BankAccount acc = findAccount(name);
        if (acc == null) { System.out.println("Account not found."); return; }

        System.out.print("Withdraw amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount > acc.balance) { System.out.println("Insufficient balance."); return; }

        acc.balance -= amount;
        System.out.println("New balance: " + acc.balance);
    }
}
