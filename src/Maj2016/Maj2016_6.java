package Maj2016;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2016_6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner1 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6\\dane_6_1" +
                ".txt"));
        List<String> dane1 = new ArrayList<>();
        while(scanner1.hasNext())
            dane1.add(scanner1.next());

        Scanner scanner2 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6\\dane_6_2" +
                ".txt"));
        List<String> dane2 = new ArrayList<>();
        while(scanner2.hasNext())
            dane2.add(scanner2.nextLine());

        Scanner scanner3 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6\\dane_6_3" +
                ".txt"));
        List<String> dane3 = new ArrayList<>();
        while(scanner3.hasNext())
            dane3.add(scanner3.nextLine());

        //Zadanie 6.1.
        StringBuilder wynik6_1 = new StringBuilder();
        wynik6_1.append(dane1.stream().map(it1 -> it1.chars().map(it2 -> it2 + (107 % 26) > 90 ?
                it2 + (107 % 26) - 26 : it2 + (107 % 26)).mapToObj(it3 -> (char) it3).
                map(String::valueOf).collect(Collectors.joining())).collect(Collectors.joining("\n")));

        try (FileWriter wyniki6_1 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6\\wyniki_6_1" +
                ".txt")) {
            wyniki6_1.write("");
            wyniki6_1.append(wynik6_1);
        }

        //Zadanie 6.2.
        StringBuilder wynik6_2 = new StringBuilder();
        wynik6_2.append(dane2.stream().map(it1 -> it1.split(" ")[0].chars().map(it2 -> it2 - (Integer.parseInt(it1.split(" ")[1]) % 26) < 65 ?
                        it2 - (Integer.parseInt(it1.split(" ")[1]) % 26) + 26 :
                        it2 - (Integer.parseInt(it1.split(" ")[1]) % 26)).mapToObj(it3 -> (char) it3).
                map(String::valueOf).collect(Collectors.joining())).collect(Collectors.joining("\n")));

        try (FileWriter wyniki6_2 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6" +
                "\\wyniki_6_2.txt")) {
            wyniki6_2.write("");
            wyniki6_2.append(wynik6_2);
        }

        //Zadanie 6.3.
        StringBuilder wynik6_3 = new StringBuilder();

        for(int i = 0; i < dane3.size(); i++)
        {
            String[] w = dane3.get(i).split(" ");
            String tj = w[0];
            String sz = w[1];

            int k = sz.charAt(0) - tj.charAt(0);
            if(k < 0)
                k += 26;

            StringBuilder wynik = new StringBuilder();

            for(int j = 0; j < tj.length(); j++)
                wynik.append(tj.charAt(j) + k > 90 ? (char)(tj.charAt(j) + k - 26) : (char)(tj.charAt(j) + k));

            if(!wynik.toString().equals(sz))
                wynik6_3.append("%s\n".formatted(tj));
        }
        try (FileWriter wyniki6_3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016_6" +
                "\\wyniki_6_3.txt")) {
            wyniki6_3.write("");
            wyniki6_3.append(wynik6_3);
        }
    }
}
