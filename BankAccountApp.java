package bank_account_app;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

// Main Class
public class BankAccountApp {

    private static List<Account> accounts = new LinkedList<Account>();

    public static void main(String[] args) {

        // Read a CSV File then create new accounts based on that data
        String file = "/Users/justinjiang/VSCode Projects/bank_account_app/NewBankAccounts.csv";

        List<String[]> newAccountHolders = CSV.read(file);

        // Fill "accounts" list with accounts from the CSV file
        for (String[] accountHolder : newAccountHolders) {
            String name = accountHolder[0];
            String ssn = accountHolder[1];
            String accountType = accountHolder[2];
            double initDeposit = Double.parseDouble(accountHolder[3]);
            if (accountType.equals("Savings")) {
                accounts.add(new Savings(name, ssn, initDeposit));
            } else if (accountType.equals("Checking")) {
                accounts.add(new Checking(name, ssn, initDeposit));
            } else {
                System.out.println("ERROR READING ACCOUNT TYPE");
            }
        }

        // Adds user account
        Account userAccount = collectUserAccount();

        // Display user account info
        System.out.println("\n*******************************");
        userAccount.showInfo();

        // Perform transactions on user's account
        transaction(userAccount);

        // Display all accounts
        for (Account acc : accounts) {
            System.out.println("\n*******************************");
            acc.showInfo();
        }

        System.out.println("\nThank you for using the bank account application!");

    }

    private static Account collectUserAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String ssn;
        do {
            System.out.print("Enter your Social Security Number (SSN, 9 digits): ");
            ssn = scanner.nextLine();
        } while (ssn.length() != 9 || !ssn.matches("\\d+"));

        String accountType;
        do {
            System.out.print("Enter your account type (Savings/Checking): ");
            accountType = scanner.nextLine();
        } while (!accountType.equalsIgnoreCase("Savings") && !accountType.equalsIgnoreCase("Checking"));

        System.out.print("Enter initial deposit amount: ");
        double initDeposit = Double.parseDouble(scanner.nextLine());

        if (accountType.equalsIgnoreCase("Savings")) {
            Savings userAccount = new Savings(name, ssn, initDeposit);
            accounts.add(userAccount);
            return userAccount;
        } else {
            Checking userAccount = new Checking(name, ssn, initDeposit);
            accounts.add(userAccount);
            return userAccount;
        }
    }

    private static void transaction(Account userAccount) {
        Scanner scanner = new Scanner(System.in);
        String userChoice;

        do {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Compound\n2. Deposit\n3. Withdraw\n4. Nothing\n");
            userChoice = scanner.nextLine().toLowerCase();

            switch (userChoice) {
                case "compound":
                    userAccount.compound();
                    break;
                case "deposit":
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    userAccount.deposit(depositAmount);
                    break;
                case "withdraw":
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    userAccount.withdraw(withdrawAmount);
                    break;
                case "nothing":
                    System.out.println("Exiting transaction.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (!userChoice.equals("nothing"));
    }

}
