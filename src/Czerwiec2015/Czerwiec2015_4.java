package Czerwiec2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Czerwiec2015_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015\\piastek.txt"));
        ArrayList<Dzien> dane = new ArrayList<>();

        String data_pocz = "2014-10-15";
        String data_kon = "2015-04-15";
        String format_daty = "yyyy-MM-dd";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_daty);

        LocalDate pocz = LocalDate.parse(data_pocz, formatter);
        LocalDate kon = LocalDate.parse(data_kon, formatter);

        while(!pocz.isAfter(kon))
        {
            dane.add(new Dzien(pocz.format(formatter), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt()));
            if(dane.size() == 1)
            {
                dane.get(0).kostka_przed_dostawa = 80;
                dane.get(0).orzech_przed_dostawa = 80;
                dane.get(0).mial_przed_dostawa = 80;
            }
            pocz = pocz.plusDays(1);
        }

        //Zadanie 4.1.
        StringBuilder wynik4_1 = new StringBuilder("4.1.\nmiesiąc\tkostka\torzech\tmiał\n");

        HashMap<String, Integer> dostawy_kostka = new HashMap<>();
        HashMap<String, Integer> dostawy_orzech = new HashMap<>();
        HashMap<String, Integer> dostawy_mial = new HashMap<>();

        for(Dzien d : dane)
        {
            if(dostawy_kostka.containsKey(d.data.substring(5, 7)))
                dostawy_kostka.put(d.data.substring(5, 7), dostawy_kostka.get(d.data.substring(5, 7)) +
                        d.kostka_dostawa);
            else
                dostawy_kostka.put(d.data.substring(5, 7), d.kostka_dostawa);

            if(dostawy_orzech.containsKey(d.data.substring(5, 7)))
                dostawy_orzech.put(d.data.substring(5, 7), dostawy_orzech.get(d.data.substring(5, 7)) +
                        d.orzech_dostawa);
            else
                dostawy_orzech.put(d.data.substring(5, 7), d.orzech_dostawa);

            if(dostawy_mial.containsKey(d.data.substring(5, 7)))
                dostawy_mial.put(d.data.substring(5, 7), dostawy_mial.get(d.data.substring(5, 7)) +
                        d.mial_dostawa);
            else
                dostawy_mial.put(d.data.substring(5, 7), d.mial_dostawa);
        }

        for(String m : dostawy_kostka.keySet())
        {
            wynik4_1.append("%s\t%d\t%d\t%d\n".formatted(m, dostawy_kostka.get(m),
                    dostawy_orzech.get(m), dostawy_mial.get(m)));
        }

        wynik4_1.append("\n");

        //Zadanie 4.2.
        //wykres w pliku "wykres4_2.png"

        //Zadanie 4.3.
        final int cena_kostka = 685;
        final int cena_orzech = 620;
        final int cena_mial = 380;

        int laczna_kwota = 0;

        for(Dzien d : dane)
            laczna_kwota += cena_kostka * d.kostka_dostawa + cena_orzech * d.orzech_dostawa +
                    cena_mial * d.mial_dostawa;

        String wynik4_3 = "4.3.\n%d\n\n".formatted(laczna_kwota);

        //Zadanie 4.4.

        int ile_dni_kostka = 0;
        int ile_dni_orzech = 0;
        int ile_dni_mial = 0;

        //Symulacja zużycia węgla

        for(int i = 0; i < dane.size(); i++)
        {
            Dzien d = dane.get(i);
            if(!Objects.equals(d.data, "2014-10-15"))
            {
                Dzien d_poprz = dane.get(i - 1);
                d.kostka_przed_dostawa = d_poprz.kostka_po_paleniu;
                d.orzech_przed_dostawa = d_poprz.orzech_po_paleniu;
                d.mial_przed_dostawa = d_poprz.mial_po_paleniu;

            }

            d.kostka_po_dostawie = d.kostka_przed_dostawa + d.kostka_dostawa;
            d.orzech_po_dostawie = d.orzech_przed_dostawa + d.orzech_dostawa;
            d.mial_po_dostawie = d.mial_przed_dostawa + d.mial_dostawa;

            d.kostka_po_paleniu = d.kostka_po_dostawie;
            d.orzech_po_paleniu = d.orzech_po_dostawie;
            d.mial_po_paleniu = d.mial_po_dostawie;

            if(d.kostka_po_dostawie >= 200)
            {
                d.kostka_po_paleniu -= 200;
                ile_dni_kostka++;
            }
            else if(d.orzech_po_dostawie >= 260)
            {
                d.orzech_po_paleniu -= 260;
                ile_dni_orzech++;
            }
            else if(d.mial_po_dostawie >= 320)
            {
                d.mial_po_paleniu -= 320;
                ile_dni_mial++;
            }

            /*System.out.println("%d %d %d %d %d %d %d %d %d %d %d %d\n".formatted(d.kostka_przed_dostawa,
                    d.orzech_przed_dostawa,
                    d.kostka_przed_dostawa, d.kostka_dostawa, d.orzech_dostawa, d.mial_dostawa,
                    d.kostka_po_dostawie, d.orzech_po_dostawie, d.mial_po_dostawie, d.kostka_po_paleniu,
                    d.orzech_po_paleniu, d.mial_po_paleniu
                    ));*/
        }

        String wynik4_4 = "4.4.\nkostka: %d\norzech: %d\nmiał: %d\n\n".formatted(
                ile_dni_kostka, ile_dni_orzech, ile_dni_mial
        );

        //Zadanie 4.5.
        StringBuilder wynik4_5 = new StringBuilder("4.5.\n");
        for(Dzien d : dane)
            if(d.mial_po_paleniu < d.mial_po_dostawie)
            {
                wynik4_5.append("%s\n\n".formatted(d.data));
                break;
            }

        //Zadanie 4.6.
        int ile_dni_podtrzymywania = 0;

        for(Dzien d : dane)
            if(d.mial_po_paleniu == d.mial_po_dostawie && d.orzech_po_paleniu == d.orzech_po_dostawie &&
                    d.kostka_po_paleniu == d.kostka_po_dostawie)
            {
                ile_dni_podtrzymywania++;
            }

        String wynik4_6 = "4.6.\n%d\n".formatted(ile_dni_podtrzymywania);

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2015\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
            wynik4.append(wynik4_5);
            wynik4.append(wynik4_6);
        }
    }
}
