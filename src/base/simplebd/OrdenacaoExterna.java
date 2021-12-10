import java.util.PriorityQueue;


public class OrdenacaoExterna {

    public static void main(String[] args) {

    }
/*TODO: ADAPTAR ESSA CLASSE AO CODIGO*/

    /**A CLASSE MULTIWAY FAZ O MERGE DE DOIS ARQUIVOS ORDENADOS ATRAVÉS DE UMA FILA DE PRIORIDADES
     * NO CASO, IREMOS UTILIZAR A LISTA DE PRIORIDADES DISPONÍVEL EM "java.util.PriorityQueue".*/

    /** PARA ESCRITA E LEITURA DOS ARQUIVOS PRETENDO UTILIZAR AS CLASSES "BinaryIn.java" e "BinaryOut.java */

    public class Multiway {

        // This class should not be instantiated.
        private Multiway() { }

        // merge together the sorted input streams and write the sorted result to standard output
        private static void merge(In[] streams) {
            int n = streams.length;
            IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
            for (int i = 0; i < n; i++)
                if (!streams[i].isEmpty())
                    pq.insert(i, streams[i].readString());

            // Extract and print min and read next from its stream.
            while (!pq.isEmpty()) {
                StdOut.print(pq.minKey() + " ");
                int i = pq.delMin();
                if (!streams[i].isEmpty())
                    pq.insert(i, streams[i].readString());
            }
            StdOut.println();
        }

        /**
         *  Reads sorted text files specified as command-line arguments;
         *  merges them together into a sorted output; and writes
         *  the results to standard output.
         *  Note: this client does not check that the input files are sorted.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            int n = args.length;
            In[] streams = new In[n];
            for (int i = 0; i < n; i++)
                streams[i] = new In(args[i]);
            merge(streams);
        }
    }


    /*=============================================================================*/


    public static void ordenacaoExterna() {
        /*TODO: PASSO 1:
         *  - ABRIR O BANCO DE DADOS PARA LEITURA.
         *  - CRIAR OS DOIS ARQUIVOS TEMPORARIOS ("tmp1" E "tmp2") ONDE OS REGISTROS ORDENADOS SERÃO GRAVADOS*/

        /*TODO: PASSO 2:
        *  - CARREGAR OS N REGISTROS (CADA N REGISTROS CORRESPONDE A UM BLOCO) NA MEMÓRIA PRINCIPAL E ORDENA-LOS*/

        /*TODO: PASSO 3:
         *  - OS BLOCOS ÍMPARES SERÃO GRAVADOS NO ARQUIVO TEMPORÁRIO 1
         *  - OS BLOCOS PARES SERÃO GRAVADOS NO ARQUIVO TEMPORÁRIO 2*/

        /*TODO: PASSOS 2 E 3 SERÃO REPETIDOS ATÉ QUE TODOS OS REGISTROS DO ARQUIVO PRINCIPAL SEJAM LIDOS.
        *  FIM DA ETAPA DE DISTRIBUIÇÃO.
        * OBS.: É NECESSÁRIO QUE O TAMANHO DOS BLOCOS FIQUE ARMAZENADO EM ALGUMA VARIÁVEL PARA UTILIZAÇÃO NAS ETAPAS POSTERIORES.*/

        /*TODO: PASSO 4 (INICIO DA ETAPA DE INTERCALAÇÃO):
        *   - OS ARQUIVOS TEMPORÁRIOS CRIADOS NAS ETAPAS ANTERIORES ("tmp1" E "tmp2") DEVERÃO SER ABERTOS COMO LEITURA (FONTES / FLUXOS DE ENTRADA)*/

        /*TODO: PASSO 5:
         *  - CRIAR OS DOIS ARQUIVOS TEMPORARIOS ( "tmp3" E "tmp4") ONDE OS SERÁ FEITA A INTERCALAÇÃO DOS ARQUIVOS ABERTOS NO PASSO ANTERIOR*/

        /*TODO: PASSO 6:
        *   - COMPARAR tmp1[0] com tmp2[0]
        *   - SE tmp1[0] < tmp2[0] = GRAVAR tmp1[0] EM tmp3[0]
        *   - SE tmp1[0] > tmp2[0] = GRAVAR tmp2[0] EM tmp3[0]
        *   - COMPARAR NOVAMENTE O REGISTRO QUE NÃO FOI GRAVADO COM O PRÓXIMO REGISTRO DO ARQUIVO TEMPORÁRIO QUE TEVE O REGISTRO GRAVADO
        *   - REPETIR O PROCESSO ATÉ QUE O TOTAL DE REGISTROS LIDOS SEJA IGUAL AO TAMANHO DO BLOCO DE LEITURA
        *   - O ULTIMO REGISTRO DO BLOCO DE LEITURA DEVERÁ SER GRAVADO DIRETO PARA EVITAR SER COMPARADO COM O PRIMEIRO REGISTRO DO PRÓXIMO BLOCO DE LEITURA*/

        /*TODO: PASSO 7
        *  - OS BLOCOS ÍMPARES SERÃO GRAVADOS NO ARQUIVO TEMPORÁRIO 3
        *  - OS BLOCOS PARES SERÃO GRAVADOS NO ARQUIVO TEMPORÁRIO 4*/

        /*TODO: PASSO 8
        *   - REPETIR OS PASSOS 4 A 7 ATÉ QUE TODOS OS REGISTROS DOS ARQUIVOS TEMPORÁRIOS "tmp1" E "tmp2" TENHAM SIDO INTERCALADOS.
        * */

        /*TODO: PASSO 9
        *  - REPETIR O PROCESSO DE INTERCALAÇÃO ATÉ QUE RESTE SOMENTE UM ARQUIVO ORDENADO CONTENDO TODOS OS REGISTROS
        *  - A CADA NOVA INTERCALAÇÃO OS ARQUIVOS TEMPORÁRIOS USADOS PARA LEITURA DEVERÃO SER LIMPOS/ELIMINADOS
        *  - A CADA ETAPA O NOSSO BLOCO DE LEITURA TERÁ SEU TAMANHO MODIFICADO SENDO O PADRÃO DE CRECIMENTO = A N*CAMINHOS
        *   - COMO ESTAMOS UTILIZANDO 2 CAMINHOS E NOSSO BLOCO DE LEITURA INICIAL É IGUAL A 4, ESTE IRÁ APRESENTAR O SEGUINTE PADRÃO:
        *           4 - 8 - 16 - 32 - 64 - ETC......*/

        /*TODO: PASSO 10
        *  - AO FINAL DO PROCESSO O ARQUIVO TEMPORARIO FINAL COM TODOS OS REGISTROS ORDENADOS DEVERÁ SER SALVO COMO "simpledb.db"*/

    }

}
