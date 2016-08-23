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

import java.util.Random;
import org.retest.datatype.DataType;

/**
 *
 * @author andreivo
 */
public class PokemonDataType extends DataType<Pokemon> {

    @Override
    public String serialize(Pokemon value) {
        return "Pokemon{name=" + value.getName() + ", cp=" + value.getCp() + "}";
    }

    @Override
    public Pokemon deserialize(String value) {
        int iname0 = value.indexOf("name");
        int iname1 = value.indexOf(",");
        String sname = value.substring(iname0 + 5, iname1);

        String scp = value.substring(iname1 + 1);
        int icp0 = scp.indexOf("cp");
        int icp1 = scp.indexOf("}");
        scp = scp.substring(icp0 + 3, icp1);
        Integer cp = Integer.parseInt(scp);

        return new Pokemon(sname, cp);
    }

    @Override
    public Pokemon randomizeParam() {
        try {
            Thread.sleep(15);
        } catch (InterruptedException ex) {
        }

        String[] namesPoke = {"Pikachu", "Ratata", 
                              "Charmander", "Bulbasaur", 
                              "Ivysaur", "Venusaur"};

        Random r1 = new Random(System.currentTimeMillis());
        int iPo = r1.nextInt(5);

        String name = namesPoke[iPo];

        Random r2 = new Random(System.currentTimeMillis());
        int cp = r2.nextInt(5000);

        return new Pokemon(name, cp);
    }
}
