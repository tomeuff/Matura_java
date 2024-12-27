package Grudzien2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Grudzien2024_3 {
    public static int l_r_d_p(int liczba)
    {
        int ile = 0;
        for(int d = 2; d <= liczba; d++)
        {
            if(liczba % d == 0)
                ile++;
            while(liczba % d == 0)
                liczba /= d;
            if(liczba == 1)
                break;
        }
        return ile;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Grudzien2024\\liczby.txt"));
        ArrayList<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        //Zadanie 3.1.
        int ile_kw = 0;
        int pierwsza = 0;
        ArrayList<Integer> kwadraty = new ArrayList<>();
        for(int i = 1; i <= 100; i++)
            kwadraty.add(i * i);

        for(Integer liczba : dane)
            if(kwadraty.contains(liczba))
            {
                ile_kw++;
                if(ile_kw == 1)
                    pierwsza = liczba;
            }

        String wynik3_1 = "3.1.\n%d %d\n\n".formatted(ile_kw, pierwsza);

        //Zadanie 4.2.
        StringBuilder wynik3_2 = new StringBuilder("3.2.\n");
        for(Integer liczba : dane)
        {
            if(l_r_d_p(liczba) >= 5)
                wynik3_2.append("%d\n".formatted(liczba));
        }

        wynik3_2.append("\n");

        //Zadanie 4.3.
        int w = 0, m = 0;
        ArrayList<Integer> l_r = new ArrayList<>();
        for(Integer liczba : dane)
        {
            String min_liczba = liczba.toString().chars().sorted().mapToObj(it ->
                    (char) it).map(String::valueOf).reduce("", (x, y) -> x + y);
            String max_liczba = new StringBuilder(min_liczba).reverse().toString();

            int roznica = Integer.parseInt(max_liczba) - Integer.parseInt(min_liczba);

            if(roznica > liczba)
                w++;
            if(roznica == liczba)
                l_r.add(liczba);
            if(roznica < liczba)
                m++;
        }

        String wynik3_3 = "3.3.\nwieksza: %d\nmniejsza: %d\nrówna: %d\nliczby równe:\n%s".formatted(w, m, l_r.size(),
                l_r.stream().map(String::valueOf).collect(Collectors.joining("\n")));

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Grudzien2024\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_1);
            wynik3.append(wynik3_2);
            wynik3.append(wynik3_3);
        }
    }
}
