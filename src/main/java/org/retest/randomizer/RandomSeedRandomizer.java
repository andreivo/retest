/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package org.retest.randomizer;

import org.retest.utils.RandomSeed;

/**
 *
 * @author andreivo
 */
public class RandomSeedRandomizer extends Randomizer<RandomSeed> {

    @Override
    public RandomSeed getObjectFromString(String value) {
        Long aLong = Long.decode(value);
        RandomSeed rs = new RandomSeed(aLong);
        return rs;
    }

    @Override
    public RandomSeed randomizeParam() {
        RandomSeed r = new RandomSeed(System.currentTimeMillis());
        return r;
    }

}
