package Czerwiec2019_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Czerwiec2019_4 {
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
        Scanner scanner1 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2019_4" +
                "\\liczby.txt"));
        Scanner scanner2 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2019_4" +
                "\\pierwsze.txt"));

        List<Integer> dane1 = new ArrayList<>();
        List<Integer> dane2 = new ArrayList<>();

        while(scanner1.hasNext())
            dane1.add(scanner1.nextInt());

        while(scanner2.hasNext())
            dane2.add(scanner2.nextInt());

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\n%s\n\n".formatted(dane1.stream().filter(it -> czy_pierwsza(it) && it >= 100 && it <= 5000).
                        map(Object::toString).collect(Collectors.joining("\n")));

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%s\n\n".formatted(dane2.stream().filter(it ->
                czy_pierwsza(Integer.parseInt(new StringBuilder(it.toString()).reverse().
                        toString()))).map(Object::toString).collect(Collectors.joining("\n")));

        //Zadanie 4.3.
        int ile = 0;
        for(Integer liczba : dane2)
        {
            while(liczba > 9)
                liczba = liczba.toString().chars().map(it -> it - 48).sum();
            if(liczba == 1)
                ile++;
        }

        String wynik4_3 = "4.3.\n%d\n".formatted(ile);

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2019_4\\wyniki4" +
                ".txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }

    }
}
