package bank_account_app;

import java.util.Random;

public class Checking extends Account {
    // List properties specific to a checking account
    private long debitCardNumber;
    private int debitCardPIN;

    // Constructor to initialize checking account properties
    public Checking(String name, String ssn, double initDeposit) {
        super(name, ssn, initDeposit);
        accountNumber = "2" + accountNumber;
        setDebitCard();
    }

    @Override
    public void setRate() {
        rate = getBaseRate() * .15;
    }

    // List any methods specific to the checking account
    private void setDebitCard() {
        Random random = new Random();
        long minDebitCardNumber = 1_000_000_000_000_000L; // Minimum 16-digit number
        long maxDebitCardNumber = 9_999_999_999_999_999L; // Maximum 16-digit number
        debitCardNumber = minDebitCardNumber
                + (long) (random.nextDouble() * (maxDebitCardNumber - minDebitCardNumber + 1));
        debitCardPIN = random.nextInt((int) Math.pow(10, 4));
        debitCardPIN = (int) (Math.random() * Math.pow(10, 4));
    }

    public void showInfo() {
        super.showInfo();
        System.out.println(
                " Your Checking Account Features" +
                        "\n Debit Card Number: " + debitCardNumber + "\n Debit Card PIN: " + debitCardPIN);
    }

}
