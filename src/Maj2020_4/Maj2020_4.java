package Maj2020_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2020_4 {

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
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2020_4\\pary.txt"));
        List<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextLine());

        //Zadanie 4.1.
        StringBuilder wynik4_1 = new StringBuilder("4.1\n");

        for(String w : dane)
        {
            int liczba = Integer.parseInt(w.split(" ")[0]);
            if(liczba % 2 == 0)
            {
                for(int p = 2; p <= liczba / 2; p++)
                {
                    if(czy_pierwsza(p) && czy_pierwsza(liczba - p))
                    {
                        wynik4_1.append("%d %d %d\n".formatted(liczba, p, liczba - p));
                        break;
                    }
                }
            }
        }

        //Zadanie 4.2.
        StringBuilder wynik4_2 = new StringBuilder("4.1\n");

        for(String w : dane)
        {
            String napis = w.split(" ")[1];
            String max_fr = "";
            for(int dl = 1; dl <= napis.length(); dl++)
                for(char z : napis.chars().mapToObj(it -> (char) it).toList())
                    if(napis.contains(String.valueOf(z).repeat(dl)) && dl > max_fr.length())
                        max_fr = String.valueOf(z).repeat(dl);
            wynik4_2.append("%s %d\n".formatted(max_fr, max_fr.length()));
        }
        wynik4_2.append("\n");

        //Zadanie 4.3.
        String wynik4_3 =
                "4.3.\n%s".formatted(dane.stream().filter(it -> Integer.parseInt(it.split(" ")[0]) ==
                        it.split(" ")[1].length()).sorted(Comparator.comparingInt(a -> Integer.parseInt(a.split(" ")[0]))).
                        sorted(Comparator.comparing(a -> a.split(" ")[1])).toList().get(0));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2020_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
