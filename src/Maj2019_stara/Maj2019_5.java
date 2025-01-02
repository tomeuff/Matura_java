package Maj2019_stara;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Maj2019_5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_filmy = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara\\filmy.txt"));
        Scanner scanner_aktorzy = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara" +
                "\\aktorzy.txt"));
        Scanner scanner_nagrody = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara" +
                "\\nagrody.txt"));

        HashMap<String, Film> filmy = new HashMap<>();
        HashMap<String, Aktor> aktorzy = new HashMap<>();
        HashMap<Integer, Nagroda> nagrody = new HashMap<>();

        scanner_filmy.nextLine();
        while(scanner_filmy.hasNext())
        {
            String[] linia = scanner_filmy.nextLine().split("\t");
            filmy.put(linia[0], new Film(linia[0], Integer.parseInt(linia[1]), linia[2]));
        }

        scanner_aktorzy.nextLine();
        while(scanner_aktorzy.hasNext())
        {
            String[] linia = scanner_aktorzy.nextLine().split("\t");
            aktorzy.put(linia[0], new Aktor(linia[0], linia[1], linia[2], linia[3], linia[4]));
        }

        scanner_nagrody.nextLine();
        int id_filmu = 1;
        while(scanner_nagrody.hasNext())
        {
            String[] linia = scanner_nagrody.nextLine().split("\t");
            nagrody.put(id_filmu, new Nagroda(id_filmu, linia[0], linia[1], linia[2]));
            id_filmu++;
        }

        //Zadanie 5.1.
        HashSet<String> KA = new HashSet<>();

        HashMap<String, Integer> KO = new HashMap<>();

        for(Nagroda nagr : nagrody.values())
        {
            String kraj = aktorzy.get(nagr.id_aktora).kraj_urodzenia;
            String id_aktora = nagr.id_aktora;
            KA.add("%s\t%s".formatted(kraj, id_aktora));
        }

        for(String ka : KA)
        {
            String kraj = ka.split("\t")[0];
            if(KO.containsKey(kraj))
                KO.put(kraj, KO.get(kraj) + 1);
            else
                KO.put(kraj, 1);
        }

        ArrayList<Map.Entry<String, Integer>> KO_al = new ArrayList<>(KO.entrySet());
        KO_al.sort(Comparator.comparing(Map.Entry::getValue));

        String wynik5_1 = "5.1.\n%s\n\n".formatted(KO_al.stream().map(it -> "%s %d".formatted(it.getKey(),
                it.getValue())).collect(Collectors.joining("\n")));

        //Zadanie 5.2.
        String min_aktor = "";
        int min_wiek = 1000000;

        for(Nagroda nagr : nagrody.values())
        {
            Aktor aktor = aktorzy.get(nagr.id_aktora);
            int rok_nagr = filmy.get(nagr.id_filmu).rok;
            int wiek = rok_nagr - Integer.parseInt(aktor.data_ur.substring(0, 4));
            if(wiek < min_wiek)
            {
                min_wiek = wiek;
                min_aktor = "%s %s".formatted(aktor.imie, aktor.nazwisko);
            }
        }

        String wynik5_2 = "5.2.\n%s %d\n\n".formatted(min_aktor, min_wiek);

        //Zadanie 5.3.
        StringBuilder wynik5_3 = new StringBuilder("5.3.\n");
        HashMap<String, Integer> ALO = new HashMap<>();

        for(Nagroda nagr : nagrody.values())
        {
            String id_aktora = nagr.id_aktora;;
            if(ALO.containsKey(id_aktora))
                ALO.put(id_aktora, ALO.get(id_aktora) + 1);
            else
                ALO.put(id_aktora, 1);
        }

        Map.Entry<String, Integer> ALO_max = Collections.max(ALO.entrySet(), Map.Entry.comparingByValue());
        wynik5_3.append("%s %s\n".formatted(aktorzy.get(ALO_max.getKey()).imie,
                aktorzy.get(ALO_max.getKey()).nazwisko));
        for(Nagroda nagr : nagrody.values())
        {
            String id_aktora = nagr.id_aktora;;
            if(Objects.equals(id_aktora, ALO_max.getKey()))
            {
                wynik5_3.append("%d %s\n".formatted(filmy.get(nagr.id_filmu).rok, filmy.get(nagr.id_filmu).tytul));
            }
        }

        wynik5_3.append("\n");

        //Zadanie 5.4.
        StringBuilder wynik5_4 = new StringBuilder("5.4.\n");
        HashMap<String, AktorNagrody> AN = new HashMap<>();

        for(Nagroda nagr : nagrody.values())
        {
            String id_aktora = nagr.id_aktora;
            if(!AN.containsKey(id_aktora))
            {
                Aktor aktor = aktorzy.get(id_aktora);
                AN.put(id_aktora, new AktorNagrody(aktor, new ArrayList<>(), new ArrayList<>()));
            }
            AktorNagrody an = AN.get(id_aktora);
            if(nagr.kategoria.endsWith("pierwszoplanowy") || nagr.kategoria.endsWith("pierwszoplanowa"))
                an.pierwszoplanowe.add(filmy.get(nagr.id_filmu));
            else if(nagr.kategoria.endsWith("drugoplanowy") || nagr.kategoria.endsWith("drugoplanowa"))
                an.drugoplanowe.add(filmy.get(nagr.id_filmu));
        }

        for(AktorNagrody an : AN.values())
        {
            if(an.pierwszoplanowe.size() > 0 && an.drugoplanowe.size() > 0)
            {
                int max_rok_pierwsz = Collections.max(an.pierwszoplanowe,
                        Comparator.comparingInt(a -> ((Film) a).rok)).rok;
                int max_rok_drugo = Collections.max(an.drugoplanowe,
                        Comparator.comparingInt(a -> ((Film) a).rok)).rok;
                int min_rok_pierwsz = Collections.min(an.pierwszoplanowe,
                        Comparator.comparingInt(a -> ((Film) a).rok)).rok;
                int min_rok_drugo = Collections.min(an.drugoplanowe,
                        Comparator.comparingInt(a -> ((Film) a).rok)).rok;

                wynik5_4.append("%s %s %d\n".formatted(an.aktor.imie, an.aktor.nazwisko,
                        Math.max(Math.abs(max_rok_pierwsz - min_rok_drugo), Math.abs(max_rok_drugo - min_rok_pierwsz))));
            }
        }
        wynik5_4.append("\n");

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2019_stara\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
            wynik5.append(wynik5_4);
        }
    }
}
