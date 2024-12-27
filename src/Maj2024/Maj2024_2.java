package Maj2024;

public class Maj2024_2 {
    public static void main(String[] args) {
        long n = 333333666666999999L;
        long b = 1;
        long c = 0;
        long ile = 0;

        while(n > 0)
        {
            long a = n % 10;
            n = n / 10;
            if(a % 2 == 0)
                c = c + b * (a / 2);
            else
            {
                ile++;
                c = c + b;
            }
            b = b * 10;
        }

        //System.out.println("%d %d".formatted(c, ile));
        System.out.println(c);
    }
}
