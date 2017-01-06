/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 2:49:07 PM
 * ****************************************************************
 */
package retest.utils;

import java.util.Random;

/**
 *
 * @author andreivo
 */
public class RandomSeed extends Random {

    private long seed;

    public RandomSeed(long l) {
        super(l);
        this.seed = l;
    }

    public long getSeed() {
        return seed;
    }

    @Override
    public void setSeed(long seed) {
        super.setSeed(seed);
        this.seed = seed;
    }

    @Override
    public String toString() {
        return Long.toString(seed);
    }
}
