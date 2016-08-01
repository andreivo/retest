/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 11:44:35 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.io.File;
import java.util.List;
import org.junit.experimental.theories.internal.Assignments;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.SaveBrokenTestDataFiles;

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

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public abstract void save() throws Exception;

    public abstract String getPostName();

    public String getFilePath(String fileExtension) {

        String result = null;

        SaveBrokenTestDataFiles an = getMethod().getAnnotation(SaveBrokenTestDataFiles.class);
        File file = new File(an.filePath());

        if (file.isDirectory()) {
            result = an.filePath() + "/" + getMethod().getName() + "_" + getPostName() + "." + fileExtension;
        } else if (file.isFile()) {
            result = an.filePath();
        }

        return result;
    }
}
