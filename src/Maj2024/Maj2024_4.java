package Maj2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2024_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2024\\liczby.txt"));
        ArrayList<Integer> dane1 = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> dane2 = Arrays.stream(scanner.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));

        //Zadanie 4.1.
        int ile_dziel = 0;
        for(Integer liczba1 : dane1)
        {
            for(Integer liczba2: dane2)
            {
                if(liczba2 % liczba1 == 0)
                {
                    ile_dziel++;
                    break;
                }
            }
        }

        String wynik4_1 = "4.1.\n%d\n\n".formatted(ile_dziel);

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%d\n\n".formatted(dane1.stream().sorted((a, b) -> -a.compareTo(b)).
                toList().get(100));

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");

        for(Integer liczba2 : dane2)
        {
            Integer liczba = liczba2;
            for(Integer liczba1 : dane1)
            {
                if(liczba % liczba1 == 0)
                    liczba = liczba / liczba1;
            }
            if(liczba == 1)
                wynik4_3.append("%d\n".formatted(liczba2));
        }
        wynik4_3.append("\n");

        //Zadanie 4.4.
        double max_sr = 0;
        int max_p = 0, max_k = 0;
        for(int p = 0; p < dane1.size(); p++)
        {
            for(int k = p + 49; k < dane1.size(); k++)
            {
                double srednia = 0;

                for(int i = p; i <= k; i++)
                    srednia += dane1.get(i);

                srednia = srednia / (k - p + 1);

                if(srednia > max_sr)
                {
                    max_sr = srednia;
                    max_p = p;
                    max_k = k;
                }
            }
        }
        String wynik4_4 = "4.4.\n%f %d %d\n\n".formatted(max_sr, max_k - max_p + 1, dane1.get(max_p));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2024\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
