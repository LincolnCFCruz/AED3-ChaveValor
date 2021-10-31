package base.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;

        int option;
        boolean flag = true;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Opcao 1: Create");
        System.out.println("Opcao 2: Read");
        System.out.println("Opcao 3: Update");
        System.out.println("Opcao 4: Delete");
        System.out.println("Opcao 5: Exit");

        while (flag == true) {
            System.out.println("Digite sua opcao:");
            option = entrada.nextInt();

            switch (option) {
                case 1: //Create
                    try {
                        File file = new File("fileName.txt");

                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        PrintWriter pw = new PrintWriter(file);
                        int loop;
                        String conteudo;

                        System.out.println("Numero de linhas.");
                        loop = entrada.nextInt();
                        for (int i = 0; i<loop; i++) {
                            System.out.println("linha: ");
                            conteudo = entrada.next();
                            pw.print(conteudo);
                            pw.print("\n");
                        }
                        pw.close();
                        System.out.println("Done");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2: //Read
                    try {

                        br = new BufferedReader(new FileReader("fileName.txt"));
                        String line;

                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: //Update
                    File fileToBeModified = new File("fileName.txt");
                    String oldContent = "";
                    BufferedReader reader = null;
                    FileWriter writer = null;
                    System.out.println("Linha a ser alterada:");
                    String oldString;
                    oldString = entrada.next();
                    System.out.println("Nova linha: ");
                    String newString;
                    newString = entrada.next();

                    try{
                        reader = new BufferedReader(new FileReader(fileToBeModified));
                        String line = reader.readLine();
                        while (line != null) {
                            oldContent = oldContent + line + System.lineSeparator();
                            line = reader.readLine();
                        }
                        String newContent = oldContent.replaceAll(oldString,newString);
                        writer = new FileWriter((fileToBeModified));
                        writer.write(newContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try{
                            reader.close();
                            writer.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Utilize uma opcao valida.");
            }
        }
    }
}