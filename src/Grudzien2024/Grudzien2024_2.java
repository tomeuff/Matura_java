package Grudzien2024;

public class Grudzien2024_2 {
    public static int ile_wyw = 0;
    public static int F(int x, int p)
    {
        ile_wyw++;
        if(x == 0)
            return 0;
        else
        {
            int c = x % p;
            if(c % 2 == 1)
                return F(x / p, p) + c;
            else
                return F(x / p, p) - c;
        }
    }
    public static void main(String[] args) {
        ile_wyw = 0;
        System.out.println(F(220, 4));
        System.out.println(ile_wyw);

        int wyn_x = 0;
        for(int x = 0; x < 100; x++)
            if(F(x, 4) == 0)
                wyn_x = x;
        System.out.println(wyn_x);
    }
}
