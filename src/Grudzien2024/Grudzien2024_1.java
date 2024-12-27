package Grudzien2024;

public class Grudzien2024_1 {
    public static void J(int n)
    {
        int nr = 1;
        while(n > 0)
        {
            if(n % 2 == 1)
                System.out.println(nr);
            n = n / 2;
            nr = nr + 1;
        }
    }
    public static void main(String[] args) {
        J(75);
    }
}
