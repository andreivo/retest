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

import static org.junit.Assert.*;
import java.util.Random;
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
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = "/tmp/testDataCase01.csv")
    @LoadTestFromDataFiles(filePath = "/tmp/testDataCase01.csv")
    public void test0(@RandomParam Random r) throws InterruptedException {
        r.nextFloat();
        //int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1, r);
        fail();
    }

    @Test
    public void test1(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1, r);
        assertEquals(1, result.length);
    }

    @Test
    @ReTest(10)
    //@SaveBrokenTestDataFiles(filePath = "/tmp/dataCase01.csv")
    //@LoadTestFromDataFiles(filePath = "/tmp/dataCase01.csv")
    public void test2(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(2, r);
        assertEquals(2, result.length);
        assertElements(result, 2);
    }

    @Test
    @ReTest(10)
    public void test3(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(3, r);
        assertEquals(3, result.length);
        assertElements(result, 3);
    }

    @Test
    @ReTest(10)
    public void test4(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1000, r);
        assertEquals(1000, result.length);
        assertElements(result, 1000);
    }
//
//
//    @Test
//    @ReTest(10)
//    //@SaveBrokenTestDataFiles(filePath = "/tmp/testDataCase01.csv")
//    @LoadTestFromDataFiles(filePath = "/tmp/testDataCase01.csv")
//    public void test2(@RandomParam Random r) {
//        int n = 2;
//        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(r, n);
//        assertElements(result, n);
//    }
//
//    @Test
//    @ReTest(10)
//    //@SaveBrokenTestDataFiles(filePath = "/tmp/testDataCase01.csv")
//    //@LoadTestFromDataFiles(filePath = "/tmp/testDataCase01.csv")
//    public void test3(@RandomParam Random r) throws InterruptedException {
//        Thread.sleep(10);
//        int n = 3;
//        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(r, n);
//        assertElements(result, n);
//    }
//

    private void assertElements(int[] arr, int tamanho) {
        int result = 0;
        for (int i = 0; i < tamanho; i++) {
            //Verifica se todos os elementos estão entre -10 e 10
            assertTrue(arr[i] >= -10 && arr[i] <= 10);
            result = result + arr[i];
        }
        //Verifica se a soma é igual a zero
        assertEquals(0, result);
    }
}
