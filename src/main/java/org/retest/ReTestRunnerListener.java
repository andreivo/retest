/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 10, 2016, 12:22:59 AM
 * ****************************************************************
 */
package org.retest;

import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


/**
 *
 * @author andreivo
 */

public class ReTestRunnerListener extends RunListener {
    
    /**
     * Called before any tests have been run.
     * @param description describes the tests to be run
     */
    
    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("testRunStarted with Description : " + description);
    }
    
    /**
     * Called when all tests have finished
     * @param result the summary of the test run, including all the tests that failed
     */
    
    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("testRunStarted with Result : " + result);
    }
    
    /**
     * Called when an atomic test is about to be started.
     * @param description the description of the test that is about to be run 
     * (generally a class and method name)
     */
    
    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("testStarted with Description : " + description);
    }

    /**
     * Called when an atomic test has finished, whether the test succeeds or fails.
     * @param description the description of the test that just ran
     */
    
    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("testFinished with Description : " + description);
    }

    /** 
     * Called when an atomic test fails.
     * @param failure describes the test that failed and the exception that was thrown
     */
    
    public void testFailure(Failure failure) throws Exception {
        System.out.println("testFailure with Failure : " + failure);
    }

    /**
     * Called when an atomic test flags that it assumes a condition that is
     * false
     * 
     * @param failure
     *            describes the test that failed and the
     *            {@link AssumptionViolatedException} that was thrown
     */
    
    public void testAssumptionFailure(Failure failure) {
        System.out.println("testAssumptionFailure with Failure : " + failure);
    }

    /**
     * Called when a test will not be run, generally because a test method is annotated 
     * with {@link org.junit.Ignore}.
     * 
     * @param description describes the test that will not be run
     */
    
    public void testIgnored(Description description) throws Exception {
        System.out.println("testIgnored with Description : " + description);
    }
}
