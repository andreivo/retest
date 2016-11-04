/*
 * ****************************************************************
 *                        André Ivo
 *                   andre.ivo@gmail.com
 *           Created on : Oct 25, 2016, 4:09:37 PM
 * ****************************************************************
 */
package handson;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.params.RandomParam;

/**
 *
 * @author root
 */
@RunWith(ReTestRunner.class)
public class Soma10Numeros {

    @Test
    public void test1(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1, r);
        assertEquals(1, result.length);
    }

}
