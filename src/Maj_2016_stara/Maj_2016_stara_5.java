package Maj_2016_stara;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Maj_2016_stara_5 {
    public static int w = 12;
    public static int k = 20;
    public static int zawin_w(int wiersz)
    {
        if(wiersz < 0)
        {
            return w - 1;
        }
        else if(wiersz > w - 1)
        {
            return 0;
        }
        return wiersz;
    }

    public static int zawin_k(int kolumna)
    {
        if(kolumna < 0)
        {
            return k - 1;
        }
        else if(kolumna > k - 1)
        {
            return 0;
        }
        return kolumna;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj_2016_stara\\gra" +
                ".txt"));

        char[][] P_prev = new char[w][k];
        char[][] P_next = new char[w][k];

        int i = 0;

        while(scanner.hasNext())
        {
            P_prev[i] = scanner.nextLine().toCharArray();
            i++;
        }

        String wynik5_1 = "";
        String wynik5_2 = "";
        String wynik5_3 = "";

        for(int p = 2; p <= 100; p++)
        {
            for(int wiersz = 0; wiersz < w; wiersz++)
            {
                for(int kolumna = 0; kolumna < k; kolumna++)
                {
                    int ile_zywych_sasiadow = 0;
                    for(int i1 = -1; i1 <= 1; i1++)
                    {
                        for(int i2 = -1; i2 <= 1; i2++)
                        {
                            if(!(i1 == 0 && i2 == 0))
                            {
                                if(P_prev[zawin_w(wiersz + i1)][zawin_k(kolumna + i2)] == 'X')
                                {
                                    ile_zywych_sasiadow++;
                                }
                            }
                        }
                    }
                    if((P_prev[wiersz][kolumna] == 'X' && (ile_zywych_sasiadow == 2 || ile_zywych_sasiadow == 3)) ||
                            (P_prev[wiersz][kolumna] == '.' && ile_zywych_sasiadow == 3))
                    {
                        P_next[wiersz][kolumna] = 'X';
                    }
                    else
                    {
                        P_next[wiersz][kolumna] = '.';
                    }
                }
            }

            boolean czy_stagnacja = true;

            for(int wiersz = 0; wiersz < w; wiersz++)
            {
                for(int kolumna = 0; kolumna < k; kolumna++)
                {
                    if(P_next[wiersz][kolumna] != P_prev[wiersz][kolumna])
                    {
                        czy_stagnacja = false;
                    }
                }
            }

            if(czy_stagnacja && wynik5_3.equals(""))
            {
                wynik5_3 = "5.3.\n%d\n".formatted(p);
            }

            if(p == 37)
            {
                int zywi = 0;
                for(int i1 = -1; i1 <= 1; i1++)
                {
                    for(int i2 = -1; i2 <= 1; i2++)
                    {
                        if(!(i1 == 0 && i2 == 0))
                        {
                            if(P_next[zawin_w(1 + i1)][zawin_k(18 + i2)] == 'X')
                            {
                                zywi++;
                            }
                        }
                    }
                }

                wynik5_1 = "5.1.\n%d\n\n".formatted(zywi);
            }

            if(p == 2)
            {
                int ile_wszystkich_zywych = 0;
                for(int wiersz = 0; wiersz < w; wiersz++)
                {
                    for(int kolumna = 0; kolumna < k; kolumna++)
                    {
                        if(P_next[wiersz][kolumna] == 'X')
                        {
                            ile_wszystkich_zywych++;
                        }
                    }
                }

                wynik5_2 = "5.2.\n%d\n\n".formatted(ile_wszystkich_zywych);
            }

            for(int wiersz = 0; wiersz < w; wiersz++)
            {
                for(int kolumna = 0; kolumna < k; kolumna++)
                {
                    P_prev[wiersz][kolumna] = P_next[wiersz][kolumna];
                }
            }
        }

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj_2016_stara\\wyniki5" +
                ".txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
        }
    }
}
