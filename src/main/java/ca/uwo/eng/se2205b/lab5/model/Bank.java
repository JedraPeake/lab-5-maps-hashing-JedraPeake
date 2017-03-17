package ca.uwo.eng.se2205b.lab5.model;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

/**
 * Represents a Bank
 */
@ParametersAreNonnullByDefault
public final class Bank {

    Set<Account> myAccounts;
    public Bank() {

    }


    /**
     * Get all of the accounts for a person
     * @param person Person whom has an account
     * @return Possibly empty {@code Set} of accounts for a Person
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public Set<Account> getAccounts(Person person) {
        if(person == null){
            throw new NullPointerException();
        }
        return myAccounts;
    }

    /**
     * Opens a new {@link Account} for an individual.
     * @param person Person who is on an account
     * @return New {@code Account}
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public Account openAccount(Person person) {
        if(person == null){
            throw new NullPointerException();
        }
        return null;
    }

    /**
     * Closes an account for an individual
     * @param person Person who is on an account
     * @param account Account to close
     *
     * @throws NullPointerException if {@code person} or {@code account} is {@code null}
     * @throws AccountCloseException if the Account can not be closed
     */
    public void closeAccount(Person person, Account account) throws AccountCloseException {


    }

    /**
     * Get <em>all</em> of the wealth of a person, the total of all of a person's accounts.
     * @param person Non-{@code null} person to get the total wealth of.
     * @return Total wealth
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public double getTotalWealth(Person person) {

        return 0.0;
    }
}
