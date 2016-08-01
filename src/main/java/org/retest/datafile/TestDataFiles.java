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
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.randomizer.Randomizer;

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
            Annotation a = parameterAnnotations[indexOfArguments][0];
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
            finalData = con.getObjectFromString(convertFrom);
        } else {
            Assert.fail("Test method " + method.getName() + " contain invalid param. @Param annotation not found!");
        }

//        for (Annotation a : parameterAnnotations) {
//            Class<? extends Randomizer> randomizerClass = null;
//            if (a.annotationType() == Param.class) {
//                randomizerClass = ((Param) a).randomizerClass();
//            } else if (a.annotationType() == RandomParam.class) {
//                randomizerClass = ((RandomParam) a).randomizerClass();
//            } else if (a.annotationType() == SecureRandomParam.class) {
//                randomizerClass = ((SecureRandomParam) a).randomizerClass();
//            }
//
//            Randomizer con = randomizerClass.newInstance();
//            finalData = con.getObjectFromString(convertFrom);
//        }

        return finalData;
    }

}
