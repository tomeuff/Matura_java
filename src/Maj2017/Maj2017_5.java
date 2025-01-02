package Maj2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Maj2017_5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_druzyny = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017\\druzyny.txt"));
        Scanner scanner_sedziowie = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017" +
                "\\sedziowie.txt"));
        Scanner scanner_wyniki = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017" +
                "\\wyniki.txt"));

        HashMap<Integer, Druzyna> druzyny = new HashMap<>();
        HashMap<String, Sedzia> sedziowie = new HashMap<>();
        HashMap<Integer, Wynik> wyniki = new HashMap<>();

        scanner_druzyny.nextLine();
        while(scanner_druzyny.hasNext())
        {
            String[] linia = scanner_druzyny.nextLine().split("\t");
            druzyny.put(Integer.parseInt(linia[0]), new Druzyna(Integer.parseInt(linia[0]),
                    linia[1], linia[2]));
        }

        scanner_sedziowie.nextLine();
        while(scanner_sedziowie.hasNext())
        {
            String[] linia = scanner_sedziowie.nextLine().split("\t");
            sedziowie.put(linia[0], new Sedzia(linia[0], linia[1], linia[2]));
        }

        scanner_wyniki.nextLine();

        int id_wyniku = 1;

        while(scanner_wyniki.hasNext())
        {
            String[] linia = scanner_wyniki.nextLine().split("\t");
            wyniki.put(id_wyniku, new Wynik(linia[0], linia[1], linia[2], Integer.parseInt(linia[3]),
                    linia[4], Integer.parseInt(linia[5]), Integer.parseInt(linia[6])));

            id_wyniku++;
        }

        //Zadanie 5.1.
        int ile_t = 0;
        int ile_l = 0;
        int ile_p = 0;
        HashMap<Integer, Integer> MDSM = new HashMap<>();

        for(Wynik wyn : wyniki.values())
        {
            if(Objects.equals(druzyny.get(wyn.Id_druzyny).Miasto, "Kucykowo"))
            {
                int rok = Integer.parseInt(wyn.Data_meczu.substring(0, 4));
                if(MDSM.containsKey(rok))
                    MDSM.put(rok, MDSM.get(rok) + 1);
                else
                    MDSM.put(rok, 1);

                if(Objects.equals(wyn.Rodzaj_meczu, "T"))
                    ile_t++;
                else if(Objects.equals(wyn.Rodzaj_meczu, "L"))
                    ile_l++;
                else if(Objects.equals(wyn.Rodzaj_meczu, "P"))
                    ile_p++;
            }
        }

        Map.Entry<Integer, Integer> MDSM_max = Collections.max(MDSM.entrySet(), Map.Entry.comparingByValue());

        String wynik5_1 = "5.1.\na)towarzyskie: %d\nligowe: %d\npucharowe: %d\nb)\nrok: %d\nliczba meczów: %d\n\n".
                formatted(ile_t, ile_l, ile_p, MDSM_max.getKey(), MDSM_max.getValue());

        //Zadanie 5.2.
        HashMap<String, Integer> DB = new HashMap<>();

        for(Wynik wyn : wyniki.values())
        {
            String druzyna = druzyny.get(wyn.Id_druzyny).Nazwa;

            if(DB.containsKey(druzyna))
                DB.put(druzyna, DB.get(druzyna) + wyn.Bramki_zdobyte - wyn.Bramki_stracone);
            else
                DB.put(druzyna, wyn.Bramki_zdobyte - wyn.Bramki_stracone);
        }

        String wynik5_2 = "5.2.\n%s\n\n".formatted(DB.entrySet().stream().filter(it -> it.getValue() == 0).
                map(Map.Entry::getKey).collect(Collectors.joining("\n")));

        //Zadanie 5.3.
        int ile_wygr = 0;
        int ile_przegr = 0;
        int ile_zrem = 0;

        for(Wynik wyn : wyniki.values())
        {
            if(Objects.equals(wyn.Gdzie, "W"))
            {
                if(wyn.Bramki_zdobyte > wyn.Bramki_stracone)
                    ile_wygr++;
                else if(wyn.Bramki_zdobyte < wyn.Bramki_stracone)
                    ile_przegr++;
                else
                    ile_zrem++;
            }
        }

        String wynik5_3 = "5.3.\nwygrane: %d\nprzegrane: %d\nzremisowane: %d\n\n".formatted(
                ile_wygr, ile_przegr, ile_zrem
        );

        //Zadanie 5.4.
        int ilu_sedziow = 0;

        for(Sedzia sed : sedziowie.values())
        {
            boolean czy_byl = false;
            for(Wynik wyn : wyniki.values())
            {
                if(Objects.equals(wyn.Nr_licencji, sed.Nr_licencji) && Objects.equals(wyn.Rodzaj_meczu, "P"))
                {
                    czy_byl = true;
                    break;
                }
            }
            if(!czy_byl)
                ilu_sedziow++;
        }

        String wynik5_4 = "5.4.\n%d\n".formatted(ilu_sedziow);

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
            wynik5.append(wynik5_4);
        }
    }
}
