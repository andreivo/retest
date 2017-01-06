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
package initial.newrandom;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import retest.ReTestRunner;
import retest.annotation.LoadTestFromDataFiles;
import retest.annotation.ReTest;
import retest.annotation.SaveBrokenTestDataFiles;
import retest.annotation.params.Param;

/**
 *
 * @author andreivo
 */
@RunWith(ReTestRunner.class)
public class NovosRandomizadores {

    @Test
    @Ignore
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = "/tmp/testData.csv")
    public void testPokemon(@Param(dataTypeClass = PokemonDataType.class) Pokemon pk1,
                            @Param(dataTypeClass = PokemonDataType.class) Pokemon pk2) {

        Battle b = new Battle(pk1, pk2);
        Pokemon winner = b.fight();

        assertTrue(pk1.getName() + "(" + pk1.getCp() + "cp) failed against " + 
                   pk2.getName() + "(" + pk2.getCp() + "cp)",
                winner.equals(pk1));
    }

    @Test    
    @LoadTestFromDataFiles(filePath = "/tmp/testData.csv")
    public void testPokemonLoad(@Param(dataTypeClass = PokemonDataType.class) Pokemon pk1,
                                @Param(dataTypeClass = PokemonDataType.class) Pokemon pk2) {

        Battle b = new Battle(pk1, pk2);
        Pokemon winner = b.fight();

        assertTrue(pk1.getName() + "(" + pk1.getCp() + "cp) failed against " + 
                   pk2.getName() + "(" + pk2.getCp() + "cp)",
                winner.equals(pk1));
    }

}
