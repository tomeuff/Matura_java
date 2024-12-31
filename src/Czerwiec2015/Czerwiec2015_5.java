package Czerwiec2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Czerwiec2015_5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_kierowcy = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015\\kierowcy.txt"));
        Scanner scanner_mandaty = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015" +
                "\\mandaty.txt"));
        Scanner scanner_wykroczenia = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src" +
                "\\Czerwiec2015\\wykroczenia.txt"));

        HashMap<String, Kierowca> kierowcy = new HashMap<>();
        HashMap<Integer, Mandat> mandaty = new HashMap<>();
        HashMap<Integer, Wykroczenie> wykroczenia = new HashMap<>();

        scanner_kierowcy.nextLine();
        while(scanner_kierowcy.hasNext())
        {
            String[] linia = scanner_kierowcy.nextLine().split("\t");
            kierowcy.put(linia[0], new Kierowca(linia[0], linia[1], linia[2]));
        }

        int nr_mandatu = 1;
        scanner_mandaty.nextLine();
        while(scanner_mandaty.hasNext())
        {
            String[] linia = scanner_mandaty.nextLine().split("\t");
            mandaty.put(nr_mandatu, new Mandat(linia[0], linia[1], Integer.parseInt(linia[2])));
            nr_mandatu++;
        }

        scanner_wykroczenia.nextLine();
        while(scanner_wykroczenia.hasNext())
        {
            String[] linia = scanner_wykroczenia.nextLine().split("\t");
            wykroczenia.put(Integer.parseInt(linia[0]), new Wykroczenie(Integer.parseInt(linia[0]),
                    linia[1], Integer.parseInt(linia[2]), Integer.parseInt(linia[3])));
        }

        //Zadanie 5.1.
        HashMap<Integer, Integer> WK = new HashMap<>();

        for(Mandat m : mandaty.values())
        {
            if(WK.containsKey(m.kod_wyk))
                WK.put(m.kod_wyk, WK.get(m.kod_wyk) + 1);
            else
                WK.put(m.kod_wyk, 1);
        }

        Map.Entry<Integer, Integer> max_wyk = Collections.max(WK.entrySet(), Map.Entry.comparingByValue());

        String wynik5_1 = "5.1.\nnazwa: %s\nliczba popełnień: %d\n\n".formatted(wykroczenia.get(
                max_wyk.getKey()).nazwa, max_wyk.getValue());

        //Zadanie 5.2.
        HashMap<String, Integer> K2013P = new HashMap<>();

        for(Kierowca k : kierowcy.values())
        {
            if(k.data_prawa_jazdy.startsWith("2013"))
            {
                for(Mandat m : mandaty.values())
                {
                    if(Objects.equals(m.pesel, k.pesel))
                    {
                        if(K2013P.containsKey(k.pesel))
                            K2013P.put(k.pesel, K2013P.get(k.pesel) + wykroczenia.get(m.kod_wyk).punkty);
                        else
                            K2013P.put(k.pesel, wykroczenia.get(m.kod_wyk).punkty);
                    }
                }
            }
        }

        StringBuilder wynik5_2 = new StringBuilder("5.2.\n");

        for(String pesel : K2013P.keySet())
            if(K2013P.get(pesel) > 20)
                wynik5_2.append("%s %d\n".formatted(pesel, K2013P.get(pesel)));

        wynik5_2.append("\n");

        //Zadanie 5.3.
        StringBuilder wynik5_3 = new StringBuilder("5.3.\n");

        for(Wykroczenie w : wykroczenia.values())
        {
            if(w.nazwa.toLowerCase().contains("naruszenie zakazu"))
                wynik5_3.append("%s\n".formatted(w.nazwa));
        }

        wynik5_3.append("\n");

        //Zadanie 5.4.
        HashMap<String, Miesiac> MWPL = new HashMap<>();

        for(Mandat m : mandaty.values())
        {
            String mies = m.data_wyk.substring(5, 7);
            if(MWPL.containsKey(mies))
                MWPL.put(mies, new Miesiac(MWPL.get(mies).kwota + wykroczenia.get(m.kod_wyk).mandat,
                        MWPL.get(mies).liczba + 1));
            else
                MWPL.put(mies, new Miesiac(wykroczenia.get(m.kod_wyk).mandat, 1));
        }

        Map.Entry<String, Miesiac> MWPL_entry = Collections.min(MWPL.entrySet(),
                Comparator.comparingInt(it -> it.getValue().liczba));

        String wynik5_4 = "5.4.\n%s %d %d\n\n".formatted(MWPL_entry.getKey(), MWPL_entry.getValue().kwota,
                MWPL_entry.getValue().liczba);

        //Zadanie 5.5.
        ArrayList<Kierowca> kierowcy_bez_mandatu = new ArrayList<>();

        for(Kierowca k : kierowcy.values())
        {
            boolean czy_byl = false;
            for(Mandat m : mandaty.values())
                if(Objects.equals(m.pesel, k.pesel))
                {
                    czy_byl = true;
                    break;
                }
            if(!czy_byl)
                kierowcy_bez_mandatu.add(k);
        }

        HashMap<String, Integer> MKBM = new HashMap<>();

        for(Kierowca k : kierowcy_bez_mandatu)
        {
            if(MKBM.containsKey(k.miasto))
                MKBM.put(k.miasto, MKBM.get(k.miasto) + 1);
            else
                MKBM.put(k.miasto, 1);
        }

        Map.Entry<String, Integer> MKBM_max = Collections.max(MKBM.entrySet(), Map.Entry.comparingByValue());

        String wynik5_5 = "5.5.\nliczba kierowców: %d\nmiasto: %s\n\n".formatted(kierowcy_bez_mandatu.size(),
                MKBM_max.getKey());

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
            wynik5.append(wynik5_4);
            wynik5.append(wynik5_5);
        }
    }
}
