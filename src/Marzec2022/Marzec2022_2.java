package Marzec2022;

import java.util.Arrays;

public class Marzec2022_2 {
    public static int n;
    public static int s;
    public static int[] A;
    public static Boolean[] B;
    public static void Tura(int k)
    {
        for(int i = s; i >= A[k - 1]; i--)
        {
            if(B[i - A[k - 1]] && !B[i])
            {
                B[i] = true;
            }
        }
    }
    public static void main(String[] args) {
        //Zadanie 2.1. 2.2.
        A = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        n = A.length;
        s = 500;
        B = new Boolean[s + 1];
        Arrays.fill(B, false);

        B[0] = true;
        for(int k = 1; k <= n; k++)
            Tura(k);

        /*if(B[s])
            System.out.println("TAK");
        else
            System.out.println("NIE");*/

        System.out.println(Arrays.stream(B).map(it -> {if(it) return 1; else return 0;}).reduce(0, Integer::sum));

        //Zadanie 2.3.
        //A=[1, 2, 4, 8, 16, 32, 64, 128]
    }
}
