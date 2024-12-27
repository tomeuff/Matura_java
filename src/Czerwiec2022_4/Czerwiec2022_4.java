package Czerwiec2022_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Czerwiec2022_4 {
    public static boolean czy_pierwsza(int liczba)
    {
        if(liczba == 1)
            return false;
        for(int d = 2; d <= Math.sqrt(liczba); d++)
            if(liczba % d == 0)
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2022_4\\liczby.txt"));
        ArrayList<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        //Zadanie 4.1.
        StringBuilder wynik4_1 = new StringBuilder("4.1.\n");
        for(Integer liczba : dane)
        {
            StringBuilder sb = new StringBuilder(liczba.toString());
            sb.reverse();
            if(Integer.parseInt(sb.toString()) % 17 == 0)
                wynik4_1.append("%d\n".formatted(Integer.parseInt(sb.toString())));
        }
        wynik4_1.append("\n");

        //Zadanie 4.2.
        int max_liczba = 0;
        int max_roznica = 0;
        for(Integer liczba : dane)
        {
            StringBuilder sb = new StringBuilder(liczba.toString());
            sb.reverse();
            Integer odbicie = Integer.parseInt(sb.toString());
            Integer roznica = Math.abs(liczba - odbicie);
            if(roznica > max_roznica)
            {
                max_roznica = roznica;
                max_liczba = liczba;
            }
        }

        String wynik4_2 = "4.2.\n%d %d\n\n".formatted(max_liczba, max_roznica);

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");

        for(Integer liczba : dane)
        {
            StringBuilder sb = new StringBuilder(liczba.toString());
            sb.reverse();
            Integer odbicie = Integer.parseInt(sb.toString());
            if(czy_pierwsza(liczba) && czy_pierwsza(odbicie))
                wynik4_3.append("%d\n".formatted(liczba));
        }
        wynik4_3.append("\n");

        //Zadanie 4.4.
        HashMap<Integer, Integer> hmLiczby = new HashMap<>();

        for(Integer liczba : dane)
        {
            if(hmLiczby.containsKey(liczba))
                hmLiczby.replace(liczba, hmLiczby.get(liczba) + 1);
            else
                hmLiczby.put(liczba, 1);
        }

        int ile_r = hmLiczby.size();
        int ile_2 = (int) hmLiczby.values().stream().filter(it -> it == 2).count();
        int ile_3 = (int) hmLiczby.values().stream().filter(it -> it == 3).count();

        String wynik4_4 = "4.3.\n%d\n%d\n%d\n".formatted(ile_r, ile_2, ile_3);

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2022_4\\wyniki4" +
                ".txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
