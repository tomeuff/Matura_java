package Maj2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2023_2 {
    //Zadanie 2.1.
    public static int ile_blokow(int n)
    {
        int wynik = 1;
        int poprz = n % 2;
        int nast;
        while(n > 0)
        {
            nast = n % 2;
            if(nast != poprz)
                wynik++;
            poprz = nast;
            n /= 2;
        }
        return wynik;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2023\\bin.txt"));
        ArrayList<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.next());

        //Zadanie 2.2.
        String wynik2_2 = "2.2.\n%d\n\n".formatted(dane.stream().
                filter(it -> ile_blokow(Integer.parseInt(it, 2)) <= 2).count());

        //Zadanie 2.3.
        String wynik2_3 = "2.3.\n%s\n\n".formatted(dane.stream().
                max(Comparator.comparingInt(a -> Integer.parseInt(a, 2))).get());

        //Zadanie 2.4.
        String wynik2_4 = "2.4.\n%d\n\n".formatted((123 ^ Integer.parseInt("101101", 2))^
                Integer.parseInt("2D", 16));

        //Zadanie 2.5.
        String wynik2_5 = "2.5.\n%s\n".formatted(dane.stream().map(it -> Integer.parseInt(it, 2)).
                map(it -> it ^ (it / 2)).map(it -> Integer.toString(it, 2)).collect(Collectors.joining("\n")));

        try (FileWriter wynik2 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2023\\wynik2.txt")) {
            wynik2.write("");
            wynik2.append(wynik2_2);
            wynik2.append(wynik2_3);
            wynik2.append(wynik2_4);
            wynik2.append(wynik2_5);
        }
    }
}
