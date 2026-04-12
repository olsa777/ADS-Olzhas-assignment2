import java.util.LinkedList;
import java.util.Scanner;

class Node {
    int data;
    Node next;
    Node(int new_data){
        data = new_data;
        next = null;
    }
}
class myQueue {
    private Node front;
    private Node rear;
    public myQueue(){
        front = rear = null;
    }
    public void enqueue(int new_data) {
        Node new_node = new Node(new_data);
        if (isEmpty()) {
            front = rear = new_node;
        } else {
            rear.next = new_node;
            rear = new_node;
        }
    }
    public boolean isEmpty() {
        return front == null;
    }
    public int getfront() {
        if (isEmpty()) {
            return -1;
        }
        return front.data;
    }
    public void dequeue() {
        if (isEmpty()) return;
        front = front.next;
        if (front == null) rear = null;
    }
}
class myStack {
    private int top;
    private int[] arr;
    private int capacity;

    public myStack(int cap) {
        capacity = cap;
        arr = new int [capacity];
        top = -1;
    }
    public void push(int x){
        if (top == capacity - 1) return;
        arr[++top] = x;
    }
    public int peek(){
        return (top == -1) ? -1 : arr[top];
    }
    public int pop(){
        if (top == -1) return -1;
        return arr[top--];
    }
    public boolean isEmpty(){
        return top == -1;
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

            switch(choice) {
                case 1 -> addAccount();
                case 2 -> displayAccounts();
                case 3 -> searchAccount();
            }
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
