/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:44:35 PM
 * ****************************************************************
 */
package retest.datafile;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;

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
                if (parameterAnnotations[i].length > 0) {
                    result.add(arguments.get(i));
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
