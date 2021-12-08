import java.util.PriorityQueue;

class HuffmanDe {

    static BinaryIn in2 = new BinaryIn("simpledb.huffman");
    static BinaryOut out2 = new BinaryOut("simpledbdec.db");

    public static void main(String[] args) {

        if (args[0].equals("-")) {


        }
        else if (args[0].equals("+")){

            decompresshuffman();

        }
        else throw new IllegalArgumentException("Illegal command line argument");

    }



    //Classe referente aos nos da trie
    static class NoTrie implements Comparable<NoTrie> {

        char simbolo;
        int frequencia;
        NoTrie esq;
        NoTrie dir;

        //Construtores:

        NoTrie(char simbolo) {
            this.simbolo=simbolo;
        }
        NoTrie(char simbolo, int frequencia) {
            this.simbolo = simbolo;
            this.frequencia = frequencia;
        }
        NoTrie(int frequencia, NoTrie esq, NoTrie dir) {
            this.frequencia = frequencia;
            this.esq = esq;
            this.dir = dir;
        }

        NoTrie(NoTrie esq, NoTrie dir) {
            this.esq = esq;
            this.dir = dir;
        }

        //metodo auxiliar para verificar se um no e folha
        boolean ehFolha() {
            return esq == null && dir == null;
        }

        //Para auxiliar na construcao da Trie ordena da frequencia mais alta para a mais baixa
        @Override
        public int compareTo(NoTrie o) {
            return this.frequencia - o.frequencia;
        }
    }

    /*Metodo que recebe uma mensagem e cria uma trie para essa mensagem com base
     * na frequencia de caracteres*/
    private static NoTrie criaTrie(char[] mensagem) {

        int[] freq = new int [256];                     //dicionario para determinacao da frequencia de cada simbolo.

        for (char c : mensagem) {
            ++freq[c];                                  //varre todos os caracteres da mensagem contando a frequencia de cada um destes.
        }

        PriorityQueue<NoTrie> tries = new PriorityQueue<>();

        for (char c = 0; c < 256; ++c) {
            if (freq[c] > 0) {
                tries.add(new NoTrie(c, freq[c]));      //caso a frequencia seja maior que zero, adiciona o simbolo a trie
            }
            while (tries.size()>1) {                    //Combina duas tries para a criacao de um novo no na arvore
                NoTrie t1 = tries.remove();
                NoTrie t2 = tries.remove();
                tries.add(new NoTrie(t1.frequencia + t2.frequencia, t1, t2));
            }
        }
        return tries.remove();                          //remove a ultima trie presente na lista e retorna.
    }



    /*Metodo para criacao da tabela de codigos a partir da trie criada*/
    private static String[] criaTabelaDeCodigos(NoTrie n) {
        String[] tc = new String[256];
        construirTabela(tc, n, "");
        return tc;
    }

    private static void construirTabela(String[] tc, NoTrie n, String s) {
        if (n.ehFolha()) {
            tc[n.simbolo] = s;
            return;
        }
        construirTabela(tc, n.esq, s + '0');
        construirTabela(tc, n.dir, s + '1');
    }

    /*Metodo para decodificar o arquivo comprimido*/
    public static void decompresshuffman() {
        NoTrie trie = leTrie(in2);                                       // carrega os bits do arquivo para descompressao
        int n = in2.readInt();                                           //Tamanho da mensagem armazenada no arquivo
        for (int i = 0; i < n; ++i) {
            NoTrie no = trie;
            do {
                if (in2.readBoolean()) {
                    no = no.dir;
                } else
                    no = no.esq;
            } while (!no.ehFolha());
            out2.write(no.simbolo);
        }
        out2.close();
    }

    /*metodo para ler a trie gravada no arquivo comprimido*/
    private static NoTrie leTrie(BinaryIn inb) {

        if (inb.readBoolean()) {
            return new NoTrie(inb.readChar());
        } else {
            return new NoTrie(leTrie(inb), leTrie(inb));
        }
    }




}


