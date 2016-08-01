/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 28, 2016, 9:08:20 AM
 * ****************************************************************
 */
package org.retest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.ReTest;
import org.retest.datafile.DataFilePayload;
import org.retest.datafile.TestDataFiles;

/**
 *
 * @author andreivo
 */
public class ReTestRunner extends BlockJUnit4ClassRunner {

    private Object testInstance;
    private ArrayList<DataFilePayload> payloads;
    public static final String DESCRIBE_FILE = "FILE:";

    public ReTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        try {
            testInstance = getTestClass().getOnlyConstructor().newInstance();

        } catch (Exception e) {
            Assert.fail("Test failed while trying to instrument fileds in the class : " + getTestClass().getJavaClass());
        }
    }

    @Override
    protected void validateConstructor(List<Throwable> errors) {
        validateOnlyOneConstructor(errors);
    }

    @Override
    protected void validateTestMethods(List<Throwable> errors) {
        // Do Nothing as we now support public non void arg test methods
    }

    @Override
    public Statement methodBlock(final FrameworkMethod method) {
        return new ReTestStatement(method, getTestClass(), testInstance);
    }

    @Override
    protected String testName(final FrameworkMethod method) {
        return String.format("%s", method.getName());
    }

//    @Override
//    public void run(RunNotifier notifier) {
//        notifier.addListener(new ReTestRunnerListener());
//        super.run(notifier);
//    }
    @Override
    protected Description describeChild(FrameworkMethod method) {
        Description description = null;

        if (method.getAnnotation(ReTest.class) != null
                && method.getAnnotation(Ignore.class) == null) {
            description = Description.createSuiteDescription(
                    testName(method),
                    method.getAnnotations());

            description = describeRepeatTest(method, description);
        }

        if (method.getAnnotation(LoadTestFromDataFiles.class) != null
                && method.getAnnotation(Ignore.class) == null) {

            if (description == null) {
                description = Description.createSuiteDescription(
                        testName(method),
                        method.getAnnotations());
            }

            try {
                description = describeTestFromDataFiles(method, description);
            } catch (Exception ex) {
                Assert.fail(ex.getMessage());
            }
        }

        if (description != null) {
            return description;
        } else {
            return super.describeChild(method);
        }
    }

    private Description describeRepeatTest(FrameworkMethod method, Description description) {
        ReTest repeat = method.getAnnotation(ReTest.class);
        int times = repeat.times();
        for (int i = 1; i <= times; i++) {
            description.addChild(Description.createTestDescription(
                    getTestClass().getJavaClass(),
                    testName(method) + " " + ReTest.class.getSimpleName() + "[" + i + "]"));
        }
        return description;
    }

    private Description describeTestFromDataFiles(FrameworkMethod method, Description description) throws Exception {
        LoadTestFromDataFiles anDF = method.getAnnotation(LoadTestFromDataFiles.class);

        String[] filePaths = anDF.filePath();
        payloads = new ArrayList<>();
        for (int k = 0; k < filePaths.length; k++) {
            String file = filePaths[k];
            File ff = new File(file);
            if (ff.exists()) {
                Class<? extends TestDataFiles> formatClass = anDF.formatClass();
                TestDataFiles tdf = formatClass.newInstance();
                DataFilePayload loadDataFromFile = tdf.loadDataFromFile(file, method);

                for (int i = 0; i < loadDataFromFile.getSize(); i++) {
                    description.addChild(Description.createTestDescription(
                            getTestClass().getJavaClass(),
                            testName(method) + " " + DESCRIBE_FILE + "[" + (k + 1) + "]" + ff.getName() + "[" + (i + 1) + "]"
                    ));

                }
                payloads.add(loadDataFromFile);
            }
        }

        return description;
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);

        if (method.getAnnotation(ReTest.class) != null
                && method.getAnnotation(Ignore.class) == null) {

            runRepeatedly(methodBlock(method), description, notifier);
        } else {
            super.runChild(method, notifier);
        }
    }

    private void runRepeatedly(Statement statement, Description description,
            RunNotifier notifier) {
        for (Description desc : description.getChildren()) {

            desc.getDisplayName();

            if (desc.getDisplayName().contains(DESCRIBE_FILE)) {
                ((ReTestStatement) statement).setTestFromDataFile(true);
                if (payloads != null) {
                    int indexFile = getIndexFile(desc.getDisplayName());
                    int indexData = getIndexData(desc.getDisplayName());
                    ((ReTestStatement) statement).setTestPayload(payloads.get(indexFile).get(indexData));
                }
            } else {
                ((ReTestStatement) statement).setTestFromDataFile(false);
            }

            runLeaf(statement, desc, notifier);
        }
    }

    private int getIndexFile(String displayName) {
        String pattern = "\\[\\d+\\]";
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(displayName);
        if (m.find()) {
            String n = m.group(0).substring(1, m.group(0).length() - 1);
            return Integer.parseInt(n) - 1;
        }

        Assert.fail("Fail get index to file: " + displayName);
        return 0;
    }

    private int getIndexData(String displayName) {
        String pattern = "\\[\\d+\\]";
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(displayName);
        //Get next number on pattern FILE:[0890]testC_BrokenT33est.csv[39908]
        m.find();
        if (m.find()) {
            String n = m.group(0).substring(1, m.group(0).length() - 1);
            return Integer.parseInt(n) - 1;
        }

        Assert.fail("Fail get index to data: " + displayName);
        return 0;
    }
}
