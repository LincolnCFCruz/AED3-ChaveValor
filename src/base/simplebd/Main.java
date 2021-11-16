package base.simplebd;

import java.util.*;

public class Main {
    public static void main(String[] args) {
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