package Czerwiec2015_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Czerwiec2015_6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner1 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015_6" +
                "\\cyfra_kodkreskowy.txt"));
        List<String> cyfry = new ArrayList<>();
        while(scanner1.hasNext())
            cyfry.add(scanner1.nextLine().split("\t")[1]);
        cyfry = cyfry.subList(1, cyfry.size());

        Scanner scanner2 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015_6" +
                "\\kody.txt"));
        List<String> kody = new ArrayList<>();
        while(scanner2.hasNext())
            kody.add(scanner2.next());

        //Zadanie 6.1.
        int[] suma_parzyste =
                kody.stream().mapToInt(it -> it.charAt(1) - 48 + it.charAt(3) - 48 + it.charAt(5) - 48).toArray();
        int[] suma_nieparzyste =
                kody.stream().mapToInt(it -> it.charAt(0) - 48 + it.charAt(2) - 48 + it.charAt(4) - 48).toArray();
        StringBuilder wynik6_1 = new StringBuilder("6.1.\n");
        for(int i = 0; i < suma_parzyste.length; i++)
            wynik6_1.append("%d %d\n".formatted(suma_parzyste[i], suma_nieparzyste[i]));

        //Zadanie 6.2.
        StringBuilder wynik6_2 = new StringBuilder("6.2.\n");
        for(int i = 0; i < suma_parzyste.length; i++)
        {
            int cyfra_kontrolna = (10 - (3 * suma_parzyste[i] + suma_nieparzyste[i]) % 10) % 10;
            wynik6_2.append("%d %s\n".formatted(cyfra_kontrolna, cyfry.get(cyfra_kontrolna)));
        }

        //Zadanie 6.3.
        StringBuilder wynik6_3 = new StringBuilder("6.3.\n");
        for(int i = 0; i < kody.size(); i++)
        {
            int cyfra_kontrolna = (10 - (3 * suma_parzyste[i] + suma_nieparzyste[i]) % 10) % 10;
            List<String> finalCyfry = cyfry;
            wynik6_3.append("11011010%s%s11010110\n".formatted(kody.get(i).chars().mapToObj(
                    it -> finalCyfry.get(it - 48)).collect(Collectors.joining()), cyfry.get(cyfra_kontrolna)));
        }

        //Pliki z wynikami
        try (FileWriter kody1 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015_6\\kody1.txt")) {
            kody1.write("");
            kody1.append(wynik6_1);
        }

        try (FileWriter kody2 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015_6\\kody2" +
                ".txt")) {
            kody2.write("");
            kody2.append(wynik6_2);
        }

        try (FileWriter kody3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015_6\\kody3" +
                ".txt")) {
            kody3.write("");
            kody3.append(wynik6_3);
        }

    }
}
