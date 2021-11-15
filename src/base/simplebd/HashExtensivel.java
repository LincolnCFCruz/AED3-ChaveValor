package base.simplebd;

import java.io.*;
import java.util.*;
import java.util.List;

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
    }

    void updateHash(Integer idx, Integer sortKey) {
        System.out.println("bucket antes da alteracao" + bucket);

        bucket.put(idx, searchHash(idx));

        System.out.println("bucket depois da alteracao" + bucket);
    }

    Long searchHash(Integer idx) {
        //return bucket.get(idx);

        for (Integer i : bucket.keySet()) {
            if (i == idx) {
               return bucket.get(i);
            }
        }
        return null;
    }
}

