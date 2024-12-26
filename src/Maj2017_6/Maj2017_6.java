package Maj2017_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Maj2017_6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017_6\\dane.txt"));
        List<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
        {
            dane.add(scanner.nextInt());
        }

        //Zadanie 6.1.
        String wynik6_1 = "6.1.\n%d\n%d\n\n".formatted(Collections.max(dane), Collections.min(dane));

        //Zadanie 6.2.
        int ile_usunac = 0;
        for(int i = 0; i < 200; i++)
        {
            List<Integer> wiersz = dane.subList(i * 320, i * 320 + 320);
            List<Integer> wiersz_odwr = new ArrayList<>(wiersz);
            Collections.reverse(wiersz_odwr);

            if(!wiersz.equals(wiersz_odwr))
                ile_usunac++;
        }
        String wynik6_2 = "6.2.\n%d\n\n".formatted(ile_usunac);

        //Zadanie 6.3.
        List<List<Integer>> dane2D = new ArrayList<>();
        for(int i = 0; i < 200; i++)
        {
            List<Integer> wiersz = dane.subList(i * 320, i * 320 + 320);
            dane2D.add(wiersz);
        }

        int ile_kontr = 0;

        for(int i = 0; i < dane2D.size(); i++)
        {
            for(int j = 0; j < dane2D.get(i).size(); j++)
            {
                if(i - 1 >= 0)
                    if(Math.abs(dane2D.get(i).get(j) - dane2D.get(i - 1).get(j)) > 128)
                    {
                        ile_kontr++;
                        continue;
                    }
                if(i + 1 < 200)
                    if(Math.abs(dane2D.get(i).get(j) - dane2D.get(i + 1).get(j)) > 128)
                    {
                        ile_kontr++;
                        continue;
                    }
                if(j - 1 >= 0)
                    if(Math.abs(dane2D.get(i).get(j) - dane2D.get(i).get(j - 1)) > 128)
                    {
                        ile_kontr++;
                        continue;
                    }
                if(j + 1 < 320)
                    if(Math.abs(dane2D.get(i).get(j) - dane2D.get(i).get(j + 1)) > 128)
                    {
                        ile_kontr++;
                        continue;
                    }
            }
        }
        String wynik6_3 = "6.3.\n%d\n\n".formatted(ile_kontr);

        //Zadanie 6.4.
        int p_max = 0, k_max = 0;
        for(int kol = 0; kol < 320; kol++)
        {
            int p = 0, k = 0;
            for(int wier = 1; wier < 200; wier++)
            {
                if(dane2D.get(wier).get(kol) == dane2D.get(wier - 1).get(kol))
                {
                    k++;
                    if(k - p + 1 > k_max - p_max + 1)
                    {
                        k_max = k;
                        p_max = p;
                    }
                }
                else
                {
                    p = wier;
                    k = wier;
                }
            }
        }
        String wynik6_4 = "6.4.\n%d\n".formatted(k_max - p_max + 1);
        System.out.println(wynik6_4);

        try (FileWriter wynik6 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017_6\\wyniki6.txt")) {
            wynik6.write("");
            wynik6.append(wynik6_1);
            wynik6.append(wynik6_2);
            wynik6.append(wynik6_3);
            wynik6.append(wynik6_4);
        }
    }
}
