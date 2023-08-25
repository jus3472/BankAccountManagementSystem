package bank_account_app;

// Savings Account
public class Savings extends Account {
    // List properties specific to the Savings account
    private int safetyDepositBoxID;
    private int safetyDepositBoxKey;

    // Constructor to initialize settings for the Savings properties
    public Savings(String name, String ssn, double initDeposit) {
        super(name, ssn, initDeposit);
        accountNumber = "1" + accountNumber;
        setSafetyDepositBox();
    }

    @Override
    public void setRate() {
        rate = getBaseRate() - .25;
    }

    // List any methods specific to savings account
    private void setSafetyDepositBox() {
        safetyDepositBoxID = (int) (Math.random() * Math.pow(10, 3));
        safetyDepositBoxKey = (int) (Math.random() * Math.pow(10, 4));
    }

    public void showInfo() {
        super.showInfo();
        System.out.println(
                " Your Savings Account Features" +
                        "\n Safety Deposit Box ID: " + safetyDepositBoxID + "\n Safety Deposit Box Key: "
                        + safetyDepositBoxKey);
    }

}
