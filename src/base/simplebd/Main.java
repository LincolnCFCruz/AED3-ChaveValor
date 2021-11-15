package base.simplebd;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        int option;
        boolean flag = true;
        Scanner entrada = new Scanner(System.in);
        HashExtensivel hash = new HashExtensivel();
        CRUD crud = new CRUD();

        System.out.println("Opcao 1: Create");
        System.out.println("Opcao 2: Insert");
        System.out.println("Opcao 3: Remove");
        System.out.println("Opcao 4: Search");
        System.out.println("Opcao 5: Update");
        System.out.println("Opcao 6: Read");
        System.out.println("Opcao 0: Exit");

        while (flag == true) {
            System.out.println("Digite sua opcao:");
            option = entrada.nextInt();

            switch (option) {
                case 1: //Create
                    crud.create();
                    break;
                case 2: //Insert
                    crud.insert();
                    break;
                case 3: //Remove
                    crud.remove(4);
                    hash.removeHash(13);

                    break;
                case 4: //Read

                   // crud.search(hash.reverseSearchHash(3));
                    hash.reverseHash();
                    hash.ordenaHash();
                    break;
                case 5: //Delete
                   crud.update("1","10","tudo bem aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                   //hash.updateHash(2, 8);
                    System.out.println(hash.bucket);
                    break;
                case 6:
                    //crud.read();
                   // hash.list();
                    break;
                case 0:

                   hash.insert();
                   hash.ordenaHash();
                   //ash.updateHash(1,2);
                    //flag = false;
                    break;
                default:
                    System.out.println("Utilize uma opcao valida.");
            }
        }
    }
}