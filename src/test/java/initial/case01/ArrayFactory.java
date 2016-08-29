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

import java.util.Random;

/**
 *
 * @author andreivo
 */
public class ArrayFactory {

    public static int[] gerarArrayComSomatoriaZero(Random r, int n) {
        int[] result = new int[n];

        //Gera numeros aleatórios
        int soma = 0;
        for (int i = 0; i < n; i++) {
            result[i] = (r.nextInt(20) - 10);
            soma = soma + result[i];
        }
               
        //Corrige a soma
        int i = 0;        
        while (soma != 0) {
            soma = soma - result[i];                        
            int valor_corrigido_elemento = 0;
            int numeros_elementos_necessarios = Math.abs(soma / 10);
            //Indica que serão necessários corrigir mais de um elemento
            if (numeros_elementos_necessarios >= 1) {
                valor_corrigido_elemento = 10 * (soma<=0 ? 1 : -1);
            }else
            {
                valor_corrigido_elemento = soma  * (-1);
            }
            //Atualiza a soma
            soma = soma+valor_corrigido_elemento;
            //Corrige o valor do elemento
            result[i] = valor_corrigido_elemento;
            i++;
        }
        return result;
    }

//    public static int[] gerarArrayComSomatoriaZero(Random r, int tamanho) {
//        int[] result = new int[tamanho];
//        result[0] = (r.nextInt(20) - 10);
//        result[1] = result[0] * -1;
//        return result;
//    }
}
