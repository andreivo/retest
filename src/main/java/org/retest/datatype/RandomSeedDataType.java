/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package org.retest.datatype;

import org.retest.utils.RandomSeed;

/**
 *
 * @author andreivo
 */
public class RandomSeedDataType extends DataType<RandomSeed> {

    @Override
    public RandomSeed deserialize(String value) {
        Long aLong = Long.decode(value);
        RandomSeed rs = new RandomSeed(aLong);
        return rs;
    }

    @Override
    public RandomSeed randomizeParam() {
        RandomSeed r = new RandomSeed(System.currentTimeMillis());
        return r;
    }

    @Override
    public String serialize(RandomSeed value) {
        return value.toString();
    }

}
