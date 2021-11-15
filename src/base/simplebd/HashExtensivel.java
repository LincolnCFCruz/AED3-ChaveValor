package base.simplebd;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class HashExtensivel<K,V> {

    HashMap<Integer, Long> bucket = new HashMap<Integer, Long>();

    public void insert() {
        RandomAccessFile br3 = null;
        String line;
        String index = "";
        String sortKey = "";
        int idx = 1;
        int stk = 1;

        try {
            br3 = new RandomAccessFile("simpledb.db", "rw");
            while ((line = br3.readLine()) != null) {
                String[] trimmed = line.split(";");
                index = trimmed[0];
                sortKey = trimmed[1];

                idx = Integer.valueOf(index);


                long pointer = br3.getFilePointer();

                if (!bucket.containsKey(idx)) {
                    bucket.put(idx, pointer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

