/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 10:38:37 AM
 * ****************************************************************
 */
package org.retest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.retest.annotation.params.Param;
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.randomizer.Randomizer;
import org.retest.datafile.BrokenTestDataFiles;
import org.retest.datafile.SuccessTestDataFiles;

/**
 *
 * @author andreivo
 */
public class ReTestStatement extends Statement {

    private int successes = 0;

    private final FrameworkMethod testMethod;
    private final TestClass testClass;
    private final Object testInstance;
    private final List<AssumptionViolatedException> fInvalidParameters = new ArrayList<>();
    private boolean testFromDataFile;
    private List<Object> testPayload;

    public ReTestStatement(FrameworkMethod _method, TestClass _testClass, Object _testInstance) {
        testMethod = _method;
        testClass = _testClass;
        this.testInstance = _testInstance;
    }

    private TestClass getTestClass() {
        return testClass;
    }

    @Override
    public void evaluate() throws Throwable {
        runWithCompleteAssignment();
        if (successes == 0) {
            Assert.fail("Never found parameters that satisfied method assumptions.  Violated assumptions: "
                    + fInvalidParameters);
        }
    }

    protected void runWithCompleteAssignment() throws InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException, Throwable {
        new BlockJUnit4ClassRunner(getTestClass().getJavaClass()) {

            @Override
            protected void collectInitializationErrors(List<Throwable> errors) {
                // do nothing
            }

            @Override
            public Statement methodBlock(final FrameworkMethod method) {
                final Statement statement = super.methodBlock(method);
                return new Statement() {
                    @Override
                    public void evaluate() throws Throwable {
                        try {
                            statement.evaluate();
                            handleDataPointSuccess();

                            if (!testFromDataFile) {
                                SuccessTestDataFiles stdf = new SuccessTestDataFiles(method, testPayload);
                                stdf.save();
                            }

                        } catch (AssumptionViolatedException e) {
                            if (!testFromDataFile) {
                                BrokenTestDataFiles btdf = new BrokenTestDataFiles(method, testPayload);
                                btdf.save();
                            }

                            handleAssumptionViolation(e);
                        } catch (Throwable e) {
                            if (!testFromDataFile) {
                                BrokenTestDataFiles btdf = new BrokenTestDataFiles(method, testPayload);
                                btdf.save();
                            }
                            throw e;
                        }
                    }
                };
            }

            @Override
            protected Statement methodInvoker(FrameworkMethod method, Object test) {
                return methodCompletesWithParameters(method, test);
            }

            @Override
            public Object createTest() throws Exception {
                return testInstance;
            }
        }.methodBlock(testMethod).evaluate();
    }

    private Statement methodCompletesWithParameters(final FrameworkMethod method, final Object freshInstance) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Object[] values = null;
                if (!testFromDataFile) {
                    values = randomizeParams(method);
                } else if (testPayload != null) {
                    values = testPayload.toArray();
                } else {
                    Assert.fail("Data not found!");
                }
                method.invokeExplosively(freshInstance, values);
            }
        };
    }

    private Object[] randomizeParams(final FrameworkMethod method) throws InstantiationException, IllegalAccessException {
        Annotation[][] parameterAnnotations = method.getMethod().getParameterAnnotations();

        if (parameterAnnotations.length > 0) {
            testPayload = new ArrayList<>();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Object item = null;
                if (parameterAnnotations[i].length > 0) {
                    Annotation a = parameterAnnotations[i][0];
                    Class<? extends Randomizer> randomizerClass = null;
                    if (a.annotationType() == Param.class) {
                        randomizerClass = ((Param) a).randomizerClass();
                    } else if (a.annotationType() == RandomParam.class) {
                        randomizerClass = ((RandomParam) a).randomizerClass();
                    } else if (a.annotationType() == SecureRandomParam.class) {
                        randomizerClass = ((SecureRandomParam) a).randomizerClass();
                    } else {
                        Assert.fail("Test method " + method.getName() + " contain invalid annotation param. @Param annotation not found!");
                    }
                    Randomizer con = randomizerClass.newInstance();
                    item = con.randomizeParam();
                } else {
                    Assert.fail("Test method " + method.getName() + " contain invalid param. @Param annotation not found!");
                }
                testPayload.add(item);
            }
            return testPayload.toArray();
        } else {
            return null;
        }
    }

    protected void handleAssumptionViolation(AssumptionViolatedException e) {
        fInvalidParameters.add(e);
    }

    protected void handleDataPointSuccess() {
        successes++;
    }

    public int getSuccesses() {
        return successes;
    }

    public void setSuccesses(int successes) {
        this.successes = successes;
    }

    public boolean isTestFromDataFile() {
        return testFromDataFile;
    }

    public void setTestFromDataFile(boolean testFromDataFile) {
        this.testFromDataFile = testFromDataFile;
    }

    void setTestPayload(List<Object> data) {
        this.testPayload = data;
    }

}
