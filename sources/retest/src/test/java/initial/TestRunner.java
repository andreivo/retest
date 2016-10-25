/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 9, 2016, 8:06:04 AM
 * ****************************************************************
 */
package initial;

import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.annotation.ReTest;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.junit.Ignore;
import org.retest.annotation.SaveBrokenTestDataFiles;
import org.retest.annotation.SaveSuccessTestDataFiles;
import org.retest.annotation.params.RandomParam;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author andreivo
 */
@RunWith(ReTestRunner.class)
public class TestRunner {

    public TestRunner() {
    }

    @Test
    @Ignore
    public void testA() throws Exception {
        int x = 8;
        int y = 2;
        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    public void testB() throws Exception {
        int x = 9;
        int y = 2;
        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    public void testC(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        System.out.println("X = " + x);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    @ReTest(3)
    public void testD(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = "/tmp")
    public void testE(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = "/tmp")
    @LoadTestFromDataFiles(filePath = {"/tmp/testC_BrokenTest.csv"})
    public void testF(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = "/tmp")
    @SaveSuccessTestDataFiles(filePath = "/tmp")
    @LoadTestFromDataFiles(filePath = {"/tmp/testG_BrokenTest.csv", "/tmp/testG_SuccessTest.csv"})
    public void testG(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @Ignore
    @SaveBrokenTestDataFiles(filePath = "/tmp")
    @SaveSuccessTestDataFiles(filePath = "/tmp")
    //@LoadTestFromDataFiles(filePath = {"/tmp/testH_BrokenTest.csv", "/tmp/testH_SuccessTest.csv"})
    public void testH() throws Exception {
        Random r1 = new Random();
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }
}
