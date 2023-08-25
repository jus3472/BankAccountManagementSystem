package bank_account_app;

// Interface
public interface IBaseRate {

    default double getBaseRate() {
        return 2.5;
    }

}
