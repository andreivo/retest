/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:44:35 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.lang.annotation.Annotation;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.params.Param;
import org.retest.annotation.params.ParamExpected;
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.datatype.DataType;

/**
 *
 * @author andreivo
 */
public abstract class TestDataFiles {

    public abstract void save(String filePath, FrameworkMethod method, List<Object> arguments) throws Exception;

    public abstract String getFileExtension();

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
            if (a.annotationType() == ParamExpected.class) {
                dataTypeClass = ((ParamExpected) a).dataTypeClass();
            } else if (a.annotationType() == Param.class) {
                dataTypeClass = ((Param) a).dataTypeClass();
            } else if (a.annotationType() == RandomParam.class) {
                dataTypeClass = ((RandomParam) a).randomizerClass();
            } else if (a.annotationType() == SecureRandomParam.class) {
                dataTypeClass = ((SecureRandomParam) a).randomizerClass();
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

}
