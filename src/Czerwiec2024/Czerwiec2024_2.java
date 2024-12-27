package Czerwiec2024;

import java.util.ArrayList;
import java.util.Collections;

public class Czerwiec2024_2 {
    //public static int ile = 0;
    public static int F(int x)
    {
        //ile++;
        if(x == 0)
            return 0;
        else
            return 2 + F(x / 2);
    }
    public static void main(String[] args) {

        //Zadanie 2.1.
        /*int[] X = new int[]{3, 16, 35};

        for(int i = 0; i < X.length; i++)
        {
            ile = 0;
            int wynik = F(X[i]);
            System.out.println("%d %d %d".formatted(X[i], wynik, ile));
        }*/

        //Zadanie 2.2.
        ArrayList<Integer> wynik2_2 = new ArrayList<>();

        for(int x = 0; x <= 1000000; x++)
        {
            int wynik = F(x);
            if(wynik == 18)
                wynik2_2.add(x);
        }

        System.out.println("%d %d".formatted(Collections.min(wynik2_2), Collections.max(wynik2_2)));
    }
}
