package Maj2015_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Maj2015_4 {
    public static void main(String[] args) throws IOException {
        File plik = new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_4\\liczby.txt");
        Scanner scanner = new Scanner(plik);
        ArrayList<String> dane = new ArrayList<String>();
        while(scanner.hasNext())
        {
            dane.add(scanner.next());
        }

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\n%d\n\n".formatted(dane.stream().filter(it -> it.chars().filter(it0 -> it0 == '0').count()
                > it.chars().filter(it1 -> it1 == '1').count()).count());

        //Zadanie 4.2.
        long podzielne_przez_2 = dane.stream().filter(it -> it.charAt(it.length() - 1) == '0').count();
        long podzielne_przez_8 = dane.stream().filter(it -> it.endsWith("000")).count();
        String wynik4_2 = "4.2.\nPodzielne przez 2: %d\nPodzielne przez 8: %d\n\n".formatted(podzielne_przez_2,
                podzielne_przez_8);

        //Zadanie 4.3.
        List<String> dane_sort =
                dane.stream().sorted((it1, it2) -> new BigInteger(it1).compareTo(new BigInteger(it2))).toList();
        int max_w = dane.indexOf(dane_sort.get(dane_sort.size() - 1)) + 1;
        int min_w = dane.indexOf(dane_sort.get(0)) + 1;
        String wynik4_3 = "4.2.\nmax wiersz: %d\nmin wiersz: %d\n\n".formatted(max_w, min_w);


        //Zapis odpowiedzi
        try (FileWriter wynik = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_4\\wynik4.txt")) {
            wynik.write("");
            wynik.append(wynik4_1);
            wynik.append(wynik4_2);
            wynik.append(wynik4_3);
        }
    }
}
