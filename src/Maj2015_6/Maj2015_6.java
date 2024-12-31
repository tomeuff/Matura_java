package Maj2015_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Maj2015_6 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_kierowcy = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_6" +
                "\\Kierowcy.txt"));
        Scanner scanner_wyscigi = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_6" +
                "\\Wyscigi.txt"));
        Scanner scanner_wyniki = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_6" +
                "\\Wyniki.txt"));

        ArrayList<Kierowca> Kierowcy = new ArrayList<>();
        ArrayList<Wyscig> Wyscigi = new ArrayList<>();
        ArrayList<Wynik> Wyniki = new ArrayList<>();

        scanner_kierowcy.nextLine();
        while(scanner_kierowcy.hasNext())
        {
            String[] linia = scanner_kierowcy.nextLine().split(";");
            Kierowcy.add(new Kierowca(linia[0], linia[1], linia[2], linia[3]));
        }

        scanner_wyscigi.nextLine();
        while(scanner_wyscigi.hasNext())
        {
            String[] linia = scanner_wyscigi.nextLine().split(";");
            Wyscigi.add(new Wyscig(linia[0], Integer.parseInt(linia[1]), linia[2]));
        }

        scanner_wyniki.nextLine();
        while(scanner_wyniki.hasNext())
        {
            String[] linia = scanner_wyniki.nextLine().split(";");
            Wyniki.add(new Wynik(linia[0], Double.parseDouble(linia[1].replace(",", ".")), linia[2]));
        }

        //Zadanie 6.1.
        int max_sezon = 0;
        String max_GrandPrix = "";
        double max_pkt = 0;

        for(Kierowca k : Kierowcy)
        {
            if(Objects.equals(k.Nazwisko, "Kubica") && Objects.equals(k.Imie, "Robert"))
            {
                for(Wynik wyn : Wyniki)
                {
                    if(Objects.equals(wyn.Id_kierowcy, k.Id_kierowcy))
                    {
                        if(wyn.Punkty > max_pkt)
                        {
                            max_pkt = wyn.Punkty;
                            for(Wyscig wys : Wyscigi)
                            {
                                if(Objects.equals(wyn.Id_wyscigu, wys.Id_wyscigu))
                                {
                                    max_sezon = wys.Rok;
                                    max_GrandPrix = wys.GrandPrix;
                                }
                            }
                        }
                    }
                }
            }
        }

        String wynik6_1 = "6.1.\n%d\n%s\n\n".formatted(max_sezon, max_GrandPrix);

        //Zadanie 6.2.
        HashMap<String, Integer> LW = new HashMap<>();

        for(Wyscig wys : Wyscigi)
        {
            if(LW.containsKey(wys.GrandPrix))
            {
                LW.put(wys.GrandPrix, LW.get(wys.GrandPrix) + 1);
            }
            else
            {
                LW.put(wys.GrandPrix, 1);
            }
        }

        Map.Entry<String, Integer> min_miejsce = Collections.min(LW.entrySet(), Map.Entry.comparingByValue());

        String wynik6_2 = "6.2.\n%s".formatted(min_miejsce.getKey());

        //Zadanie 6.3.
        StringBuilder wynik6_3 = new StringBuilder("6.3.\n");

        int[] lata = new int[]{2000, 2006, 2012};

        for(int i = 0; i < lata.length; i++)
        {
            int rok = lata[i];
            HashMap<Kierowca, Double> KG = new HashMap<>();

            for(Kierowca k : Kierowcy)
            {
                for(Wynik wyn : Wyniki)
                {
                    int rok_wys = 0;
                    for(Wyscig wys : Wyscigi)
                    {
                        if(Objects.equals(wys.Id_wyscigu, wyn.Id_wyscigu))
                            rok_wys = wys.Rok;
                    }
                    if(Objects.equals(wyn.Id_kierowcy, k.Id_kierowcy) && rok_wys == rok)
                    {
                        if(KG.containsKey(k))
                        {
                            KG.put(k, KG.get(k) + wyn.Punkty);
                        }
                        else
                        {
                            KG.put(k, wyn.Punkty);
                        }
                    }
                }
            }

            Map.Entry<Kierowca, Double> max_kier = Collections.max(KG.entrySet(), Map.Entry.comparingByValue());

            wynik6_3.append("%d %s %s %.2f\n".formatted(rok, max_kier.getKey().Imie,
                    max_kier.getKey().Nazwisko, KG.get(max_kier.getKey())));

            wynik6_3.append("\n");
        }

        //Zadanie 6.4.
        HashMap<Kierowca, Integer> KierP = new HashMap<>();

        for(Wynik wyn : Wyniki)
        {
            int rok_wys = 0;
            for(Wyscig wys : Wyscigi)
            {
                if(Objects.equals(wyn.Id_wyscigu, wys.Id_wyscigu))
                    rok_wys = wys.Rok;
            }

            if(rok_wys == 2012)
            {
                Kierowca k = new Kierowca("", "", "", "");

                for(Kierowca kier : Kierowcy)
                {
                    if(Objects.equals(kier.Id_kierowcy, wyn.Id_kierowcy))
                        k = kier;
                }

                if(KierP.containsKey(k))
                    KierP.put(k, KierP.get(k) + 1);
                else
                    KierP.put(k, 1);
            }
        }

        HashMap<String, Integer> KrajP = new HashMap<>();

        for(Kierowca k : KierP.keySet())
        {
            if(KrajP.containsKey(k.Kraj))
                KrajP.put(k.Kraj, KrajP.get(k.Kraj) + 1);
            else
                KrajP.put(k.Kraj, 1);
        }

        StringBuilder wynik6_4 = new StringBuilder("6.4.\n");

        for(String kraj : KrajP.keySet())
            wynik6_4.append("%s %d\n".formatted(kraj, KrajP.get(kraj)));

        wynik6_4.append("\n");

        try (FileWriter wynik6 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2015_6\\wyniki6.txt")) {
            wynik6.write("");
            wynik6.append(wynik6_1);
            wynik6.append(wynik6_2);
            wynik6.append(wynik6_3);
            wynik6.append(wynik6_4);
        }
    }
}
