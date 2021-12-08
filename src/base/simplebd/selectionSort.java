package base.simplebd;

import java.io.*;

public class selectionSort {
    public static void selectionCode(String[] arr, int n) {
        for(int i = 0; i < n - 1; i++) {
            int min_index = i;
            String minStr = arr[i];

            for(int j = i + 1; j < n; j++) {
                if(arr[j].compareTo(minStr) < 0) {
                    minStr = arr[j];
                    min_index = j;
                }
            }

            if(min_index != i) {
                String temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }

        for(int k=0; k< arr.length;k++){
            System.out.println(arr[k]);
        }
    }

    public static void lerArquivo (){
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;
            String Conteudo = "";
            int contador = 0;

            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    Conteudo = Conteudo + line + ",";
                    contador++;
                }
            }

            String[] trimmed = Conteudo.split(",");

            selectionCode(trimmed,contador);

            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
