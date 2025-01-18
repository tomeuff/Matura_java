package Marzec2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Marzec2022_3 {
    static final int n = 1000;
    public static long NWD(long a, long b)
    {
        while(b != 0)
        {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
    public static boolean czy_pierwsza(long liczba)
    {
        if(liczba == 1)
        {
            return false;
        }

        for(int d = 2; d <= Math.sqrt(liczba); d++)
        {
            if(liczba % d == 0)
            {
                return false;
            }
        }

        return true;
    }

    //Zadanie 3.2.
    public static long pot_modul(long a, long x, long M)
    {
        long b = 1;
        long z = a;

        while(x > 0)
        {
            if(x % 2 == 1)
            {
                b = b * z % M;
            }

            x /= 2;
            z = (z * z) % M;
        }

        return b;
    }

    public static long pot_modul2(long a, long x, long M)
    {
        long wynik = 1;
        long pota = a;

        while(x > 0)
        {
            if(x % 2 == 1)
            {
                wynik = (wynik * pota) % M;
            }

            x /= 2;
            pota = (pota * pota) % M;
        }

        return wynik;
    }

    public static void main(String[] args) throws IOException {
        //Zadanie 3.1.
        /*System.out.println(5);
        System.out.println(2);
        System.out.println(6);
        System.out.println(1);*/

        Long[][] dane = new Long[n][3];

        Scanner scanner  = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\liczby" +
                ".txt"));

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                dane[i][j] = scanner.nextLong();
            }
        }

        //Zadanie 3.1.
        String wynik3_3 = "3.3.\n%d\n\n".formatted(Arrays.stream(dane).filter(it -> czy_pierwsza(it[0])).count());

        //Zadanie 3.4.
        String wynik3_4 = "3.4.\n%d\n\n".formatted(Arrays.stream(dane).filter(it -> NWD(it[0], it[1]) == 1).count());

        //Zadanie 3.5.
        int ile_mozliwe = 0;

        for(int i = 0; i < n; i++)
        {
            for(int x = 0; x <= dane[i][0] - 1; x++)
            {
                if(pot_modul2(dane[i][1], x, dane[i][0]) == dane[i][2])
                {
                    ile_mozliwe++;
                    break;
                }
            }
        }

        String wynik3_5 = "3.5.\n%d\n".formatted(ile_mozliwe);

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_3);
            wynik3.append(wynik3_4);
            wynik3.append(wynik3_5);
        }
    }
}
