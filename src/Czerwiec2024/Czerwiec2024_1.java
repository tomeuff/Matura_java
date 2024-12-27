package Czerwiec2024;

import java.util.ArrayList;
import java.util.Collections;

public class Czerwiec2024_1 {
    public static void main(String[] args) {

        //Zadanie 1.1.
        int w = 4, k = 5, n = 179;
        int[][] T = new int[w][k];
        ArrayList<Integer> bin = new ArrayList<>();

        while(n > 0)
        {
            bin.add(n % 2);
            n /= 2;
        }

        Collections.reverse(bin);

        int ind = 0;

        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < k; j++)
            {
                T[i][j] = bin.get(ind);
                ind++;
                if(ind >= bin.size())
                    ind = 0;
            }
        }

        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < k; j++)
            {
                System.out.print(T[i][j] + "\t");
            }
            System.out.println();
        }

        //Zadanie 1.2.
        w = 5;
        k = 3;
        n = 19;

        int n_kopia = n;

        int ile_cyfr = 0;

        while(n_kopia > 0)
        {
            ile_cyfr++;
            n_kopia /= 2;
        }

        int ile_pol = w * k;

        int reszta = ile_pol % ile_cyfr;

        n_kopia = n;

        while(n_kopia > 0)
        {
            int cyfra = n_kopia % 2;
            n_kopia /= 2;

            if(reszta == 0)
            {
                System.out.println(cyfra);
                break;
            }

            reszta--;
        }
    }
}
