package org.bbw.df;

public class Reihe {
    public static void main(String[] args) {
        printReiheIterativ(4);
        printReiheRekursiv(4);
    }

    // 1           return 0 + 4
    //  2         return 4 + 4
    //   3       return 8 + 4
    //    ...   ...
    //     10  return 36 + 4
    private static void printReiheIterativ(int factor) {
        int product = 0;
        for (int i = 1; i <= 10; i++) {
            product += factor;
            System.out.printf("%2d * %d = %2d\n", i, factor, product);
        }
        System.out.println();
    }

    // 10          return 36 + 4
    //  ...       ...
    //   3       return 8 + 4
    //    2     return 4 + 4
    //     1   return 0 + 4
    private static void printReiheRekursiv(int factor) {
        getProduct(factor, 10);
        System.out.println();
    }

    private static int getProduct(int factor, int max) {
        if (max == 0) {
            return 0;
        }
        int product = getProduct(factor, max - 1) + factor;
        System.out.printf("%2d * %d = %2d\n", max, factor, product);
        return product;
    }
}
