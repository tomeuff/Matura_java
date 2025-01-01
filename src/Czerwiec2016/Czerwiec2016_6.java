package Czerwiec2016;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Czerwiec2016_6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016_6\\liczby" +
                ".txt"));
        List<String> dane = new ArrayList<>();

        while(scanner.hasNext())
        {
            dane.add(scanner.next());
        }

        try (FileWriter wyniki6 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016_6\\wyniki6" +
                ".txt")) {

            //Zadanie 6.1.
            String wynik6_1 =
                    "6.1.\n%d\n\n".formatted(dane.stream().filter(it -> it.charAt(it.length() - 1) == '8').count());

            //Zadanie 6.2.
            String wynik6_2 =
                    "6.2.\n%d\n\n".formatted(dane.stream().filter(it -> it.charAt(it.length() - 1) == '4'
                            && !it.contains("0")).count());

            //Zadanie 6.3.
            String wynik6_3 = "6.3.\n%d\n\n".formatted(dane.stream().filter(it -> it.charAt(it.length() - 1) == '2'
                    && it.charAt(it.length() - 2) == '0').count());

            //Zadanie 6.4.
            String wynik6_4 =
                    "6.4.\n%d\n\n".formatted(dane.stream().filter(it -> it.charAt(it.length() - 1) == '8').mapToInt(it ->
                            Integer.parseInt(it.substring(0, it.length() - 1), 8)).sum());


            //Zadanie 6.5.
            List<Integer> liczby = dane.stream().mapToInt(it -> Integer.parseInt(it.substring(0, it.length() - 1),
                    Integer.parseInt(String.valueOf(it.charAt(it.length() - 1))))).mapToObj(Integer::valueOf).toList();

            int max_ind = liczby.indexOf(Collections.max(liczby));
            int min_ind = liczby.indexOf(Collections.min(liczby));

            String wynik6_5 =
                    "6.5.\n%s %d\n%s %d".formatted(dane.get(max_ind), liczby.get(max_ind), dane.get(min_ind),
                            liczby.get(min_ind));

            wyniki6.write("");
            wyniki6.append(wynik6_1);
            wyniki6.append(wynik6_2);
            wyniki6.append(wynik6_3);
            wyniki6.append(wynik6_4);
            wyniki6.append(wynik6_5);
        }
    }
}
