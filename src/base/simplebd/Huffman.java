import java.util.PriorityQueue;

class Huffman {

    static BinaryOut output = new BinaryOut("C:\\Users\\conta\\Desktop\\tmp\\PUC\\Huffman\\simpledb.huffman");
    static BinaryIn input = new BinaryIn("C:\\Users\\conta\\Desktop\\tmp\\PUC\\Huffman\\simpledb.db");

    static BinaryIn  input2 = new BinaryIn("C:\\Users\\conta\\Desktop\\tmp\\PUC\\Huffman\\simpledb.huffman");
    static BinaryOut output2 = new BinaryOut("C:\\Users\\conta\\Desktop\\tmp\\PUC\\Huffman\\simpledbex.db");

    public static void main(String[] args) {

        if (args[0].equals("-")) {

            compress(input);

        }
        else if (args[0].equals("+")){

            expansor(input2);

        }
        else throw new IllegalArgumentException("Illegal command line argument");

    }

    /*metodo que realiza a compressao:
    * Recebe uma cadeia de bits, realizando a leitura de cada bit convertendo em um array de char*/
    public static void compress(BinaryIn in) {
        String entrada = in.readString();
        char[] mensagem = entrada.toCharArray();            //converte a mensagem recebida em um array de char.

        NoTrie trie = criaTrie(mensagem);                   //cria a trie baseada na frequencia de cada caractere.

        escreveTrie(trie);                                  //grava a trie no arquivo de saida

        output.write(mensagem.length);                //grava o tamanho da mensagem no arquivo de saida

        String[] tc = criaTabelaDeCodigos(trie);            //cria a tabela de codigos para compressao

        for (char c : mensagem) {
            output.write(tc[c]);                           //escreve cada caractere codificado no arquivo de saida
            output.close();
        }
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

    /*Metodo responsavel por escrever a trie no arquivo codificado para futura decodificacao*/
    private static void escreveTrie(NoTrie n) {

        if (n.ehFolha()) {
            output.write(true);
            output.write(n.simbolo);
        } else {
            output.write(false);
            escreveTrie(n.esq);
            escreveTrie(n.dir);
        }
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
    public static void expansor(BinaryIn in) {
        NoTrie trie = leTrie(in);                                       // carrega os bits do arquivo para descompressao
        int n = in.readInt();                                           //Tamanho da mensagem armazenada no arquivo
        for (int i = 0; i < n; ++i) {
            NoTrie no = trie;
            do {
                if (in.readBoolean()) {
                    no = no.dir;
                } else
                    no = no.esq;
            } while (!no.ehFolha());
            output2.write(no.simbolo);
        }
        output2.close();
    }

    /*metodo para ler a trie gravada no arquivo comprimido*/
    private static NoTrie leTrie(BinaryIn in) {

        if (in.readBoolean()) {
            return new NoTrie(in.readChar());
        } else {
            return new NoTrie(leTrie(in), leTrie(in));
        }
    }




}


