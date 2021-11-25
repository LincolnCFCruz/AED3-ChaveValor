package base.simplebd;

import java.io.*;
import java.util.*;

public class HashExtensivel<K,V> {

    HashMap<Integer, Long> bucket = new HashMap<Integer, Long>();

    public void insert() {
        RandomAccessFile br12 = null;
        String line;
        String index = "";
        int idx = 1;

        String filePath = "simpledb.db";
        try {
            br12 = new RandomAccessFile(filePath, "rw");
            long pointer = br12.getFilePointer();
            line = br12.readLine();
            while (line != null) {
                if(!line.equals("")) {
                    String[] trimmed = line.split(";");
                    index = trimmed[0];
                    idx = Integer.valueOf(index);

                    if (!bucket.containsKey(idx)) {
                        bucket.put(idx, pointer);
                    }
                }
                pointer = br12.getFilePointer();
                line = br12.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br12.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bucket);
    }//ok

    void updateHash(Integer idx, Integer sortKey) {
        bucket.put(idx, searchHash(idx));
    } //ok

    Long searchHash(Integer idx) {
           for (Integer i : bucket.keySet()) {
            if (i == idx) {
               return bucket.get(i);
            }
        }
        return null;
    } //ok

    void removeHash(Integer idx){
        System.out.println("Antes da remocao" + bucket);
        bucket.remove(idx);
        System.out.println("Depois da remocao" + bucket);
    }//ok

    //ordena a hash
    void ordenaHash(){
        TreeMap<Integer, Long> newMap = new TreeMap<>();
        newMap.putAll(bucket);
    }//ok

    //inverte a Hash obs: usar no método list();
    TreeMap reverseHash(){
        TreeMap<Integer, Long> newMap = new TreeMap<>(Collections.reverseOrder());
        newMap.putAll(bucket);
        return newMap;
    } //ok
}

