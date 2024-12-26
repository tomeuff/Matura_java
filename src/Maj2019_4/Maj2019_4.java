package Maj2019_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2019_4 {

    public static int NWD(int a, int b)
    {
        while(b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
    public static int silnia(int liczba)
    {
        int wynik = 1;
        for(int i = 1; i <= liczba; i++)
            wynik *= i;
        return wynik;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_4\\liczby.txt"));
        List<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        //Zadanie 4.1.
        List<Integer> pot3 = new ArrayList<>();
        int liczba = 1;
        while(liczba <= 100000)
        {
            pot3.add(liczba);
            liczba *= 3;
        }

        String wynik4_1 = "4.1.\n%d\n\n".formatted(dane.stream().filter(it -> pot3.contains(it)).count());

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%s\n\n".formatted(dane.stream().filter(it1 ->
                it1.toString().chars().map(it2 -> silnia(it2 - 48)).sum() ==
                        it1).map(String::valueOf).collect(Collectors.joining("\n")));


        //Zadanie 4.3.
        int p_max = 0, k_max = 0, nwd_max = 0;

        for(int p = 0; p < dane.size(); p++)
        {
            for(int k = p; k < dane.size(); k++)
            {
                int nwd = dane.get(p);
                for(int i = p; i <= k; i++)
                {
                    nwd = NWD(nwd, dane.get(i));
                    if(nwd == 1)
                        break;
                }
                if(nwd > 1 && k - p + 1 > k_max - p_max + 1)
                {
                    k_max = k;
                    p_max = p;
                    nwd_max = nwd;
                }
            }
        }

        String wynik4_3 = "4.3.\n%d %d %d\n".formatted(dane.get(p_max), k_max - p_max + 1, nwd_max);

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
