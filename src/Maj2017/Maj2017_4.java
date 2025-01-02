package Maj2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Maj2017_4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner_cukier = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017\\cukier.txt"));
        Scanner scanner_cennik = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2017\\cennik" +
                ".txt"));
        ArrayList<Transakcja> transakcje = new ArrayList<>();
        ArrayList<Magazyn> magazyn = new ArrayList<>();

        while(scanner_cukier.hasNext())
        {
            String[] linia = scanner_cukier.nextLine().split("\t");
            transakcje.add(new Transakcja(linia[0], linia[1], Integer.parseInt(linia[2])));
            magazyn.add(new Magazyn(linia[0], 0, 0, 0));
        }

        HashMap<Integer, Double> cennik = new HashMap<>();

        while(scanner_cennik.hasNext())
            cennik.put(scanner_cennik.nextInt(), scanner_cennik.nextDouble());

        //Zadanie 4.1.
        HashMap<String, Integer> KKG = new HashMap<>();

        for(Transakcja t : transakcje)
        {
            if(KKG.containsKey(t.nip))
                KKG.put(t.nip, KKG.get(t.nip) + t.kg);
            else
                KKG.put(t.nip, t.kg);
        }

        ArrayList<Map.Entry<String, Integer>> KKG_sort = new ArrayList<>(KKG.entrySet());

        KKG_sort.sort(Map.Entry.comparingByValue());

        StringBuilder wynik4_1 = new StringBuilder("4.1.\n");

        for(int i = 0; i < 3; i++)
            wynik4_1.append("%s %d\n".formatted(KKG_sort.get(KKG_sort.size() - 1 - i).getKey(),
                    KKG_sort.get(KKG_sort.size() - 1 - i).getValue()));
        wynik4_1.append("\n");

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%.2f\n\n".formatted(transakcje.stream().mapToDouble(it ->
                        it.kg * cennik.get(Integer.parseInt(it.data.substring(0, 4)))).sum());

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");

        HashMap<Integer, Integer> RKG = new HashMap<>();

        for(Transakcja t : transakcje)
        {
            int rok = Integer.parseInt(t.data.substring(0, 4));

            if(RKG.containsKey(rok))
                RKG.put(rok, RKG.get(rok) + t.kg);
            else
                RKG.put(rok, t.kg);
        }

        for(Integer r : RKG.keySet())
            wynik4_3.append("%d\t%d\n".formatted(r, RKG.get(r)));

        wynik4_3.append("\n");

        //Zadanie 4.4.
        double suma_rabatow = 0;
        HashMap<String, Integer> KCDP = new HashMap<>();

        for(Transakcja t : transakcje)
        {
            String klient = t.nip;

            if(KCDP.containsKey(klient))
                KCDP.put(klient, KCDP.get(klient) + t.kg);
            else
                KCDP.put(klient, t.kg);

            if(KCDP.get(klient) >= 100 && KCDP.get(klient) < 1000)
                suma_rabatow += 0.05 * t.kg;
            else if(KCDP.get(klient) >= 1000 && KCDP.get(klient) < 10000)
                suma_rabatow += 0.1 * t.kg;
            else if(KCDP.get(klient) >= 10000)
                suma_rabatow += 0.2 * t.kg;
        }

        String wynik4_4 = "4.4.\n%.2f\n\n".formatted(suma_rabatow);

        //Zadanie 4.5.
        int ile_razy_wlr_4000 = 0;

        magazyn.set(0, new Magazyn(magazyn.get(0).data, 5000, 0, 0));

        for(int i = 0; i < transakcje.size(); i++)
        {
            Magazyn m = magazyn.get(i);
            Transakcja t = transakcje.get(i);

            if(i > 0)
                m.stan_przed_transakcja = magazyn.get(i - 1).stan_po_uzupelnieniu;

            m.stan_po_transakcji = m.stan_przed_transakcja - transakcje.get(i).kg;

            if(i < transakcje.size() - 1)
            {
                if(!t.data.substring(5, 7).equals(transakcje.get(i + 1).data.substring(5, 7)))
                {
                    int ile_palet = (int) Math.ceil((5000.0 - (double) m.stan_po_transakcji) / 1000.0);
                    m.stan_po_uzupelnieniu = m.stan_po_transakcji + ile_palet * 1000;

                    if(ile_palet >= 4)
                        ile_razy_wlr_4000++;
                }
                else
                    m.stan_po_uzupelnieniu = m.stan_po_transakcji;
            }
            else
            {
                int ile_palet = (int) Math.ceil((5000.0 - (double) m.stan_po_transakcji) / 1000.0);
                m.stan_po_uzupelnieniu = m.stan_po_transakcji + ile_palet * 1000;

                if(ile_palet >= 4)
                    ile_razy_wlr_4000++;
            }
        }

        String wynik4_5 = "4.5.\n%d\n\n".formatted(ile_razy_wlr_4000);

        System.out.println(wynik4_5);
    }
}
