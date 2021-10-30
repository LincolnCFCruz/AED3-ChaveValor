package base.company;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Chave l1 = new Chave(1, "Eu, Robô", "Isaac Asimov", 14.90F);
        Chave l2 = new Chave(2, "Eu Sou a Lenda", "Richard Matheson", 21.99F);

        RandomAccessFile arq;
        byte ba[];

        try {

            // ESCRITA
            arq = new RandomAccessFile("E:\\Arquivos\\Workspace\\Java\\AED3-Trabalho\\src\\base\\company\\dados/simpledb.db", "rw");

            long pos1 = arq.getFilePointer();
            ba = l1.toByteArray();
            arq.writeInt(ba.length);
            arq.write(ba);

            long pos2 = arq.getFilePointer();
            ba = l2.toByteArray();
            arq.writeInt(ba.length);
            arq.write(ba);

            // LEITURA
            Chave l3 = new Chave();
            Chave l4 = new Chave();
            int tam;

            arq.seek(pos2);
            tam = arq.readInt();
            ba = new byte[tam];
            arq.read(ba);
            l3.fromByteArray(ba);

            arq.seek(pos1);
            tam = arq.readInt();
            ba = new byte[tam];
            arq.read(ba);
            l4.fromByteArray(ba);

            System.out.println(l3);
            System.out.println(l4);

            arq.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
