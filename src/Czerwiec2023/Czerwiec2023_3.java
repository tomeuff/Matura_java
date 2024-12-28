package Czerwiec2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Czerwiec2023_3 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023\\anagram.txt"));
        ArrayList<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.next());

        //Zadanie 3.1.
        long zr = dane.stream().filter(it1 -> 2 * it1.chars().filter(it2 -> it2 == 48).
                count() == it1.length()).count();
        long pzr = dane.stream().filter(it1 -> Math.abs(2 * it1.chars().filter(it2 -> it2 == 48).
                count() - it1.length()) == 1).count();
        String wynik3_1 = "3.1.\n%d\n%d\n\n".formatted(zr, pzr);

        //Zadanie 3.2.
        String wynik3_2 = "3.2\n%s\n\n".formatted(dane.stream().filter(it1 ->
                it1.length() == 8 && (it1.chars().filter(it2 -> it2 == 48).count() == 3 ||
                        it1.chars().filter(it2 -> it2 == 48).count() == 4)).collect(Collectors.joining("\n")));

        //Zadanie 3.3.
        long max_r_int = 0;
        for(int i = 1; i < dane.size(); i++)
        {
            long r = Math.abs(Long.parseLong(dane.get(i), 2) - Long.parseLong(dane.get(i - 1), 2));
            if(r > max_r_int)
                max_r_int = r;
        }
        String wynik3_3 = "3.3.\n%s\n\n".formatted(Long.toString(max_r_int, 2));

        //Zadanie 3.4.
        int ile_bez_0 = 0;
        int max_s_r_c = 0;
        long max_10 = 0;

        for(String liczba_bin : dane)
        {
            long liczba_10 = Long.parseLong(liczba_bin, 2);

            if(!Long.toString(liczba_10).contains("0"))
                ile_bez_0++;

            int suma_cyfr = Long.toString(liczba_10).chars().mapToObj(it -> (char) it).collect(
                    Collectors.toCollection(HashSet::new)).stream().map(it -> (int)(it - 48)).
                    reduce(0, Integer::sum);
            if(suma_cyfr > max_s_r_c)
            {
                max_s_r_c = suma_cyfr;
                max_10 = liczba_10;
            }
        }

        String wynik3_4 = "3.4.\n%d\n%d\n".formatted(ile_bez_0, max_10);

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_1);
            wynik3.append(wynik3_2);
            wynik3.append(wynik3_3);
            wynik3.append(wynik3_4);
        }
    }
}
