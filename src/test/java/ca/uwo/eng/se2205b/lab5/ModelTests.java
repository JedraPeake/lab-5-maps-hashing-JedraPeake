package ca.uwo.eng.se2205b.lab5;

import ca.uwo.eng.se2205b.lab5.model.Person;
import org.junit.Test;

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

}
