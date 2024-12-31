package Maj2015_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Maj2015_5 {
    public static void main(String[] args) throws IOException {
        ArrayList<Wojewodztwo> kraina = new ArrayList<>();
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_5\\kraina.txt"));

        while(scanner.hasNext())
        {
            String wiersz_napis = scanner.nextLine();
            String[] wiersz = wiersz_napis.split(";");
            kraina.add(new Wojewodztwo(wiersz[0], Integer.parseInt(wiersz[1]), Integer.parseInt(wiersz[2]),
                    Integer.parseInt(wiersz[3]), Integer.parseInt(wiersz[4])));
        }

        //zadanie 5.1.
        HashMap<String, Integer> LR2013 = new HashMap<>();

        for(Wojewodztwo w : kraina)
        {
            if(LR2013.containsKey(w.region))
            {
                LR2013.put(w.region, LR2013.get(w.region) + w.Ludnosc.get(2013));
            }
            else
            {
                LR2013.put(w.region, w.Ludnosc.get(2013));
            }
        }

        StringBuilder wynik5_1 = new StringBuilder("5.1\n");

        for(String k : LR2013.keySet())
        {
            wynik5_1.append("%s %d\n".formatted(k, LR2013.get(k)));
        }

        wynik5_1.append("\n");

        //Zadanie 5.2.
        StringBuilder wynik5_2 = new StringBuilder("5.2.\n");

        int l_woj_2014_wieksze_niz_2013 = 0;
        HashMap<String, Integer> R2013_wieksze_niz_2013 = new HashMap<>();

        for(Wojewodztwo w : kraina)
        {
            if(w.Mezczyzni.get(2014) > w.Mezczyzni.get(2013) && w.Kobiety.get(2014) > w.Kobiety.get(2013))
            {
                l_woj_2014_wieksze_niz_2013++;
                if(R2013_wieksze_niz_2013.containsKey(w.region))
                {
                    R2013_wieksze_niz_2013.put(w.region, R2013_wieksze_niz_2013.get(w.region) + 1);
                }
                else
                {
                    R2013_wieksze_niz_2013.put(w.region, 1);
                }
            }
        }

        wynik5_2.append("liczba województw: %d\n".formatted(l_woj_2014_wieksze_niz_2013));

        for(String k : R2013_wieksze_niz_2013.keySet())
        {
            wynik5_2.append("%s: %d\n".formatted(k, R2013_wieksze_niz_2013.get(k)));
        }

        wynik5_2.append("\n");

        //Zadanie 5.3.
        for(int rok = 2015; rok <= 2025; rok++)
        {
            for(Wojewodztwo w : kraina)
            {
                if(w.Ludnosc.get(rok - 1) > 2 * w.Ludnosc.get(2013))
                    w.Ludnosc.put(rok, w.Ludnosc.get(rok - 1));
                else
                    w.Ludnosc.put(rok, (int) Math.floor(w.Ludnosc.get(rok - 1) * w.tempo));
            }
        }

        int ludnosc_2025 = 0;
        int max_ludnosc_2025 = 0;
        String max_woj_2025 = "";
        int ile_przel = 0;

        for(Wojewodztwo w : kraina)
        {
            ludnosc_2025 += w.Ludnosc.get(2025);
            if(w.Ludnosc.get(2025) > max_ludnosc_2025)
            {
                max_ludnosc_2025 = w.Ludnosc.get(2025);
                max_woj_2025 = w.nazwa_woj;
            }
            if(w.Ludnosc.get(2025) > 2 * w.Ludnosc.get(2013))
                ile_przel++;
        }

        String wynik5_3 = ("5.3.\nliczba wszystkich mieszkańców: %d\n" +
                "województwo z największą liczbą mieszkańców: %s\n" +
                "liczba województw z przeludnieniem: %d\n").formatted(ludnosc_2025, max_woj_2025, ile_przel);

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_5\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
        }
    }
}
