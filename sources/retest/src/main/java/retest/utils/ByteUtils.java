/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:40:07 AM
 * ****************************************************************
 */
package retest.utils;

import java.nio.ByteBuffer;

/**
 *
 * @author andreivo
 */
public class ByteUtils {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE);

    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

}
