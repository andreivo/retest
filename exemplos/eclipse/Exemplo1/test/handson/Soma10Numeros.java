package test.handson;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.params.RandomParam;
import src.handson.ArrayFactory;

@RunWith(ReTestRunner.class)
public class Soma10Numeros {

    @Test
    public void test1(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1, r);
        assertEquals(1, result.length);
    }

}
