package Maj2018_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Maj2018_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2018_4\\sygnaly" +
                ".txt"));
        List<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextLine());

        //Zadanie 4.1.
        StringBuilder przeslanie = new StringBuilder();
        for(int i = 39; i < dane.size(); i += 40)
            przeslanie.append(dane.get(i).charAt(9));

        String wynik4_1 = "4.1.\n%s\n\n".formatted(przeslanie.toString());

        //Zadanie 4.2.
        int max_l_r_l = 0;
        String max_s = "";
        for(int i = 0; i < dane.size(); i++)
        {
            Set<Character> zbior = dane.get(i).chars().mapToObj(it -> (char) it).collect(Collectors.toSet());
            if(zbior.size() > max_l_r_l)
            {
                max_l_r_l = zbior.size();
                max_s = dane.get(i);
            }
        }

        String wynik4_2 = "4.2.\n%s %d\n\n".formatted(max_s, max_l_r_l);

        //Zadanie 4.3.
        String wynik4_3 = "4.3\n%s\n".formatted(dane.stream().filter(it -> it.chars().max().getAsInt() -
                it.chars().min().getAsInt() <= 10).collect(Collectors.joining("\n")));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2018_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }

    }
}
