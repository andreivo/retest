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
package initial.testdatadriven;

import initial.datafile.NewFileTest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.ReTest;
import org.retest.annotation.SaveSuccessTestDataFiles;
import org.retest.annotation.params.IntegerParam;

/**
 *
 * @author andreivo
 */
@RunWith(ReTestRunner.class)
public class DataFileTest {

    public DataFileTest() {
    }

    @Test
    @ReTest(10)
    @SaveSuccessTestDataFiles(formatClass = NewFileTest.class, filePath = "/tmp/testData.csv")
    public Integer testA(@IntegerParam Integer i1, @IntegerParam Integer i2) {
        int x = i1 + i2;
        return x;
    }

    @Test
    @LoadTestFromDataFiles(formatClass = NewFileTest.class, filePath = "/tmp/testData.csv")
    public void testB(@IntegerParam Integer i1,
            @IntegerParam Integer i2,
            @IntegerParam Integer r) {
        int x = i1 + i2;
        assertEquals(x, r, 0);
    }

}
