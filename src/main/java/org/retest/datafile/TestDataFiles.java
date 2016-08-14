/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:44:35 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.params.IntegerParam;
import org.retest.annotation.params.Param;
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.datatype.DataType;

/**
 *
 * @author andreivo
 */
public abstract class TestDataFiles {

    public abstract void save(String filePath, FrameworkMethod method, List<Object> arguments, boolean hasExpectedValue) throws Exception;

    public abstract String getFileExtension();

    public abstract String getDelimitador();

    public abstract DataFilePayload loadDataFromFile(String filePath, FrameworkMethod method) throws Exception;

    public abstract List<Object> convertData(String record, FrameworkMethod method) throws Exception;

    public Object convertToObject(FrameworkMethod method, String convertFrom, int indexOfArguments) throws InstantiationException, IllegalAccessException {
        Object finalData = null;
        Annotation[][] parameterAnnotations = method.getMethod().getParameterAnnotations();
        if (parameterAnnotations[indexOfArguments].length > 0) {

            if (parameterAnnotations.length < indexOfArguments + 1) {
                Assert.fail("Invalid number of parameters. The number of parameters in the file is greater than the test function.");
            }

            Annotation a = parameterAnnotations[indexOfArguments][0];

            Class<? extends DataType> dataTypeClass = null;
            if (a.annotationType() == Param.class) {
                dataTypeClass = ((Param) a).dataTypeClass();
            } else if (a.annotationType() == RandomParam.class) {
                dataTypeClass = ((RandomParam) a).randomizerClass();
            } else if (a.annotationType() == SecureRandomParam.class) {
                dataTypeClass = ((SecureRandomParam) a).randomizerClass();
            } else if (a.annotationType() == IntegerParam.class) {
                dataTypeClass = ((IntegerParam) a).randomizerClass();
            } else {
                Assert.fail("Test method " + method.getName() + " contain invalid annotation param. @Param annotation not found!");
            }
            DataType con = dataTypeClass.newInstance();
            finalData = con.getObjectFromString(convertFrom);
        } else {
            Assert.fail("Test method " + method.getName() + " contain invalid param. @Param annotation not found!");
        }

        return finalData;
    }

    public List<String> getHeader(FrameworkMethod method, boolean hasExpectedValue) {
        List<String> result;
        Annotation[][] parameterAnnotations = method.getMethod().getParameterAnnotations();
        Class<?>[] parameterTypes = method.getMethod().getParameterTypes();

        if (parameterAnnotations.length > 0) {
            result = new ArrayList<>();

            for (int i = 0; i < parameterAnnotations.length; i++) {

                if (parameterAnnotations[i].length > 0) {
                    Annotation a = parameterAnnotations[i][0];

                    if (parameterTypes[i].isPrimitive()) {
                        Assert.fail("Dont use primitive types for @Param. Expected a Class instead of '" + parameterTypes[i].getName() + "'");
                    }

                    String valueName = null;
                    if (a.annotationType() == Param.class) {
                        valueName = ((Param) a).name();
                    } else if (a.annotationType() == RandomParam.class) {
                        valueName = ((RandomParam) a).name();
                    } else if (a.annotationType() == IntegerParam.class) {
                        valueName = ((IntegerParam) a).name();
                    } else if (a.annotationType() == SecureRandomParam.class) {
                        valueName = ((SecureRandomParam) a).name();
                    }

                    if (valueName.isEmpty()) {
                        Class<? extends Annotation> type = a.annotationType();
                        valueName = type.getSimpleName();
                    }

                    result.add(valueName);

                } else {
                    Assert.fail("Test method " + method.getName() + " contain invalid param. @Param annotation not found!");
                }
            }

            if (hasExpectedValue) {
                result.add("ExpectedValue");
            }

            return result;
        } else {
            return null;
        }
    }

}
