package ca.uwo.eng.se2205b.lab5.model;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents an Account with a list of time organized transactions.
 */
@ParametersAreNonnullByDefault
public final class Account {

    double balance;

    public Account() {
        balance =0;
    }

    /**
     * Add a transaction to the account, updating the balance
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        balance += transaction.getAmount();
    }

    /**
     * Get the Balance of this account
     * @return Current balance
     */
    public double getBalance() {
        return balance;
    }
}
