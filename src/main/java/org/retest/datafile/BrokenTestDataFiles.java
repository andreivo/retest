/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 8:03:38 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.SaveBrokenTestDataFiles;

/**
 *
 * @author andreivo
 */
public class BrokenTestDataFiles extends AbstractTestDataFiles {

    private static final String POSTNAME = "BrokenTest";

    public BrokenTestDataFiles(FrameworkMethod method, List<Object> arguments) {
        super(method, arguments);
    }

    @Override
    public void save() throws IOException, InstantiationException, IllegalAccessException, Exception {
        if (getMethod().getAnnotation(SaveBrokenTestDataFiles.class) != null) {
            if (getArguments() != null) {
                SaveBrokenTestDataFiles an = getMethod().getAnnotation(SaveBrokenTestDataFiles.class);
                TestDataFiles tdf = an.formatClass().newInstance();
                tdf.save(getFilePath(tdf.getFileExtension()), getMethod(), getArguments());
            } else {
                System.out.println("Data not found : annotation " + SaveBrokenTestDataFiles.class.getSimpleName() + " cannot be used.");
            }
        }
    }

    @Override
    public String getPostName() {
        return POSTNAME;
    }

}
