/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:04:54 PM
 * ****************************************************************
 */
package org.retest.datatype;

import org.retest.utils.SecureRandomSeed;

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
        SecureRandomSeed r = new SecureRandomSeed(System.currentTimeMillis());
        return r;
    }

    @Override
    public String serialize(SecureRandomSeed value) {
        return value.toString();
    }

}
