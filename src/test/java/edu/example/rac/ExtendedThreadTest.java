package edu.example.rac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ExtendedThreadTest extends TestUtils implements TestLifeCycleLogger {
    static final Logger log = LogManager.getLogger(ExtendedThreadTest.class);


    @BeforeAll
    static void beforeAll() { }

    @AfterAll
    static void afterAll() { }

    @BeforeEach
    void beforeEach() { 
        this.testInput = new int[] {6, 3, 8, 1, 0, 7, 5, 2, 9, 4};
        threadUnderTest = new ExtendedThread(testInput);
    }

    @AfterEach
    void afterEach() { 
        threadUnderTest = null;
    }


    int[] testInput;
    ExtendedThread threadUnderTest = null;



    /**
     * Execution: mvn test -Dtest=ExtendedThreadTest#startTest
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    void startTest() throws InterruptedException, IllegalArgumentException {
        threadUnderTest.start();

        Awaitility.await().until(() -> threadUnderTest.isAlive(), is(false));

        int[] actual = threadUnderTest.getNums();

        log.info(String.format("Actual sorted int[]: %s", Arrays.toString(actual)));

        assertTrue(isSorted(actual), String.format("int[] not sorted: nums[]: %s", Arrays.toString(threadUnderTest.getNums())));
    }
}
