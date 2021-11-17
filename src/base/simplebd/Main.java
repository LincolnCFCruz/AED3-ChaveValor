package base.simplebd;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int teste=0;
        //teste=1;
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
                            //insert(insertArg[0],insertArg[1]);   // sort-key,value
                            System.out.println("A função insert ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Número de argumentos incorreto para a operação. \nPara mais informações de uso, utilize a opção --help");

                        }
                        break;
                    case "--remove":
                        //remove(arg[1]);    // arg[1] -> key
                        System.out.println("A função remove ainda não foi desenvolvida hehe");
                        break;
                    case "--search":
                        //search(arg[1]);    // arg[1] -> key
                        System.out.println("A função search ainda não foi desenvolvida hehe");
                    break;
                    case "--update":
                        String[] updateArg= arg[1].split(",");
                        if(updateArg.length==3){
                            //update(updateArg[0],updateArg[1],updateArg[2]); //key,sort-key,value
                            System.out.println("A função update ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Número de argumentos incorreto para a operação. \nPara mais informações de uso, utilize a opção --help");
                        }

                    break;
                    case "--list":
                        if(arg[1].matches("[0-9]+<[0-9]+")){
                            System.out.println("A função list '<' ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].matches("[0-9]+>[0-9]+")){
                            System.out.println("A função list '>' ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].matches("[0-9]+") && arg[2].matches("[0-9]+")){ //lembrar que o '=' foi "comido" no split
                            System.out.println("A função list '=' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("[0-9]{1,}<") && arg[2].matches("[0-9]{1,}")){ //lembrar que o '=' foi "comido" no split
                            System.out.println("A função list '<=' ainda não foi desenvolvida hehe");
                        }
                        else if(arg.length==3 && arg[1].matches("[0-9]{1,}>") && arg[2].matches("[0-9]{1,}")){ //lembrar que o '=' foi "comido" no split
                            System.out.println("A função list '>=' ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }
                    break;
                    case "--reverse-list":
                    //
                    break;
                    case "--compress":
                        if(arg[1].equals("lzw") ){
                            System.out.println("A função de compactação utilizando o o algoritmo LZW ainda não foi desenvolvida hehe");
                        }
                        else if(arg[1].equals("huffman")){
                            System.out.println("A função de compactação utilizando o o algoritmo Huffman ainda não foi desenvolvida hehe");
                        }
                        else{
                            System.out.println("Opção não contemplada no simpledb\nPara mais informações utilize a opção --help");
                        }
                    break;
                    case "--decompress":
                        if(arg[1].equals("lzw") ){
                            System.out.println("A função de descompactação utilizando o o algoritmo LZW ainda não foi desenvolvida ");
                        }
                        else if(arg[1].equals("huffman")){
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
    public static void test() {
        int option,k,sK;
        long pos;
        boolean flag = true;
        String value;

        Scanner entrada = new Scanner(System.in);
        HashExtensivel hash = new HashExtensivel();
        CRUD crud = new CRUD();

        System.out.println("Opcao 1: Create");
        System.out.println("Opcao 2: Insert");
        System.out.println("Opcao 3: Remove");
        System.out.println("Opcao 4: Search");
        System.out.println("Opcao 5: Update");
        System.out.println("Opcao 6: Read");
        System.out.println("Opcao 7: Hash");
        System.out.println("Opcao 0: Exit");

        while (flag == true) {
            System.out.println("Digite sua opcao:");
            option = entrada.nextInt();

            switch (option) {
                case 1:
                    crud.create();
                    break;
                case 2:
                    crud.insert(sK = entrada.nextInt(), value = entrada.next());
                    break;
                case 3:
                    crud.remove(k=entrada.nextInt());
                    hash.removeHash(k);
                    break;
                case 4:
                    crud.search(pos = entrada.nextLong());
                    break;
                case 5:
                    crud.update(k = entrada.nextInt(), sK = entrada.nextInt(), value = entrada.next());
                    break;
                case 6:
                    crud.list();
                    System.out.println("\nBucket List \n");
                    System.out.println(hash.bucket);
                    break;
                case 7:
                    hash.insert();
                    hash.ordenaHash();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Utilize uma opcao valida.");
            }
        }
    }
}