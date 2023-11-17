package edu.example.rac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class CallableThreadTest  extends TestUtils implements TestLifeCycleLogger {
    static final Logger log = LogManager.getLogger(CallableThreadTest.class);
    static final int NUM_OF_CORES =  (int)(Runtime.getRuntime().availableProcessors() * 1.5);

    @BeforeAll
    static void beforeAll() { 
        log.info(String.format("The number of CPU cores available to the JAVA Virtual Machine: %d", NUM_OF_CORES));
    }

    @AfterAll
    static void afterAll() { }

    @BeforeEach
    void beforeEach() { }

    @AfterEach
    void afterEach() { 
    }


    
    /**
     * Execution: mvn test -Dtest=CallableThreadTest#callStart
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    @Test
    void callStart() throws InterruptedException, IllegalArgumentException, ExecutionException {
        ExecutorService executor = Executors.newWorkStealingPool(NUM_OF_CORES);
        List<CallableThread> threads = new ArrayList<CallableThread>();

        for(int i = 1; i <= 20; i++) {
            threads.add(new CallableThread(generateArrayOfRandomInts(i * 10, i * 100)));
        }

        List<Future<int[]>> results = executor.invokeAll(threads);

        try {
            for(Future<int[]> result : results) {
                int[] actual = result.get();
                log.info(String.format("Actual sorted int[]: %s", Arrays.toString(actual)));

                assertTrue(isSorted(actual), String.format("int[] not sorted: nums[]: %s", Arrays.toString(actual)));
            }
        } finally {
            if(executor != null)
                executor.shutdown(); 
        }
    }
}
