/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package org.retest.randomizer;

import org.retest.utils.SecureRandomSeed;

/**
 *
 * @author andreivo
 */
public class SecureRandomSeedRandomizer extends Randomizer<SecureRandomSeed> {

    @Override
    public SecureRandomSeed getObjectFromString(String value) {
        Long aLong = Long.decode(value);
        SecureRandomSeed rs = new SecureRandomSeed(aLong);
        return rs;
    }

    @Override
    public SecureRandomSeed randomizeParam() {
        SecureRandomSeed r = new SecureRandomSeed(System.currentTimeMillis());
        return r;
    }

}
