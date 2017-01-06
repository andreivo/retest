/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package retest.datatype;

import java.util.Random;
import retest.utils.SecureRandomSeed;

/**
 *
 * @author andreivo
 */
public class SecureRandomSeedDataType extends DataType<SecureRandomSeed> {

    @Override
    public SecureRandomSeed deserialize(String value) {
        Long aLong = Long.decode(value);
        SecureRandomSeed rs = new SecureRandomSeed(aLong);
        return rs;
    }

    @Override
    public SecureRandomSeed randomizeParam() {
        Random rseed = new Random(10000);
        SecureRandomSeed r = new SecureRandomSeed(System.currentTimeMillis() - rseed.nextLong());
        return r;
    }

    @Override
    public String serialize(SecureRandomSeed value) {
        return value.toString();
    }

}
