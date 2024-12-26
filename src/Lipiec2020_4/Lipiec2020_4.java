package Lipiec2020_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Lipiec2020_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Lipiec2020_4\\identyfikator.txt"));
        ArrayList<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.next());

        //Zadanie 4.1.
        int max_suma = dane.stream().mapToInt(i -> i.substring(3).chars().sum()).max().getAsInt();
        String wynik4_1 = "4.1.\n%s\n\n".formatted(dane.stream().filter(it -> it.substring(3).chars().sum() ==
                        max_suma).collect(Collectors.joining("\n")));

        //Zadanie 4.2.
        String wynik4_2 =
                "4.2.\n%s\n\n".formatted(dane.stream().filter(i -> new StringBuilder(i.substring(0, 3)).reverse().toString().equals(
                        i.substring(0, 3)) || new StringBuilder(i.substring(3)).reverse().toString().equals(
                        i.substring(3))
                ).collect(Collectors.joining("\n")));

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");
        ArrayList<Integer> wagi = new ArrayList<>(List.of(new Integer[]{7, 3, 1, 0, 7, 3, 1, 7, 3}));
        ArrayList<String> poprawne = new ArrayList<>();

        for(String i : dane)
        {
            int test = 0;
            List<Character> i_lista = i.chars().mapToObj(it -> (char) it).toList();
            for(int ind = 0; ind < i_lista.size(); ind++)
            {
                if(i_lista.get(ind) >= 65 && i_lista.get(ind) <= 90)
                    test += (i_lista.get(ind) - 55) * wagi.get(ind);
                else
                    test += (i_lista.get(ind) - 48) * wagi.get(ind);
            }
            if(test % 10 != Integer.parseInt(i.substring(3, 4)))
                wynik4_3.append("%s\n".formatted(i));
        }

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Lipiec2020_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
