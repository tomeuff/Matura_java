package Marzec2021_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Marzec2021_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2021_4\\galerie.txt"));
        ArrayList<Galeria> dane = new ArrayList<>();

        while(scanner.hasNext())
        {
            String wiersz = scanner.nextLine();
            ArrayList<String> wiersz_lista = new ArrayList<>(Arrays.stream(wiersz.split(" ")).toList());
            dane.add(new Galeria(wiersz_lista.get(0), wiersz_lista.get(1),
                    new ArrayList<>(wiersz_lista.subList(2, 142).stream().map(Integer::parseInt).collect(Collectors.toList()))));
        }

        //Zadanie 4.1.
        StringBuilder wynik4_1 = new StringBuilder("4.1.\n");

        HashMap<String, Integer> hmKraje = new HashMap<>();

        for(Galeria g : dane)
        {
            if(hmKraje.containsKey(g.kraj))
                hmKraje.replace(g.kraj, hmKraje.get(g.kraj) + 1);
            else
                hmKraje.put(g.kraj, 1);
        }

        for(String k : hmKraje.keySet())
            wynik4_1.append("%s %d\n".formatted(k, hmKraje.get(k)));
        wynik4_1.append("\n");

        //Zadanie 4.2.
        StringBuilder wynik4_2 = new StringBuilder("4.2.\na)\n");

        for(Galeria g : dane)
        {
            HashSet<Integer> r_lokale = new HashSet<>();
            int l_lokali = 0;
            int c_pow = 0;

            for(int i = 0; i < 140; i += 2)
            {
                if(g.lokale.get(i) != 0)
                {
                    l_lokali++;
                    c_pow += g.lokale.get(i) * g.lokale.get(i + 1);
                    r_lokale.add(g.lokale.get(i) * g.lokale.get(i + 1));
                }
            }
            g.c_powierzchnia = c_pow;
            g.l_r_lokali = r_lokale.size();
            wynik4_2.append("%s %d %d\n".formatted(g.miasto, c_pow, l_lokali));
        }

        wynik4_2.append("\n");

        Galeria g_max_c_pow = dane.stream().max((g1, g2) -> g1.c_powierzchnia - g2.c_powierzchnia).get();
        Galeria g_min_c_pow = dane.stream().min((g1, g2) -> g1.c_powierzchnia - g2.c_powierzchnia).get();

        wynik4_2.append("b)\n");
        wynik4_2.append("%s %d\n".formatted(g_max_c_pow.miasto, g_max_c_pow.c_powierzchnia));
        wynik4_2.append("%s %d\n\n".formatted(g_min_c_pow.miasto, g_min_c_pow.c_powierzchnia));

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");

        Galeria g_max_l_r_l = dane.stream().max((g1, g2) -> g1.l_r_lokali - g2.l_r_lokali).get();
        Galeria g_min_l_r_l = dane.stream().min((g1, g2) -> g1.l_r_lokali - g2.l_r_lokali).get();

        wynik4_3.append("%s %d\n".formatted(g_max_l_r_l.miasto, g_max_l_r_l.l_r_lokali));
        wynik4_3.append("%s %d\n\n".formatted(g_min_l_r_l.miasto, g_min_l_r_l.l_r_lokali));

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2021_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
        }
    }
}
