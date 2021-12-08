package base.simplebd;

import java.io.*;
import java.util.*;

public class selectionSort {
    public static void selectionCode(String[] arr, int n) {
        for(int i = 0; i < n - 1; i++) {
            int min_index = i;
            String minStr = arr[i];

            //Compara string sucessora com antecessora e define o valor alfabetico, ou seja, a diferença numerica entre elas.
            for(int j = i + 1; j < n; j++) {
                if(arr[j].compareTo(minStr) < 0) {
                    minStr = arr[j];
                    min_index = j;
                }
            }

            // Se necessario trocar posição dentro do array para ordenação.
            if(min_index != i) {
                String temp = arr[min_index];
                arr[min_index] = arr[i];
                arr[i] = temp;
            }
        }

        Scanner entrada = new Scanner(System.in);
        int op = entrada.nextInt();
        int count =0;

        try {
            BufferedWriter br15 = new BufferedWriter(new FileWriter("simpledb.db"));

            if (op == 1) {
                for (int k = 0; k < arr.length; k++) {
                    if(count == 0){
                        br15.write(arr[k]);
                    } else {
                        br15.write("\n" + arr[k]);
                    }
                    count++;
                }
            } else if (op == 2) {
                for (int k = arr.length - 1; k >= 0; k--) {
                    if(count == 0){
                        br15.write(arr[k]);
                    } else {
                        br15.write("\n" + arr[k]);
                    }
                    count++;
                }
            }
            br15.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void lerArquivo (){
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader("simpledb.db"));
            String line;
            String Conteudo = "";
            int contador = 0;

            // Conteudo = x;x;x, < a virgula é usada como referencia para o split, resultando na criação de um array funcional
            // para o metodo de ordenação por seleção.

            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    Conteudo = Conteudo + line + ",";
                    contador++;
                }
            }

            String[] trimmed = Conteudo.split(",");

            br.close();

            selectionCode(trimmed,contador);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}