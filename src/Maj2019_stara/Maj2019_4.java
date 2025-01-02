package Maj2019_stara;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2019_4 {
    public static void main(String[] args) throws IOException {
        final int n = 50;
        final int w = 30;
        final int k = 30;

        char[][][] T = new char[n][w][k];

        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara\\dzialki.txt"));

        String wiersz;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < w; j++)
            {
                wiersz = scanner.nextLine();
                if(!wiersz.isEmpty())
                    T[i][j] = wiersz.toCharArray();
            }
            if(i < n - 1)
                scanner.nextLine();
        }

        //Zadanie 4.1.
        int prog = (w * k * 70) / 100;
        int ile_70_lub_wiecej = 0;

        for(int i = 0; i < n; i++)
        {
            int ile_trawy = 0;
            for(int j = 0; j < w; j++)
                for(int m = 0; m < k; m++)
                    if(T[i][j][m] == '*')
                        ile_trawy++;
            if(ile_trawy >= prog)
                ile_70_lub_wiecej++;
        }

        String wynik4_1 = "4.1.\n%d\n\n".formatted(ile_70_lub_wiecej);

        //Zadanie 4.2.
        String wynik4_2 = "";
        boolean czy_stop = false;
        for(int i1 = 0; i1 < n; i1++)
        {
            for(int i2 = 0; i2 < n; i2++)
            {
                if(i1 != i2)
                {
                    int ile_zgodnych = 0;
                    for(int i = 0; i < w; i++)
                    {
                        for(int j = 0; j < k; j++)
                        {
                            if(T[i1][i][j] == T[i2][w - i - 1][k - j - 1])
                                ile_zgodnych++;
                        }
                    }
                    if(ile_zgodnych == 900)
                    {
                        wynik4_2 = "4.2.\n%d %d\n\n".formatted(i1 + 1, i2 + 1);
                        czy_stop = true;
                        break;
                    }
                }
                if(czy_stop)
                    break;
            }
            if(czy_stop)
                break;
        }

        //Zadanie 4.3.
        int max_a = 0;
        ArrayList<Integer> max_dzialki = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            for(int a = 0; a <= 30; a++)
            {
                int ile_x = 0;
                for(int j = 0; j < a; j++)
                    for(int m = 0; m < a; m++)
                        if(T[i][j][m] == 'X')
                            ile_x++;
                if(ile_x == 0)
                {
                    if(a == max_a)
                        max_dzialki.add(i + 1);
                    else if(a > max_a)
                    {
                        max_a = a;
                        max_dzialki = new ArrayList<>();
                        max_dzialki.add(i + 1);
                    }
                }
            }
        }
        String wynik4_3 = "4.3.\n%d\n%s\n".formatted(max_a,
                max_dzialki.stream().map(Object::toString).collect(Collectors.joining(", ")));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
