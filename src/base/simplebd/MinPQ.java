
/*Classe para comprimir/descomprimir o banco de dados com base
no algoritmo de Huffman.

Referências utilizadas para desenvolvimento desse algoritmo:

https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Huffman.html
https://www.ime.usp.br/~pf/estruturas-de-dados/aulas/huffman.html#Node
*/


/*---------------------------------------------------------------------------------
* BINARYSTDIN.JAVA*/

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/


/*----------------------------------------------------------------------------------
*
* BINARYSTDOUT.JAVA*/

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

/*--------------------------------------------------------------------------------
*   minPQ.JAVA*/

/*CLASSE PARA A IMPLEMENTAÇÃO DA FILA PRIORIZADA EM ORDEM CRESCENTE
 *
 * >> ref.:
 *
 * >>>> https://www.ime.usp.br/~pf/estruturas-de-dados/aulas/priority.html#intro
 * */

class MinPQ<Item extends Comparable <Item>> {

    private Item[] pq;
    private int N =0; //heap fica em pq [1...N]

    public MinPQ() { // Construtor
        pq = (Item[]) new Comparable[N];
    }

    public int size() {
        return N;
    }

    public void insert(Item v) {
        pq[++N] = v;
        swim(N);
    }

    public Item delMin() {
        Item max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Item t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


}
