package edu.example.rac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



/**
 * Unit test for simple App.
 */
public class AppTest {

    static final Logger log = LogManager.getLogger(AppTest.class);


    /**
     * Rigorous Test :-)

     * Execution: mvn test -Dtest=AppTest#shouldAnswerWithTrue
     */
    @Test
    public void shouldAnswerWithTrue() {
        log.info(String.format("Verifying '%b'", true));
        assertTrue( true );
    }
}
