package base.simplebd;

import java.io.*;
import java.util.*;

public class Main {
    public static void create () {
        try {
            File file = new File("simpledb.db");

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Done");
            } else { System.out.println("Arquivo ja existe."); }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insert () {
        File fArquivo = null;
        FileWriter fwArquivo = null;
        Scanner sc2 = null;
        BufferedWriter bw = null;
        BufferedReader br =  null;

        try {
            fArquivo = new File("simpledb.db");

            if (fArquivo.exists() == true) {
                fwArquivo = new FileWriter(fArquivo, true);
            } else {
                fwArquivo = new FileWriter(fArquivo);
            }

            sc2 = new Scanner(System.in);
            bw = new BufferedWriter(fwArquivo);
            br = new BufferedReader(new FileReader("simpledb.db"));

            String line;
            int index = 1;

            while ((line = br.readLine()) != null){
                String [] trimmed = line.split(";");
                String key = trimmed [0];

                if (key != null) {
                    index = Integer.parseInt(key);
                    index++;
                } else if (key == null){
                    index = 1;
                }
            }
            System.out.println("Teste: "+ index);

            System.out.println("Type: ");
            int type;
            type = Integer.parseInt(sc2.nextLine());

            System.out.println("Value: ");
            String value;
            value = sc2.nextLine();

            bw.write("\n" + index + ";");
            bw.write(type + ";");
            bw.write(value);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
                fwArquivo.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void remove () {
        BufferedReader countBR = null;
        BufferedReader reader = null;
        FileWriter my_writer = null;

        String filePath = "simpledb.db";
        String fileTemp = "simpledbTemp.db";

        try {
            File index_file = new File(filePath);
            File main_file = new File(filePath);
            File temp_file = new File(fileTemp);

            countBR = new BufferedReader(new FileReader(index_file));
            reader = new BufferedReader(new FileReader(main_file));
            my_writer = new FileWriter(temp_file);

            Scanner sc4 = new Scanner(System.in);

            System.out.println("Delete: ");
            int value = sc4.nextInt();
            String lineToRemove = String.valueOf(value);
            String current_line;
            String line;
            int count = 0;

            while((line = countBR.readLine()) != null) {
                count ++;
            }

            count=count-1;

            while((current_line = reader.readLine()) != null){
                String [] trimmed = current_line.split(";");
                String key = trimmed [0];

                if(!key.equals(lineToRemove)){
                    if(count != 0){
                        my_writer.write(current_line + "\n");
                        count--;
                        System.out.println("NOT LAST : " + current_line + " -- Count: " + count);
                    }
                    if(count == 0){
                        my_writer.write(current_line);
                        System.out.println("LAST : " + current_line + " -- Count: " + count);
                    }
                }
            }

            my_writer.close();
            countBR.close();
            reader.close();

            index_file.delete();
            temp_file.renameTo(index_file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void search (Long pos) {
        RandomAccessFile br2 = null;
        String line;
        String sortKey;
        String value;

        try{
            br2 = new RandomAccessFile("simpledb.db", "rw");

            br2.seek(pos);
            line = br2.readLine();
            String[] trimmed = line.split(";");
            sortKey = trimmed[1];
            value = trimmed[2];

            System.out.println(sortKey +" - "+ value);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update (String key, String newsortKey, String value) {
        BufferedReader br = null;
        FileWriter writer = null;

        try{
           // Scanner sc3 = new Scanner(System.in);
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;
            String newString = "";
            String oldString = "";
            String oldContent = "";
            String updateLine = key;

            while ((line = br.readLine()) != null) {
                oldContent = oldContent + line + System.lineSeparator();

                String [] trimmed = line.split(";");
                String keyA = trimmed [0];

                if (keyA.equals(updateLine)){
                    oldString = line;

                    System.out.println("TypeUpdate: ");
                    String sortKeyUpdate;
                    sortKeyUpdate = newsortKey;

                    System.out.println("ValueUpdate: ");
                    String valueUpdate;
                    valueUpdate = value;

                    newString = key + ";" + sortKeyUpdate + ";" + valueUpdate;
                }

                String newContent = oldContent.replaceAll(oldString,newString);
                writer = new FileWriter(("simpledb.db"));
                writer.write(newContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void read () {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("simpledb.db"));
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
    }

    public static void main(String[] args) {
        int option;
        boolean flag = true;
        Scanner entrada = new Scanner(System.in);
        HashExtensivel hash = new HashExtensivel();

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
                    create();
                    break;
                case 2: //Insert
                    insert();
                    break;
                case 3: //Remove
                    remove();
                    break;
                case 4: //Read
                    search(hash.searchHash(2));
                    //hash.searchHash(2);
                    break;
                case 5: //Delete
                   update("1","10","tudo bem aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                   //hash.updateHash(2, 8);
                    System.out.println(hash.bucket);
                    break;
                case 6:
                    read();
                    break;
                case 0:

                   hash.insert();
                   //ash.updateHash(1,2);
                    //flag = false;
                    break;
                default:
                    System.out.println("Utilize uma opcao valida.");
            }
        }
    }
}