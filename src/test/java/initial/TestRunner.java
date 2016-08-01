/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Jul 9, 2016, 8:06:04 AM
 * ****************************************************************
 */
package initial;

import org.retest.randomizer.IntegerRandomizer;
import java.security.SecureRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.annotation.ReTest;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.params.Param;
import org.retest.randomizer.SecureRandomSeedRandomizer;
import static org.junit.Assert.assertEquals;
import org.retest.annotation.SaveBrokenTestDataFiles;

/**
 *
 * @author andreivo
 */
@RunWith(ReTestRunner.class)
public class TestRunner {

    public TestRunner() {
    }

    @Test
    public void testA() throws Exception {

        int x = 8;
        int y = 2;

        //Thread.sleep(100);
        int z = x / y;

        assertEquals(4, z);
    }

    @Test
    @ReTest(times = 4)
    //@SaveBrokenTestDataFiles(filePath = "/tmp")
    //@SaveSuccessTestDataFiles(filePath = "/tmp")
    @LoadTestFromDataFiles(filePath = {"/tmp/testC_BrokenTest.csv", "/tmp/testC_SuccessTest.csv"})
    //public void testC(Random r, SecureRandom r1) throws Exception {
    //public void testC(@RandomParam Random r, @SecureRandomParam SecureRandom r1) throws Exception {
    public void testC(@Param(randomizerClass = IntegerRandomizer.class) Integer r,
            @Param(randomizerClass = SecureRandomSeedRandomizer.class) SecureRandom r1) throws Exception {

        int x = r;//.nextInt(1000);
        int y = 2;

        int z = x / y;
        int j = z * y;

        //System.out.println(x);
        assertEquals(x, j);
    }
}
