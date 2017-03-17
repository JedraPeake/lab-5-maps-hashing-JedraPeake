package ca.uwo.eng.se2205b.lab5;

import ca.uwo.eng.se2205b.lab5.model.Account;
import ca.uwo.eng.se2205b.lab5.model.Bank;
import ca.uwo.eng.se2205b.lab5.model.Person;
import ca.uwo.eng.se2205b.lab5.model.Transaction;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Includes testing for the Banking Model
 */
public class ModelTests {

    @Test
    public void person() throws Exception {
        Person t = new Person("J","P");
        assertEquals("J", t.getFirstName());
        assertEquals("P", t.getLastName());
        //ask kevin about null and empty cases???
    }

    @Test
    public void transaction() throws Exception {
        LocalDateTime date = LocalDateTime.of(15, 5, 30,5,5);
        Transaction t = new Transaction(date,2.5);
        assertEquals(2.5, t.getAmount(),0.00001);
        assertEquals(date, t.getDateTime());
        //ask kevin about null and empty cases???
    }

    @Test
    public void account() throws Exception {
        Account t = new Account();
        assertEquals(0.0, t.getBalance(), 0.01);
        LocalDateTime date = LocalDateTime.of(15, 5, 30,5,5);
        Transaction my = new Transaction(date,2.5);
        t.addTransaction(my);
        assertEquals(2.5, t.getBalance(), 0.01);
        //ask kevin about null and empty cases???
    }

    @Test
    public void bank() throws Exception {
        Person t = new Person("J","P");
        Bank my = new Bank();
        my.getAccounts(t);
        Set<Account> myAccounts=null;
        assertEquals(myAccounts,my.getAccounts(t));
        my.openAccount(t);
        Account temp = new Account();
        myAccounts.add(temp);
        assertEquals(myAccounts,my.getAccounts(t));
//        LocalDateTime date = LocalDateTime.of(15, 5, 30,5,5);
//        Transaction my = new Transaction(date,2.5);
//        t.addTransaction(my);
//        assertEquals(2.5, t.getBalance(), 0.01);
        //ask kevin about null and empty cases???
    }

}
