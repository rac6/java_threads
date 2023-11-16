package edu.example.rac;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class RunnableThread implements Runnable  {
    static final Logger log = LogManager.getLogger(RunnableThread.class);

    public RunnableThread(int[] nums) {
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
    public void run() {
        final String mtd = new Throwable().getStackTrace()[0].getMethodName();

        try {
            log.info(String.format("START: %s", mtd));

            log.info(String.format("Sorting int[]: %s", Arrays.toString(this.nums)));
            
            Arrays.sort(this.nums);

        } finally {
            log.info(String.format("END: %s:", mtd));
        }
    }

    int[] getNums() {
        return this.nums;
    }


    int[] nums;
}
