/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package org.retest.randomizer;

import java.util.Random;

/**
 *
 * @author andreivo
 */
public class IntegerRandomizer extends Randomizer<Integer> {

    @Override
    public Integer getObjectFromString(String value) {
        Integer aInt = Integer.decode(value);        
        return aInt;
    }

    @Override
    public Integer randomizeParam() {
        Random r = new Random(System.currentTimeMillis());        
        return r.nextInt();
    }

}
