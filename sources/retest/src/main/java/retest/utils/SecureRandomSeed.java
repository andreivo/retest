/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 2:49:07 PM
 * ****************************************************************
 */
package retest.utils;

import java.security.SecureRandom;

/**
 *
 * @author andreivo
 */
public class SecureRandomSeed extends SecureRandom {

    private long seed;

    public SecureRandomSeed(long l) {
        super(ByteUtils.longToBytes(l));
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
