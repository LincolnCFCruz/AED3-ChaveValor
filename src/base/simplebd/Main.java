package base.simplebd;

import java.io.IOException;
import java.util.*;

import static base.simplebd.Lzw.compress;
import static base.simplebd.Lzw.decompress;

public class Main {
    public static void main(String[] args) throws IOException {
        int teste=0;
        teste=1;
        if(teste==1){
        test();
        }
        else{

            if(args.length==0 || args[0].equals("--help")){
                System.out.println("simpledb [cmd]\n" +
                        "  --insert=<sort-key,value> \n      Insere um objeto no banco de dados.\n" +
                        "  --remove=<key>\n      Remove do banco de dados o objeto identificado pela chave key.\n" +
                        "  --search=<key>\n      Busca no banco de dados objeto identificado pela chave key.\n" +
                        "  --update=<key,sort-key,value>\n      Atualiza o objeto que e identificado pela chave key. \n" +
                        "  --list=<expr>\n      Lista em ordem crescente todos os objetos que possam uma chave de ordenação que atenda aos critérios especificados pela expressão 'expr'.\n" +
                        "  --reverse-list=<expr>\n      Lista em ordem decrescente  todos os objetos que possam uma chave de ordenação que atenda aos critérios especificados pela expressão 'expr'.\n" +
                        "  * A expressão 'expr' deve aceitar qualquer operação logica simples envolvendo a chave:\n" +
                        "      key>X: objetos que possuem chave de ordenação maior que X.\n" +
                        "      key<X: objetos que possuem chave de ordenação menor que X.\n" +
                        "      key=X: objetos que possuem chave ordenação igual a X.\n" +
                        "      key>=X: objetos que possuem chave de ordenação maior ou igual que X.\n" +
                        "      key<=X: objetos que possuem chave de ordenação menor ou igual que X.\n" +
                        "  --compress=[huffman|lzw]\n      Compacta os registros do banco de dados usando o algoritmo de Codificação de Huffman ou o Algoritmo de Compressão LZW. \n" +
                        "  --decompress=[huffman|lzw]\n      Descompacta os registros do banco de dados usando o algoritmo de Codificação de Huffman ou o Algoritmo de Compressão LZW. \n");
            } else{
                String[] arg= args[0].split("=");
                System.out.println(arg[0]);
                if(arg.length==1){
                    System.out.println("Faltando argumentos para as operações. \nPara mais informações de uso, utilize a opção --help");
                }
                switch (arg[0]){
                    case "--insert":
                        String[] insertArg= arg[1].split(",");
                        if(insertArg.length==2) {

                            //crud.insert(insertArg[0],insertArg[1]);   // sort-key,value
                            System.out.println("A função insert ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Número de argumentos incorreto para a operação. \nPara mais informações de uso, utilize a opção --help");
                        }
                        break;
                    case "--remove":
                        // crud.remove(arg[1]);    // arg[1] -> key
                        System.out.println("A função remove ainda não foi desenvolvida hehe");
                        break;
                    case "--search":
                        // crud.search(arg[1]);    // arg[1] -> key
                        System.out.println("A função search ainda não foi desenvolvida hehe");
                    break;
                    case "--update":
                        String[] updateArg= arg[1].split(",");
                        if(updateArg.length==3){
                            // crud.update(updateArg[0],updateArg[1],updateArg[2]); //key,sort-key,value
                            System.out.println("A função update ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Número de argumentos incorreto para a operação. \nPara mais informações de uso, utilize a opção --help");
                        }

                    break;
                    case "--list":
                        String[] n;
                        if(arg[1].matches("key<[0-9]+")){
                            n=arg[1].split("<");
                            // listmenorque(n[1]);
                            System.out.println("A função list '<' ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].matches("key>[0-9]+")){
                            n=arg[1].split(">");
                            // listmaiorque(n[1]);
                            System.out.println("A função list '>' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("key") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            // listigual(arg[2]);
                            System.out.println("A função list '=' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("key<") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            //listmenorigual(arg[2]);
                            System.out.println("A função list '<=' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("key>") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            //listmaiorigual(arg[2]);
                            System.out.println("A função list '>=' ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }
                    break;
                    case "--reverse-list":
                        if(arg[1].matches("key<[0-9]+")){
                            n=arg[1].split("<");
                            // reverselistmenorque(n[1]);
                            System.out.println("A função reverse-list '<' ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].matches("key+>[0-9]+")){
                            n=arg[1].split(">");
                            // reverselistmaiorque(n[1]);
                            System.out.println("A função reverse-list '>' ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].matches("key") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            // reverselistigual(arg[2]);
                            System.out.println("A função reverse-list '=' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("key<") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            System.out.println("A função reverse-list '<=' ainda não foi desenvolvida hehe");
                            //reverselistmenorigual(arg[2]);
                        }
                        else if(arg.length==3 && arg[1].matches("key+>") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            //reverselistmaiorigual(arg[2]);
                            System.out.println("A função reverse-list '>=' ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }

                    break;
                    case "--compress":
                        if(arg[1].equals("lzw") ){
                            //compresslzw();
                            System.out.println("A função de compactação utilizando o o algoritmo LZW ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].equals("huffman")){
                            //compresshuffman();
                            System.out.println("A função de compactação utilizando o o algoritmo Huffman ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }
                    break;
                    case "--decompress":
                        if(arg[1].equals("lzw") ){
                            //decompresslzw();
                            System.out.println("A função de descompactação utilizando o o algoritmo LZW ainda não foi desenvolvida ");
                        }
                        else if(arg[1].equals("huffman")){
                            //decompresshuffman();
                            System.out.println("A função de descompactação utilizando o o algoritmo Huffman ainda não foi desenvolvida ");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }
                    break;
                    default:
                        System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                    break;
                }
            }
        }
    }

    public static void test() throws IOException {
        List<Integer> compressed = compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(compressed);
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }
}