package Czerwiec2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Czerwiec2024_3 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2024\\slowa.txt"));
        ArrayList<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextLine());

        //Zadanie 3.1.
        String wynik3_1 = "3.1.\n%d\n\n".formatted(dane.stream().filter(it -> it.matches(".*k.t.*")).count());

        //Zadanie 3.2.
        ArrayList<String> wynik = new ArrayList<>();

        for(String s : dane)
        {
            String s_rot13 = s.chars().mapToObj(it -> (char) (it + 13 > 122 ? it + 13 - 26 : it + 13)).
                    map(String::valueOf).collect(Collectors.joining());
            if(s_rot13.contentEquals(new StringBuilder(s).reverse()))
                wynik.add(s);
        }
        String wynik3_2 = "3.2.\n%d %s\n\n".formatted(wynik.size(),
                Collections.max(wynik, Comparator.comparingInt(String::length)));

        //Zadanie 3.3.
        StringBuilder wynik3_3 = new StringBuilder("3.3.\n");
        for(String s : dane)
        {
            for(char z = 'a'; z <= 'z'; z++)
            {
                char finalZ = z;
                if(2 * s.chars().filter(it -> it == finalZ).count() >= s.length())
                {
                    wynik3_3.append("%s\n".formatted(s));
                    break;
                }
            }
        }
        wynik3_3.append("\n");

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2024\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_1);
            wynik3.append(wynik3_2);
            wynik3.append(wynik3_3);
        }
    }
}
