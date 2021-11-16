package base.simplebd;

import java.io.*;
import java.util.Scanner;

public class CRUD {
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
    } //ok

    public static void insert (Integer sK, String value) {
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

            bw.write("\n" + index + ";");
            bw.write(sK + ";");
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
    } //ok

    public static void remove (Integer k) {
        BufferedReader my_reader = null;
        FileWriter my_writer = null;

        String filePath = "simpledb.db";
        String fileTemp = "simpledbTemp.db";

        try {
            File main_file = new File(filePath);
            File temp_file = new File(fileTemp);

            my_reader = new BufferedReader(new FileReader(main_file));
            my_writer = new FileWriter(temp_file);

            System.out.println("Delete: ");
            String lineToRemove = String.valueOf(k);
            String current_line;

            while((current_line = my_reader.readLine()) != null){
                String [] trimmed = current_line.split(";");
                String key = trimmed [0];

                if(!key.equals(lineToRemove)){
                    my_writer.write(current_line + "\n");
                }
            }
            my_writer.close();
            my_reader.close();

            main_file.delete();
            temp_file.renameTo(main_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Esta funcionando, mas falta remover linha vazia após exclusão. (Pode quebrar o simpledb.db)

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
    }//ok

    public static void update (Integer key, Integer newsortKey, String value) {
        BufferedReader br = null;
        FileWriter writer = null;

        try{
            br = new BufferedReader(new FileReader("simpledb.db"));

            String line;
            String newString = "";
            String oldString = "";
            String oldContent = "";
            String updateLine = String.valueOf(key);

            while ((line = br.readLine()) != null) {
                oldContent = oldContent + line + System.lineSeparator();

                String [] trimmed = line.split(";");
                String keyA = trimmed [0];

                if (keyA.equals(updateLine)){
                    oldString = line;

                    String sortKeyUpdate;
                    sortKeyUpdate = String.valueOf(newsortKey);

                    String valueUpdate;
                    valueUpdate = value;

                    newString = updateLine + ";" + sortKeyUpdate + ";" + valueUpdate;
                    System.out.println(newString);
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
    } //ok

    public static void list() {
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
    } //Lê lista completa, falta bucket + ordenação
}
