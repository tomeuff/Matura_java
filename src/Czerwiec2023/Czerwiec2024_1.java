package Czerwiec2023;

import java.util.ArrayList;
import java.util.HashMap;

public class Czerwiec2024_1 {
    public static int iloczyn_iter(int x, int y)
    {
        int z = 0;

        while(y > 0)
        {
            if(y % 2 == 1)
                z = z + x;
            x = x + x;
            y = y / 2;
        }

        return z;
    }
    public static int nr_wyw = 0;

    public static int l_dodawan = 0;

    public static HashMap<Integer, Wywolanie> wywolania = new HashMap<>();

    public static int iloczyn(int x, int y)
    {
        nr_wyw++;
        wywolania.put(nr_wyw, new Wywolanie());

        Wywolanie w = wywolania.get(nr_wyw);

        w.nr = nr_wyw;
        w.x = x;
        w.y = y;

        if(y == 1)
            return x;
        else
        {
            int k = y / 2;

            w.k = k;

            int z = iloczyn(x, k);

            w.z = z;

            if(y % 2 == 0)
            {
                w.wynik = z + z;
                l_dodawan += 1;
                return z + z;
            }
            else
            {
                w.wynik = x + z + z;
                l_dodawan += 2;
                return x + z + z;
            }
        }
    }
    public static void main(String[] args) {
        /*iloczyn(10, 45);

        for(Wywolanie w : wywolania.values())
            System.out.println(w.toString());*/

        /*int[] X = new int[]{9, 8, 2, 112};
        int[] Y = new int[]{11, 32, 47, 112};

        for(int i = 0; i < X.length; i++)
        {
            l_dodawan = 0;
            iloczyn(X[i], Y[i]);

            System.out.println("%d\t%d\t%d".formatted(X[i], Y[i], l_dodawan));
        }*/

        System.out.println(iloczyn_iter(10, 45));
    }
}
