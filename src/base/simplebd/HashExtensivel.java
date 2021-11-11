package base.simplebd;

import java.io.*;
import java.util.*;
import java.util.List;

public class HashExtensivel<K,V> {

        public class Pagina<K,V> {
        int size;
        int p = 0; // p = profundidade local

        HashMap<Integer, Integer> bucket = new HashMap<Integer, Integer>();

        Pagina next; //pra apontar pro proximo bucket

        boolean isfull() {
            return bucket.size() > 4096;
        }
    }

    protected int gp = 0;
    //array de buckets, ou seja, a tabela

     ArrayList<Pagina<K,V>> arrayDeBuckets = new ArrayList<Pagina<K, V>>();

    //construtor da uma hashtable, simplesmente adiciona uma pagina ao array
    public void HashTable() {
        arrayDeBuckets.add(new Pagina<K, V>());
    }

       public void insert() {
        BufferedReader br3 = null;
        String line;
        String index = "";
        String sortKey = "";
        int idx = 1;
        int stk = 1;

        try{
            br3 = new BufferedReader(new FileReader("simpledb.db"));
            while ((line = br3.readLine()) != null) {
                String trimmed = line;
                index = trimmed.substring(0, 1);
                sortKey = trimmed.substring(2,3);
                idx = Integer.valueOf(index);
                stk = Integer.valueOf(sortKey);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }


        int bucketIndex = index.hashCode(); //calcular o bucket vai incluir
      //  Pagina p = arrayDeBuckets.
        Pagina<K, V> p = arrayDeBuckets.get(bucketIndex);//encontraa o bucket para inserir a chave

        //se preferir, o numero de páginas, ainda não to usando pra nada
        int numBuckets = arrayDeBuckets.size();

        //verifica se a chave já está contida no bucket
        Set<Integer> chaves = p.bucket.keySet();
        for (Integer chave : chaves){
            if (p.bucket.containsKey(idx)) {
                break;
            }else if(!p.isfull()){
                p.bucket.put(idx,stk);
                p.size ++;
            }else{
                Pagina newPagina = new Pagina();
                newPagina.bucket.put(idx,stk);
                p.next =newPagina;
                newPagina.size++;
                //a função set() do HashMap já  insere a nova pagina no Array de Buckets
                arrayDeBuckets.add(newPagina);
            }
        }
    }
}
