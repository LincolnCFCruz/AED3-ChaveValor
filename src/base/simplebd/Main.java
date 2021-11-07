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

    public static void add () {
        File fArquivo = null;
        try {
            fArquivo = new File("simpledb.db");
            FileWriter fwArquivo = null;

            if (fArquivo.exists() == true) {
                fwArquivo = new FileWriter(fArquivo, true);
            } else {
                fwArquivo = new FileWriter(fArquivo);
            }

            Scanner sc2 = new Scanner(System.in);
            BufferedWriter bw = new BufferedWriter(fwArquivo);
            BufferedReader br = new BufferedReader(new FileReader("simpledb.db"));

            String line;
            int index = 1;

            while ((line = br.readLine()) != null){
                String trimmed = line;
                String key = "";
                key = trimmed.substring(0,1);

                if (key != null) {
                    index = Integer.parseInt(key);
                    index++;
                } else if (key == null){
                    index = 1;
                }
            }

            bw.write(index + ";");

            System.out.println("Type: ");
            int type = Integer.parseInt(sc2.nextLine());
            bw.write(type + ";");

            System.out.println("Value: ");
            String value;
            value = sc2.nextLine();
            bw.write(value + "\n");

            System.out.println("Done");

            br.close();
            bw.close();
            fwArquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void update () {
        BufferedReader br = null;
        FileWriter writer = null;
        try {
            Scanner sc3 = new Scanner(System.in);
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;
            String newString = "";
            String oldString = "";
            String oldContent = "";
            String updateLine = sc3.nextLine();

            while ((line = br.readLine()) != null) {
                oldContent = oldContent + line + System.lineSeparator();

                String trimmed = line;
                String key = "";
                key = trimmed.substring(0,1);
                if (key.equals(updateLine)){
                    oldString = line;

                    System.out.println("TypeUpdate: ");
                    String typeUpdate;
                    typeUpdate = sc3.nextLine();

                    System.out.println("ValueUpdate: ");
                    String valueUpdate;
                    valueUpdate = sc3.nextLine();

                    newString = key + ";" + typeUpdate + ";" + valueUpdate;
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

    public static void delete () {
        BufferedReader my_reader = null;
        FileWriter my_writer = null;

        String filePath = "simpledb.db";
        String fileTemp = "simpledbTemp.db";

        try {
            File input_file = new File(filePath);
            File temp_file = new File(fileTemp);

            my_reader = new BufferedReader(new FileReader(input_file));
            my_writer = new FileWriter(temp_file);

            Scanner sc4 = new Scanner(System.in);

            System.out.println("Delete: ");
            int value = sc4.nextInt();
            String lineToRemove = String.valueOf(value);
            String current_line;

            while((current_line = my_reader.readLine()) != null) {
                String trimmedLine = current_line.trim();
                String key = "";
                key = trimmedLine.substring(0,1);
                if(key.equals(lineToRemove)) continue;
                my_writer.write(current_line + System.getProperty("line.separator"));
            }

            my_writer.close();
            my_reader.close();

            System.out.println("Done");
            input_file.delete();
            temp_file.renameTo(input_file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int option;
        boolean flag = true;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Opcao 1: Create");
        System.out.println("Opcao 2: Add");
        System.out.println("Opcao 3: Read");
        System.out.println("Opcao 4: Update");
        System.out.println("Opcao 5: Delete");
        System.out.println("Opcao 0: Exit");

        while (flag == true) {
            System.out.println("Digite sua opcao:");
            option = entrada.nextInt();

            switch (option) {
                case 1: //Create
                    create();
                    break;
                case 2:
                    add();
                    break;
                case 3: //Read
                    read();
                    break;
                case 4: //Update
                    update();
                    break;
                case 5: //Delete
                    delete();
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