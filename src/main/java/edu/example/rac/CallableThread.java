package edu.example.rac;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CallableThread implements Callable<int[]> {
static final Logger log = LogManager.getLogger(CallableThread.class);

    public CallableThread(int[] nums) {
        final String mtd = new Throwable().getStackTrace()[0].getMethodName();

        try {
            log.info(String.format("START: %s", mtd));

            if(nums == null || nums.length == 0)
                throw new InvalidParameterException("The parameter [nums] is null or empty!");

            this.nums = nums;

        } finally {
            log.info(String.format("END: %s:", mtd));
        }
    }

    @Override
    public int[] call() throws Exception {
        final String mtd = new Throwable().getStackTrace()[0].getMethodName();

        try {
            log.info(String.format("START: %s", mtd));

            if(nums.length == 10)
                Thread.sleep(10000);

            log.info(String.format("Thread with id: %d is sorting int[]: %s", Thread.currentThread().getId(), Arrays.toString(this.nums)));
            
            Arrays.sort(this.nums);

            return this.nums;

        } finally {
            log.info(String.format("END: %s:", mtd));
        }
    }
    

    int[] nums;
}
