package Czerwiec2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class czerwiec2016_5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_maturzysta = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016\\maturzysta.txt"));
        HashMap<Integer, Maturzysta> maturzysci = new HashMap<>();

        scanner_maturzysta.nextLine();

        while(scanner_maturzysta.hasNext())
        {
            String[] linia = scanner_maturzysta.nextLine().split(";");
            maturzysci.put(Integer.parseInt(linia[0]), new Maturzysta(Integer.parseInt(linia[0]),
                    linia[1], linia[2], linia[3], linia[4]));
        }

        Scanner scanner_przedmiot = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016" +
                "\\przedmioty.txt"));
        HashMap<Integer, Przedmiot> przedmioty = new HashMap<>();

        scanner_przedmiot.nextLine();

        while(scanner_przedmiot.hasNext())
        {
            String[] linia = scanner_przedmiot.nextLine().split(";");
            przedmioty.put(Integer.parseInt(linia[0]), new Przedmiot(Integer.parseInt(linia[0]),
                    linia[1], linia[2], linia[3], linia[4]));
        }

        Scanner scanner_zdawanie = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016" +
                "\\zdaje.txt"));
        HashMap<Integer, Zdawanie> zdawania = new HashMap<>();

        scanner_zdawanie.nextLine();

        int nr_zdawania = 1;
        while(scanner_zdawanie.hasNext())
        {
            String[] linia = scanner_zdawanie.nextLine().split(";");
            zdawania.put(nr_zdawania, new Zdawanie(Integer.parseInt(linia[0]),
                    Integer.parseInt(linia[1])));
            nr_zdawania++;
        }

        //Zadanie 5.1.
        ArrayList<Maturzysta> maturzysci_informatyka = new ArrayList<>();

        for(Zdawanie zd : zdawania.values())
            if(zd.Id_przedmiotu == 15)
                maturzysci_informatyka.add(maturzysci.get(zd.Id_zdajacego));

        maturzysci_informatyka.sort(Comparator.comparing(it -> it.Nazwisko));

        StringBuilder wynik5_1 = new StringBuilder("5.1.\n");

        for(Maturzysta m : maturzysci_informatyka)
            wynik5_1.append("%s %s\n".formatted(m.Imie, m.Nazwisko));
        wynik5_1.append("\n");

        //Zadanie 5.2.
        HashMap<Przedmiot, Integer> PDC = new HashMap<>();

        for(Zdawanie zd : zdawania.values())
        {
            if(Objects.equals(przedmioty.get(zd.Id_przedmiotu).Typ, "dodatkowy"))
            {
                Przedmiot przedm = przedmioty.get(zd.Id_przedmiotu);

                if(PDC.containsKey(przedm))
                    PDC.put(przedm, PDC.get(przedm) + 1);
                else
                    PDC.put(przedm, 1);

            }
        }

        Map.Entry<Przedmiot, Integer> PDC_max = Collections.max(PDC.entrySet(), Map.Entry.comparingByValue());

        String wynik5_2 = "5.2.\n%s %d\n\n".formatted(PDC_max.getKey().Nazwa_przedmiotu, PDC_max.getValue());

        //Zadanie 5.3.
        HashMap<Maturzysta, Integer> ZPD = new HashMap<>();

        for(Maturzysta m : maturzysci.values())
        {
            int ile_dod = 0;
            for(Zdawanie zd : zdawania.values())
            {
                if(Objects.equals(przedmioty.get(zd.Id_przedmiotu).Typ, "dodatkowy") && zd.Id_zdajacego == m.Id_zdajacego)
                    ile_dod++;
            }
            ZPD.put(m, ile_dod);
        }

        int max_l_p_d = Collections.max(ZPD.entrySet(), Map.Entry.comparingByValue()).getValue();

        String wynik5_3 = "5.3.\n%s\n\n".formatted(ZPD.entrySet().stream().filter(it -> it.getValue() == max_l_p_d).
                map(it -> "%s %s %d\n".formatted(it.getKey().Imie, it.getKey().Nazwisko, it.getValue())).collect(Collectors.joining()));

        //Zadanie 5.4.
        StringBuilder wynik5_4 = new StringBuilder("5.4.\n");

        for(Przedmiot p : przedmioty.values())
        {
            boolean czy_byl = false;

            for(Zdawanie zd : zdawania.values())
            {
                if(zd.Id_przedmiotu == p.Id_przedmiotu)
                {
                    czy_byl = true;
                    break;
                }
            }

            if(!czy_byl)
                wynik5_4.append("%s\n".formatted(p.Nazwa_przedmiotu));
        }

        wynik5_4.append("\n");

        //Zadanie 5.5.
        Map.Entry<Integer, Maturzysta> najmlodszy_maturzysta = Collections.max(maturzysci.entrySet(), Map.Entry.comparingByValue(Comparator.comparing(a -> a.Data_urodzenia)));
        StringBuilder wynik5_5 =
                new StringBuilder("5.5.\n%s %s\n".formatted(najmlodszy_maturzysta.getValue().Imie, najmlodszy_maturzysta.getValue().Nazwisko));
        for(Zdawanie zd : zdawania.values())
        {
            if(zd.Id_zdajacego == najmlodszy_maturzysta.getValue().Id_zdajacego && Objects.equals(przedmioty.get(zd.Id_przedmiotu).Typ, "dodatkowy"))
                wynik5_5.append("%s\n".formatted(przedmioty.get(zd.Id_przedmiotu).Nazwa_przedmiotu));
        }

        wynik5_5.append("\n");

        //Zadanie 5.6.
        int l_m = 0;

        for(Maturzysta m : maturzysci.values())
            if(Integer.parseInt(m.PESEL.substring(9, 10)) % 2 == 1)
                l_m++;

        String wynik5_6 = "5.6.\n%d\n".formatted(l_m);

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016\\wyniki_5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
            wynik5.append(wynik5_4);
            wynik5.append(wynik5_5);
            wynik5.append(wynik5_6);
        }
    }
}
