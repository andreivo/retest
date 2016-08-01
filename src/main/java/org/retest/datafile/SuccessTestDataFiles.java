/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 8:24:42 PM
 * ****************************************************************
 */
package org.retest.datafile;

import java.io.IOException;
import java.util.List;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.SaveSuccessTestDataFiles;

/**
 *
 * @author andreivo
 */
public class SuccessTestDataFiles extends AbstractTestDataFiles {

    private static final String POSTNAME = "SuccessTest";

    public SuccessTestDataFiles(FrameworkMethod method, List<Object> arguments) {
        super(method, arguments);
    }

    @Override
    public void save() throws IOException, InstantiationException, IllegalAccessException, Exception {
        if (getMethod().getAnnotation(SaveSuccessTestDataFiles.class) != null) {
            SaveSuccessTestDataFiles an = getMethod().getAnnotation(SaveSuccessTestDataFiles.class);
            TestDataFiles tdf = an.formatClass().newInstance();
            tdf.save(getFilePath(tdf.getFileExtension()), getMethod(), getArguments());
        }
    }

    @Override
    public String getPostName() {
        return POSTNAME;
    }
}
