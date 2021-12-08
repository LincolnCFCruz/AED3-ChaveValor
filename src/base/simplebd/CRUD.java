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
            int menor = Integer.MAX_VALUE;
            int maior = Integer.MIN_VALUE;
            String idx = "1";
            String sortKey = String.valueOf(sK);
            String line, newContent;

            // Leitura inicial do arquivo para verificação dos valores já existentes, caso exista valores já existentes adicionar novo valor ao index,
            // caso contrario index tera o valor inicial "1".

            while((line = br.readLine()) != null) {
                if(!line.equals("")){
                    String [] trimmed = line.split(";");
                    idx = trimmed [0];

                    if(!idx.equals("")){
                        index = Integer.parseInt(idx);
                        menor = index;
                        if (maior < menor){
                            maior = menor;
                        }
                        index = maior+1;
                        System.out.println(index);
                        idx = idx.valueOf(index);
                    }
                } else {
                    idx = "1";
                }
            }

            newContent = idx + ";" + sortKey + ";" + value;

            // Para evitar espaços vazios dentro do arquivo é feito uma comparação dos valores do index para definir se é necessario ou não pular linha.

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
    }

    public static void remove (Integer k) {
        File finalFile = null;
        File deleteTemp = null;
        FileWriter fwArquivo = null;
        BufferedWriter my_writer = null;
        BufferedReader my_reader =  null;

        String filePath = "simpledb.db";
        String fileTemp = "simpledbTemp.db";
        int count=0;

        // Leitura sequencial do arquivo que ira alocar em um arquivo temporaro valores diferentes do que sera excluido,
        // ao terminar o processo o arquivo original sera substituido pelo arquivo temporaro.

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
        String key;
        String sortKey;
        String value;

        // Busca e leitura do arquivo a partir da posição definida pela hash.

        try{
            br2 = new RandomAccessFile("simpledb.db", "rw");

            br2.seek(pos);

            line = br2.readLine();
            String[] trimmed = line.split(";");
            key = trimmed [0];
            sortKey = trimmed[1];
            value = trimmed[2];

            System.out.println(key + ";" + sortKey + ";" + value);

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

    public static void update (Integer key, Integer newsortKey, String value) {
        BufferedReader br = null;
        FileWriter writer = null;

        // Recebe os dados para alteração.
        // Armazena o conteudo dentro de uma string e substitui o valor desejado.

        try {
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;
            String newString = "";
            String oldString = "";
            String oldContent = "";
            String updateLine = String.valueOf(key);

            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    oldContent = oldContent + line + System.lineSeparator();
                    String[] trimmed = line.split(";");
                    String index = trimmed[0];

                    if (index.equals(updateLine)) {
                        oldString = line;

                        String typeUpdate;
                        typeUpdate = String.valueOf(newsortKey);

                        String valueUpdate;
                        valueUpdate = value;

                        newString = key + ";" + typeUpdate + ";" + valueUpdate;
                    }
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

    public static void list() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;

            // Leitura sequencial do arquivo, linha a linha, ate que valor seja nulo.

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
}
