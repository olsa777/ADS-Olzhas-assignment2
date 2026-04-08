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

public class Task3 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> transactionHistory = new Stack<>();  // NEW in Task 3
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1 -- Add account");
            System.out.println("2 -- Display all accounts");
            System.out.println("3 -- Search by username");
            System.out.println("4 -- Deposit");
            System.out.println("5 -- Withdraw");
            System.out.println("6 -- View last transaction");
            System.out.println("7 -- Undo last transaction");
            System.out.println("0 -- Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addAccount();
            else if (choice == 2) displayAccounts();
            else if (choice == 3) searchAccount();
            else if (choice == 4) deposit();
            else if (choice == 5) withdraw();
            else if (choice == 6) viewLastTransaction();
            else if (choice == 7) undoLastTransaction();

        } while (choice != 0);
    }

    // ── Task 1 methods ───────────────────────────

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

    // ── Task 2 methods ───────────────────────────

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

        transactionHistory.push("Deposit " + amount + " to " + name);  // Task 3: record it
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

        transactionHistory.push("Withdraw " + amount + " from " + name);  // Task 3: record it
    }

    // ── Task 3 methods ───────────────────────────

    static void viewLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions yet."); return; }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    static void undoLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("Nothing to undo."); return; }
        String removed = transactionHistory.pop();
        System.out.println("Undo → " + removed + " removed.");
    }
}
