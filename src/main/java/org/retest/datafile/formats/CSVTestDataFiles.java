/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 7:47:26 PM
 * ****************************************************************
 */
package org.retest.datafile.formats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.junit.runners.model.FrameworkMethod;
import org.retest.annotation.params.Param;
import org.retest.annotation.params.RandomParam;
import org.retest.annotation.params.SecureRandomParam;
import org.retest.datafile.DataFilePayload;
import org.retest.datafile.TestDataFiles;

/**
 *
 * @author andreivo
 */
public class CSVTestDataFiles extends TestDataFiles {

    private static final String EXT = "csv";
    private static final String DELIM = ";";

    @Override
    public String getFileExtension() {
        return EXT;
    }

    @Override
    public void save(String filePath, FrameworkMethod method, List<Object> arguments) throws IOException {
        saveHeader(filePath, method);
        saveBodyItem(filePath, arguments);
    }

    private void saveBodyItem(String filePath, List<Object> arguments) throws IOException {
        File file = new File(filePath);

        StringBuilder content = new StringBuilder();
        String delim = "";
        for (Object o : arguments) {
            content.append(delim).append(o.toString());
            delim = DELIM;
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(content.toString());
        bw.close();
    }

    private void saveHeader(String filePath, FrameworkMethod method) throws IOException {

        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuilder content = new StringBuilder();
            String delim = "";

            for (Annotation[] aa : method.getMethod().getParameterAnnotations()) {
                for (Annotation a : aa) {
                    String valueName = null;
                    if (a.annotationType() == Param.class) {
                        valueName = ((Param) a).name();
                    } else if (a.annotationType() == RandomParam.class) {
                        valueName = ((RandomParam) a).name();
                    } else if (a.annotationType() == SecureRandomParam.class) {
                        valueName = ((SecureRandomParam) a).name();
                    }

                    if (valueName.isEmpty()) {
                        Class<? extends Annotation> type = a.annotationType();
                        valueName = type.getSimpleName();
                    }

                    content.append(delim).append(valueName);

                    delim = DELIM;
                }
            }

            bw.write(content.toString());
            bw.close();
        }

    }

    @Override
    public DataFilePayload loadDataFromFile(String filePath, FrameworkMethod method) throws IOException, InstantiationException, IllegalAccessException {
        DataFilePayload result = new DataFilePayload(filePath);
        String thisLine;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //FirstLine is a header, get the next
        br.readLine();
        while ((thisLine = br.readLine()) != null) {
            result.addItem(convertData(thisLine, method));
        }
        br.close();
        return result;
    }

    @Override
    public List<Object> convertData(String record, FrameworkMethod method) throws InstantiationException, IllegalAccessException {
        String[] split = record.split(DELIM);        
        List<Object> result = null;
        result = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            Object item = convertToObject(method, split[i], i);
            result.add(item);
        }
        return result;
    }
}
