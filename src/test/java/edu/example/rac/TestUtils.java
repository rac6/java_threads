package edu.example.rac;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class TestUtils {
    static final Logger log = LogManager.getLogger(TestUtils.class);


    public static boolean isSorted(int[] array) {
        final String mtd = new Throwable().getStackTrace()[0].getMethodName();

        try {
            log.info(String.format("START: %s", mtd));

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1])
                    return false;
            }
            return true;

        } finally {
            log.info(String.format("END: %s:", mtd));
        }
    }

    public static int[] generateArrayOfRandomInts(final int size, final int bound) {
        final String mtd = new Throwable().getStackTrace()[0].getMethodName();

        try {
            log.info(String.format("START: %s", mtd));

            if(size == 0)
                throw new InvalidParameterException(String.format("The value of the parameter [size] is 0! Accepted values > 0."));

            int[] nums = new int[size];

            Random random = new java.util.Random();

            for(int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(bound);
            }

            log.info(String.format("Generated array of random integers: %s:", Arrays.toString(nums)));

            return nums;

        } finally {
            log.info(String.format("END: %s:", mtd));
        }
    }

}
