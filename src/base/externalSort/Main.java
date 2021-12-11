package base.externalSort;

import static base.externalSort.ExternalSort.sort;

public class Main {

    public static void main(String[] args) throws Exception {
        ExternalSort sortTest = new ExternalSort();

        String f1 = "test1.db";
        String f2 = "test2.db";

        sort(f1, f2);
    }
}
