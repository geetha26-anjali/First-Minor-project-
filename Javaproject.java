import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    protected double balance;

   
    public BankAccount(String name, double balance) {
        this.accountHolderName = name;
        this.balance = balance;
    }

    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

   
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }
}



class SavingsAccount extends BankAccount {

    public SavingsAccount(String name, double balance) {
        super(name, balance); 
    }

   
    public void addInterest() {
        double interest = balance * 0.05;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}




class CurrentAccount extends BankAccount {

    private static final double SERVICE_CHARGE = 200;

    public CurrentAccount(String name, double balance) {
        super(name, balance); // Constructor chaining
    }

    
    public void deductServiceCharge() {
        if (balance >= SERVICE_CHARGE) {
            balance -= SERVICE_CHARGE;
            System.out.println("Service charge deducted: " + SERVICE_CHARGE);
        } else {
            System.out.println("Insufficient balance for service charge.");
        }
    }
}



public class Javaproject{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount acc;

        System.out.println("Select Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int choice = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit: ");
        double amount = sc.nextDouble();

     
        if (choice == 1) {
            acc = new SavingsAccount(name, amount);
        } else {
            acc = new CurrentAccount(name, amount);
        }

        int option;
        do {
            System.out.println("\nChoose Operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Add Interest / Deduct Charges");
            System.out.println("5. Exit");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    acc.deposit(sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    acc.withdraw(sc.nextDouble());
                    break;

                case 3:
                    System.out.println("Current Balance: " + acc.getBalance());
                    break;

                case 4:
                    if (acc instanceof SavingsAccount) {
                        ((SavingsAccount) acc).addInterest();
                    } else if (acc instanceof CurrentAccount) {
                        ((CurrentAccount) acc).deductServiceCharge();
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the Bank System.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (option != 5);

        sc.close();
    }
}


