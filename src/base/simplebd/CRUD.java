package base.simplebd;

import java.io.*;

public class CRUD {
    public static void insert (Integer sK, String value) {
        File fArquivo = null;
        FileWriter fwArquivo = null;
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

            int index=1;
            String idx = "1";
            String sortKey = String.valueOf(sK);
            String line, newContent;

            while((line = br.readLine()) != null) {
                String [] trimmed = line.split(";");
                idx = trimmed [0];
                if (!line.equals("")) {
                    index = Integer.parseInt(idx);
                    index++;
                } else {
                    index = 0;
                }
                idx = String.valueOf(index);
            }

            newContent = idx + ";" + sortKey + ";" + value;

            if (index == 1){
                bw.write(newContent);
            }

            if (index != 1) {
                bw.write("\n" + newContent);
            }
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
        File finalFile = null;
        File deleteTemp = null;
        FileWriter fwArquivo = null;
        BufferedWriter my_writer = null;
        BufferedReader my_reader =  null;

        String filePath = "simpledb.db";
        String fileTemp = "simpledbTemp.db";
        int count=0;

        try {
            finalFile = new File(filePath);
            deleteTemp = new File (fileTemp);
            fwArquivo = new FileWriter(fileTemp);
            my_writer = new BufferedWriter(fwArquivo);
            my_reader = new BufferedReader(new FileReader("simpledb.db"));

            String lineToRemove = String.valueOf(k);
            String current_line;

            while((current_line = my_reader.readLine()) != null){
                if(!current_line.equals("")) {
                    String[] trimmed = current_line.split(";");
                    String key = trimmed[0];

                    if (!key.equals(lineToRemove)) {
                        if(count == 0){
                            my_writer.write(current_line);
                        } else {
                            my_writer.write("\n" + current_line);
                        }
                        count++;
                    }
                }
            }
            my_reader.close();
            my_writer.close();
            fwArquivo.close();

            finalFile.delete();
            deleteTemp.renameTo(finalFile);

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

            line = br2.readLine();
            String[] trimmed = line.split(";");
            sortKey = trimmed[1];
            value = trimmed[2];

            br2.seek(pos);

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
        int count = 0;
        String newString;

        try{
            br = new BufferedReader(new FileReader("simpledb.db"));
            writer = new FileWriter(("simpledb.db"));

            String line;
            String updateLine = String.valueOf(key);

            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    String[] trimmed = line.split(";");
                    String keyA = trimmed[0];

                    if (keyA.equals(updateLine)) {
                        String sortKeyUpdate;
                        sortKeyUpdate = String.valueOf(newsortKey);

                        String valueUpdate;
                        valueUpdate = value;

                        newString = updateLine + ";" + sortKeyUpdate + ";" + valueUpdate;
                        if(count == 0) {
                            writer.write(newString);
                        } else {
                            writer.write("\n" + newString);
                        }
                    }else{
                        if (count == 0){
                            writer.write(line);
                        } else {
                            writer.write("\n" + line);
                        }
                    }
                }
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
