package Maj2022_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Maj2022_4 {
    public static int l_r_czyn(int liczba)
    {
        int wynik = 0;
        for(int d = 2; d <= liczba; d++)
        {
            if(liczba % d == 0)
                wynik++;
            while(liczba % d == 0)
                liczba /= d;
            if(liczba == 1)
                break;
        }
        return wynik;
    }

    public static int l_czyn(int liczba)
    {
        int wynik = 0;
        for(int d = 2; d <= liczba; d++)
        {
            while(liczba % d == 0)
            {
                wynik++;
                liczba /= d;
            }
            if(liczba == 1)
                break;
        }
        return wynik;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2022_4\\liczby.txt"));
        ArrayList<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        //Zadanie 4.1.
        ArrayList<Integer> liczby = new ArrayList<>(dane.stream().filter(
                it -> it.toString().charAt(0) == it.toString().charAt(it.toString().length() - 1)).toList());
        String wynik4_1 = "4.1.\n%d %d\n\n".formatted(liczby.size(), liczby.get(0));

        //Zadanie 4.2.
        int max_l_r_c = dane.stream().max((a, b) -> l_r_czyn(a) - l_r_czyn(b)).get();
        int max_l_c = dane.stream().max((a, b) -> l_czyn(a) - l_czyn(b)).get();

        String wynik4_2 = "4.1.\n%d %d\n%d %d\n\n".formatted(max_l_c, l_czyn((max_l_c)), max_l_r_c,
                l_r_czyn((max_l_r_c)));

        //Zadanie 4.3.
        Collections.sort(dane);
        int ile_db3 = 0;
        int ile_db5 = 0;
        StringBuilder DB3 = new StringBuilder();
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");
        for(int i = 0; i < dane.size(); i++)
        {
            for(int j = i + 1; j < dane.size(); j++)
            {
                if(dane.get(j) % dane.get(i) == 0)
                {
                    for(int k = j + 1; k < dane.size(); k++)
                    {
                        if(dane.get(k) % dane.get(j) == 0)
                        {
                            ile_db3++;
                            DB3.append("%d %d %d\n".formatted(dane.get(i), dane.get(j), dane.get(k)));
                            for(int l = k + 1; l < dane.size(); l++)
                            {
                                if(dane.get(l) % dane.get(k) == 0)
                                {
                                    for(int m = l + 1; m < dane.size(); m++)
                                    {
                                        if(dane.get(m) % dane.get(l) == 0)
                                        {
                                            ile_db5++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        wynik4_3.append("a)\n%d\n%s\nb)\n%d".formatted(ile_db3, DB3.toString(), ile_db5));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2022_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
