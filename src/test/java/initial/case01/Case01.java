/*
 * The MIT License
 *
 * Copyright 2016 andreivo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package initial.case01;

import java.util.Arrays;
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.ReTest;
import org.retest.annotation.SaveBrokenTestDataFiles;
import org.retest.annotation.params.RandomParam;

/**
 *
 * @author andreivo
 */
@RunWith(ReTestRunner.class)
public class Case01 {

    @Test
    @Ignore
    @ReTest(100)
    @SaveBrokenTestDataFiles(filePath = "/tmp/testDataCase01.csv")
    public void test(@RandomParam Random r) throws InterruptedException {
        Thread.sleep(10);
        int[] arr = ArrayFactory.gerarArrayComSomatoriaZero(r, 3);
        assertElements(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    @LoadTestFromDataFiles(filePath = "/tmp/testDataCase01.csv")
    public void testLoad(@RandomParam Random r) throws InterruptedException {
        int[] arr = ArrayFactory.gerarArrayComSomatoriaZero(r, 3);
        assertElements(arr);
        System.out.println(Arrays.toString(arr));
    }

    private void assertElements(int[] arr) {
        int result = 0;
        for (int a : arr) {
            assertTrue(a >= -10 && a <= 10);
            result = result + a;
        }

        assertEquals(Arrays.toString(arr), 0, result);
    }
}
