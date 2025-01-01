package Czerwiec2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Czerwiec2016_4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2016\\ubezpieczenia.txt"));
        ArrayList<Osoba> dane = new ArrayList<>();

        scanner.nextLine();

        while(scanner.hasNext())
        {
            String[] linia = scanner.nextLine().split(";");
            dane.add(new Osoba(linia[0], linia[1], linia[2], linia[3]));
        }

        //Zadanie 4.1.
        HashMap<String, Integer> UM = new HashMap<>();

        for(Osoba o : dane)
        {
            String mies = o.Data_urodz.substring(5, 7);
            if(UM.containsKey(mies))
                UM.put(mies, UM.get(mies) + 1);
            else
                UM.put(mies, 1);
        }

        StringBuilder wynik4_1 = new StringBuilder("4.1.\n");

        for(String mies : UM.keySet())
            wynik4_1.append("%s %d\n".formatted(mies, UM.get(mies)));

        //Zadanie 4.2.
        HashMap<String, Integer> ZK = new HashMap<>();

        for(Osoba o : dane)
        {
            if(o.Imie.endsWith("a"))
            {
                String m_zam = o.Miejsce_zamieszkania;
                if(ZK.containsKey(m_zam))
                    ZK.put(m_zam, ZK.get(m_zam) + 1);
                else
                    ZK.put(m_zam, 1);
            }
        }

        StringBuilder wynik4_2 = new StringBuilder("4.2.\n");

        for(String m_zam : ZK.keySet())
            wynik4_2.append("%s %d\n".formatted(m_zam, ZK.get(m_zam)));

        wynik4_2.append("\n");

        //Zadanie 4.3.
        double suma_k = 0;
        double suma_m = 0;
        final int k_ubez_k = 25000;
        final int k_ubez_m = 30000;

        for(Osoba o : dane)
        {
            int wiek = 2016 - Integer.parseInt(o.Data_urodz.substring(0, 4));
            double procent = 0;

            if(wiek <= 30)
                procent = 0.001;
            else if(wiek >= 31 && wiek <= 45)
                procent = 0.0015;
            else
                procent = 0.0012;

            double dodatek = 0;

            if(wiek > 60)
                dodatek = 49;

            if(o.Imie.endsWith("a"))
                suma_k += k_ubez_k * procent + dodatek;
            else
                suma_m += k_ubez_m * procent + dodatek;

        }

        String wynik4_3 = "4.3.\nkobiety: %.2f\nmężczyźni: %.2f\n\n".formatted(suma_k, suma_m);

        //Zadanie 4.4.
        HashMap<String, Integer> PU = new HashMap<>();

        for(Osoba o : dane)
        {
            int wiek = 2016 - Integer.parseInt(o.Data_urodz.substring(0, 4));
            String przedzial = "%d-%d".formatted(wiek / 10 * 10, wiek / 10 * 10 + 9);

            if(PU.containsKey(przedzial))
                PU.put(przedzial, PU.get(przedzial) + 1);
            else
                PU.put(przedzial, 1);
        }

        StringBuilder wynik4_4 = new StringBuilder("4.4.\nprzedział\tliczba_osób\n");

        for(String przed : PU.keySet())
            wynik4_4.append("%s\t%d\n".formatted(przed, PU.get(przed)));

        wynik4_4.append("\n");

        System.out.println(wynik4_4);
    }
}
