package base.simplebd;

import java.io.*;
import java.util.*;

public class Lzw {
    public static List<Integer> compress(String uncompressed) throws IOException {

        File fArquivo = new File("simpledb.db");
        File fArquivocomp = new File("simpledb.lzw");
        FileWriter fwArquivo = null;
        FileWriter fwArquivocomp = null;
        Scanner sc2 = null;
        BufferedWriter bw = null;
        BufferedReader br = null;
        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("simpledb.lzw"));

        if (fArquivo.exists() == true) {
            fwArquivo = new FileWriter(fArquivo, true);
        } else {
            fwArquivo = new FileWriter(fArquivo);
        }
        if (fArquivocomp.exists() == true) {
            fwArquivocomp = new FileWriter(fArquivocomp, true);
        } else {
            fwArquivocomp = new FileWriter(fArquivocomp);
        }
        bw = new BufferedWriter(fwArquivocomp);
        br = new BufferedReader(new FileReader("simpledb.db"));


        int dictam = 256;
        Map<String, Integer> dic = new HashMap<String, Integer>();
        for (int i = 0; i < 256; i++) {
            dic.put("" + (char) i, i);
        }
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dic.containsKey(wc))
                w = wc;
            else {
                result.add(dic.get(w));
                //Add wc to the dic.
                dic.put(wc, dictam++);
                w = "" + c;
            }
        }
        //Output the code for w.
        if (!w.equals("")) {
            result.add(dic.get(w));
        }
        bw.write(result.toString());
        file.writeObject(result.toString());
        br.close();
        bw.close();
        fwArquivo.close();
        file.close();
        return result;
    }

    public static String decompress(List<Integer> compressed) {
        int dictam = 256;
        Map<Integer, String> dic = new HashMap<Integer, String>();
        for (int i = 0; i < 256; i++)
            dic.put(i, "" + (char) i);

        String w = "" + (char) (int) compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dic.containsKey(k))
                entry = dic.get(k);
            else if (k == dictam)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            //Add w+entry[0] to the dic.
            dic.put(dictam++, w + entry.charAt(0));

            w = entry;
        }
        return result.toString();
    }
}
