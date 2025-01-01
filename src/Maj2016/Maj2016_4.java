package Maj2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maj2016_4 {
    public static boolean czy_nalezy_do_kola(Punkt p, int a, int b, int r)
    {
        return (p.x - a) * (p.x - a) + (p.y - b) * (p.y - b) <= r * r;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016\\punkty.txt"));
        ArrayList<Punkt> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(new Punkt(scanner.nextInt(), scanner.nextInt()));

        final int r = 200;
        final int a = 200;
        final int b = 200;
        ArrayList<Punkt> do_brzegu = new ArrayList<>();
        int ile_do_wnetrza = 0;

        for(Punkt p : dane)
        {
            if((p.x - a) * (p.x - a) + (p.y - b) * (p.y - b) == r * r)
                do_brzegu.add(p);
            else if((p.x - a) * (p.x - a) + (p.y - b) * (p.y - b) < r * r)
                ile_do_wnetrza++;
        }

        String wynik4_1 = "4.1.\ndo brzegu:\n%s\ndo wnętrza: %d\n\n".formatted(do_brzegu.stream().
                map(it -> "(%d, %d)".formatted(it.x, it.y)).collect(Collectors.joining("\n")),
                ile_do_wnetrza);

        //Zadanie 4.2.
        String wynik4_2 = ("4.2.\npierwsze 1000 punktów: %.4f\npierwsze 5000 punktów: %.4f\n" +
                "wszystkie punkty: %.4f\n\n").
                formatted(4 * dane.subList(0, 1000).stream().filter(it -> czy_nalezy_do_kola(it, a, b, r)).count() / (double) 1000,
                4 * dane.subList(0, 5000).stream().filter(it -> czy_nalezy_do_kola(it, a, b, r)).count() / (double) 5000,
                        4 * dane.stream().filter(it -> czy_nalezy_do_kola(it, a, b, r)).count() / (double) 10000);
        //Zadanie 4.3.
        ArrayList<Double> epsilony = new ArrayList<>();

        for(int i = 0; i < 1700; i++)
            epsilony.add(Math.abs(Math.PI - 4 * dane.subList(0, i + 1).stream().filter(it -> czy_nalezy_do_kola(it, a, b, r)).count() / (double) (i + 1)));

        String wynik4_3 = "4.3.\nepsilon 1000: %.4f\nepsilon 1700: %.4f\n\n".formatted(epsilony.get(999), epsilony.get(1699));

        try (FileWriter wyniki_wykres = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016" +
                "\\wyniki_wykres.txt")) {
            wyniki_wykres.write("");
            for (int i = 0; i < epsilony.size(); i++)
                wyniki_wykres.append("%d %f\n".formatted(i, epsilony.get(i)));
        }

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016\\wyniki_4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
