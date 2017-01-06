/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:05:22 PM
 * ****************************************************************
 */
package retest.datatype;

/**
 *
 * @author andreivo
 * @param <T> param to generics type
 */
public abstract class DataType<T> {

    public abstract String serialize(T value);

    public abstract T deserialize(String value);

    public abstract T randomizeParam();

}
