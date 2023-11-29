package edu.example.rac;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SyncStaticVars {
    static final Logger log = LogManager.getLogger(SyncStaticVars.class);
    static int count;
    private static final AtomicInteger syncCount = new AtomicInteger(0);

    private static final Object lock = new Object();
    private static final ReentrantLock rLock = new ReentrantLock();


    SyncStaticVars() {
        /* PROBLEM
         * This straight forward code will have a problem in multi-threaded programs when multiple instances 
         * of the SyncStaticVars class are present as the count variable is "shared" accross all instances.
         */
        count++;

        /* SOLUTION I
         * In this case, the synchronized keyword locks on the class object because the variable is static. 
         * This means no matter how many instances of SyncStaticVars is created, only one can access 
         * the variable at a time, as long as they use the two static methods.
         */
        incrementCount();
        getCount();

        /* SOLUTION II
         * Use a synchronized block to explicitly synchronize on the class object.
         */
        incrementCount2();
        getCount2();

        /* SOLUTION III
         * Use a synchronized block with a specific object instance instead of the class
         */
        incrementCount3();
        getCount3();

        /* SOLUTION IV
         * The AtomicInteger (https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html)
         * class is an alternative way to synchronize access to our static variable. This class provides atomic read and write operations, 
         * ensuring a consistent view of the underlying value across all threads.
         */
        syncCount.incrementAndGet();

        /* SOLUTION V
         * The ReentrantLock (https://www.baeldung.com/java-binary-semaphore-vs-reentrant-lock#reentrantLock) 
         * class is another mechanism we can use to synchronize access to static data. It provides the same basic 
         * behavior and semantics as the synchronized keyword we used earlier but with additional capabilities.
         */
        rLock.lock();
        try {
            count = count + 1;
        }
        finally {
            rLock.unlock();
        }


    }



    /* SOLUTION I */
    private static synchronized void incrementCount() {
        count = count + 1;
    }
    private static synchronized int getCount() {
        return count;
    }


    /* SOLUTION II */
    private static void incrementCount2() {
        synchronized(SyncStaticVars.class) {
            count = count + 1;
        }
    }    
    public static int getCount2() {
        synchronized(SyncStaticVars.class) {
            return count;
        }
    }


    /* SOLUTION III */
    private static void incrementCount3() {
        synchronized(lock) {
            count = count + 1;
        }
    }
    public static int getCount3() {
        synchronized(lock) {
            return count;
        }
    }


    /* SOLUTION IV */
    private static synchronized int getCount4() {
        return syncCount.get();
    }


    /* SOLUTION V */
    public static int getCount5() {
        rLock.lock();
        try {
            return count;
        }
        finally {
            rLock.unlock();
        }
    }

}
