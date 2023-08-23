package bank_account_app;

public interface IBaseRate {

    default double getBaseRate() {
        return 2.5;
    }

}
