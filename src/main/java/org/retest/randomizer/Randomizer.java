/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 29, 2016, 3:05:22 PM
 * ****************************************************************
 */
package org.retest.randomizer;

/**
 *
 * @author andreivo
 * @param <T>
 */
public abstract class Randomizer<T> {

    /**
    * Randomizer String into a correct object value
    * 
     * @param value String that represents a object
     * @return Returns a correct object 
    */
    public abstract T getObjectFromString(String value);
    
    public abstract T randomizeParam();

}
