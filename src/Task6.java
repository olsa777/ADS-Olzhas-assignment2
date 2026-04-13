import java.util.*;

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class MyStack<T> {
    private Node<T> top;

    void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    T pop() {
        if (isEmpty()) return null;
        T data = top.data;
        top = top.next;
        return data;
    }

    T peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    boolean isEmpty() {
        return top == null;
    }
}

class MyQueue<T> {
    private Node<T> front;
    private Node<T> back;

    void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            back.next = newNode;
            back = newNode;
        }
    }

    T dequeue() {
        if (isEmpty()) return null;
        T data = front.data;
        front = front.next;
        if (front == null) back = null;
        return data;
    }

    T peek() {
        if (isEmpty()) return null;
        return front.data;
    }

    boolean isEmpty() {
        return front == null;
    }

    void display() {
        if (isEmpty()) { System.out.println("Queue is empty."); return; }
        Node<T> current = front;
        int i = 1;
        while (current != null) {
            System.out.println(i++ + ". " + current.data);
            current = current.next;
        }
    }
}

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

public class Task6 {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static MyStack<String> transactionHistory = new MyStack<>();
    static MyQueue<String> billQueue = new MyQueue<>();
    static MyQueue<BankAccount> accountRequests = new MyQueue<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        BankAccount[] physicalAccounts = new BankAccount[3];
        physicalAccounts[0] = new BankAccount("A01", "Amir", 100000);
        physicalAccounts[1] = new BankAccount("A02", "Dana", 200000);
        physicalAccounts[2] = new BankAccount("A03", "Zara", 300000);

        System.out.println("=== Task 6: Physical Array Accounts ===");
        for (BankAccount acc : physicalAccounts) {
            System.out.println(acc.accountNumber + " - " + acc.username + " -- Balance: " + acc.balance);
        }
        System.out.println("========================================\n");

        int choice;
        do {
            System.out.println("\n========= MAIN MENU =========");
            System.out.println("1 -- Enter Bank");
            System.out.println("2 -- Enter ATM");
            System.out.println("3 -- Admin Area");
            System.out.println("4 -- Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) bankMenu();
            else if (choice == 2) atmMenu();
            else if (choice == 3) adminMenu();
            else if (choice == 4) System.out.println("Goodbye!");
            else System.out.println("Invalid option.");

        } while (choice != 4);
    }

    static void bankMenu() {
        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1 -- Add account");
            System.out.println("2 -- Display all accounts");
            System.out.println("3 -- Search by username");
            System.out.println("4 -- Deposit");
            System.out.println("5 -- Withdraw");
            System.out.println("6 -- Add bill payment");
            System.out.println("7 -- Submit account opening request");
            System.out.println("8 -- View last transaction");
            System.out.println("9 -- Undo last transaction");
            System.out.println("0 -- Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) addAccount();
            else if (choice == 2) displayAccounts();
            else if (choice == 3) searchAccount();
            else if (choice == 4) deposit();
            else if (choice == 5) withdraw();
            else if (choice == 6) addBill();
            else if (choice == 7) submitAccountRequest();
            else if (choice == 8) viewLastTransaction();
            else if (choice == 9) undoLastTransaction();
            else if (choice != 0) System.out.println("Invalid option.");

        } while (choice != 0);
    }

    static void atmMenu() {
        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1 -- Balance enquiry");
            System.out.println("2 -- Withdraw");
            System.out.println("0 -- Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String name = scanner.nextLine();
                BankAccount acc = findAccount(name);
                if (acc != null) System.out.println("Balance: " + acc.balance);
                else System.out.println("Account not found.");
            } else if (choice == 2) {
                withdraw();
            } else if (choice != 0) {
                System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    static void adminMenu() {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1 -- View pending account requests");
            System.out.println("2 -- Process next account request");
            System.out.println("3 -- View bill queue");
            System.out.println("4 -- Process next bill");
            System.out.println("0 -- Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) viewPendingRequests();
            else if (choice == 2) processAccountRequest();
            else if (choice == 3) displayBillQueue();
            else if (choice == 4) processNextBill();
            else if (choice != 0) System.out.println("Invalid option.");

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
        transactionHistory.push("Deposit " + amount + " to " + name);
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
        transactionHistory.push("Withdraw " + amount + " from " + name);
    }

    static void viewLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions yet."); return; }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    static void undoLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("Nothing to undo."); return; }
        String removed = transactionHistory.pop();
        System.out.println("Undo → " + removed + " removed.");
    }

    static void addBill() {
        System.out.print("Enter bill name: ");
        String bill = scanner.nextLine();
        billQueue.enqueue(bill);
        System.out.println("Added: " + bill);
        transactionHistory.push("Bill payment added: " + bill);
    }

    static void processNextBill() {
        if (billQueue.isEmpty()) { System.out.println("No bills in queue."); return; }
        System.out.println("Processing: " + billQueue.dequeue());
        if (billQueue.isEmpty()) System.out.println("No remaining bills.");
        else System.out.println("Next in queue: " + billQueue.peek());
    }

    static void displayBillQueue() {
        System.out.println("Bill Queue:");
        billQueue.display();
    }

    static void submitAccountRequest() {
        System.out.print("Account number: ");
        String num = scanner.nextLine();
        System.out.print("Username: ");
        String name = scanner.nextLine();
        System.out.print("Initial balance: ");
        double bal = scanner.nextDouble();
        scanner.nextLine();

        accountRequests.enqueue(new BankAccount(num, name, bal));
        System.out.println("Request submitted.");
    }

    static void viewPendingRequests() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        MyQueue<BankAccount> temp = new MyQueue<>();
        int i = 1;
        System.out.println("Pending Requests:");
        while (!accountRequests.isEmpty()) {
            BankAccount req = accountRequests.dequeue();
            System.out.println(i++ + ". " + req.username + " (Acc: " + req.accountNumber + ")");
            temp.enqueue(req);
        }
        while (!temp.isEmpty()) {
            accountRequests.enqueue(temp.dequeue());
        }
    }

    static void processAccountRequest() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        BankAccount approved = accountRequests.dequeue();
        accounts.add(approved);
        System.out.println("Account approved and added: " + approved.username);
    }
}