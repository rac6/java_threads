package edu.example.rac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class RunnableThreadTest implements TestLifeCycleLogger {
    static final Logger log = LogManager.getLogger(RunnableThreadTest.class);


    @BeforeAll
    static void beforeAll() { }

    @AfterAll
    static void afterAll() { }

    @BeforeEach
    void beforeEach() { 
        this.testInput = new int[] {3, 1, 6, 5, 0, 7, 2, 9, 4, 8};
        threadUnderTest = new RunnableThread(testInput);
    }

    @AfterEach
    void afterEach() { 
        threadUnderTest = null;
    }

    boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }


    int[] testInput;
    RunnableThread threadUnderTest = null;
    


        /**
     * Execution: mvn test -Dtest=RunnableThreadTest#testStart
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    void testStart() throws InterruptedException, IllegalArgumentException {

        Thread thread = new Thread(threadUnderTest);
        thread.start();

        Awaitility.await().until(() -> thread.isAlive(), not(equalTo(true)));

        int[] actual = threadUnderTest.getNums();

        log.info(String.format("Actual sorted int[]: %s", Arrays.toString(actual)));

        assertTrue(isSorted(actual), String.format("int[] not sorted: nums[]: %s", Arrays.toString(threadUnderTest.getNums())));
    }

}
