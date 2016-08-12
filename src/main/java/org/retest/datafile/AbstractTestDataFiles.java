/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:44:35 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.SaveBrokenTestDataFiles;
import org.retest.annotation.params.Param;
import org.retest.annotation.params.ParamExpected;
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.datatype.DataType;

/**
 *
 * @author andreivo
 */
public abstract class AbstractTestDataFiles {

    private FrameworkMethod method;
    private List<Object> arguments;

    public AbstractTestDataFiles(FrameworkMethod method, List<Object> arguments) {
        this.method = method;
        this.arguments = arguments;
    }

    public FrameworkMethod getMethod() {
        return method;
    }

    public void setMethod(FrameworkMethod method) {
        this.method = method;
    }

    public List<Object> getArgumentsToSave(Object returnValue) {
        Annotation[][] parameterAnnotations = method.getMethod().getParameterAnnotations();

        if (parameterAnnotations.length > 0) {
            List result = new ArrayList<>();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Object item = null;
                if (parameterAnnotations[i].length > 0) {
                    Annotation a = parameterAnnotations[i][0];
                    if (a.annotationType() != ParamExpected.class) {
                        result.add(arguments.get(i));
                    }
                }
            }

            if (returnValue != null) {
                result.add(returnValue);
            }

            return result;
        } else {
            return arguments;
        }
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public abstract void save(Object returnValue) throws Exception;

    public abstract String getPostName();

    public String getFilePath(String filePath, String fileExtension) {

        String result = null;

        File file = new File(filePath);

        if (file.isDirectory()) {
            result = filePath + "/" + getMethod().getName() + "_" + getPostName() + "." + fileExtension;
        } else {
            result = filePath;
        }

        return result;
    }
}
