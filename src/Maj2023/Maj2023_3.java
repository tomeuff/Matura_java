package Maj2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Maj2023_3 {
    public static boolean czy_rosnie(int p, int k, ArrayList<Integer> dane)
    {
        if(k - p + 1 < 2)
            return false;
        for(int i = p + 1; i <= k; i++)
            if(dane.get(i) <= dane.get(i - 1))
                return false;
        return true;
    }

    public static boolean czy_maleje(int p, int k, ArrayList<Integer> dane)
    {
        if(k - p + 1 < 2)
            return false;
        for(int i = p + 1; i <= k; i++)
            if(dane.get(i) >= dane.get(i - 1))
                return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2023\\pi.txt"));
        ArrayList<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        //Zadanie 3.1.
        int ile_ponad_90 = 0;
        for(int i = 1; i < dane.size(); i++)
        {
            int fragment = Integer.parseInt("%d%d".formatted(dane.get(i - 1), dane.get(i)));
            if(fragment > 90)
                ile_ponad_90++;
        }
        String wynik3_1 = "3.1.\n%d\n\n".formatted(ile_ponad_90);

        //Zadanie 3.2.
        HashMap<String, Integer> hm = new HashMap<>();
        for(int i = 1; i < dane.size(); i++)
        {
            String fragment = "%d%d".formatted(dane.get(i - 1), dane.get(i));

            if(hm.containsKey(fragment))
                hm.put(fragment, hm.get(fragment) + 1);
            else
                hm.put(fragment, 1);
        }

        Map.Entry<String, Integer> max_fr = Collections.max(hm.entrySet(), Map.Entry.comparingByValue());
        Map.Entry<String, Integer> min_fr = Collections.min(hm.entrySet(), Map.Entry.comparingByValue());

        String wynik3_2 = "3.2.\n%s %d\n%s %d\n\n".formatted(max_fr.getKey(), max_fr.getValue(),
                min_fr.getKey(), min_fr.getValue());

        //Zadanie 3.3.
        int ile_r_m_6 = 0;
        for(int p = 0; p < dane.size() - 5; p++)
        {
            for(int podzial = p; podzial <= p + 5; podzial++)
            {
                if(czy_rosnie(p, podzial, dane) && czy_maleje(podzial + 1, p + 5, dane))
                {
                    ile_r_m_6++;
                    break;
                }
            }
        }
        String wynik3_3 = "3.3.\n%d\n\n".formatted(ile_r_m_6);

        //Zadanie 3.4.
        int max_p = 0;
        int max_k = 0;

        for(int p = 0; p < dane.size(); p++)
        {
            for(int k = p; k < dane.size(); k++)
            {
                for(int podzial = p; podzial <= k; podzial++)
                {
                    if(czy_rosnie(p, podzial, dane) && czy_maleje(podzial + 1, k, dane))
                    {
                        if(k - p + 1 > max_k - max_p + 1)
                        {
                            max_k = k;
                            max_p = p;
                        }
                        break;
                    }
                }
            }
        }

        String wynik3_4 = "3.4.\n%d\n%s\n".formatted(max_p + 1,
                dane.subList(max_p, max_k + 1).stream().map(it -> Integer.toString(it)).collect(Collectors.joining("")));

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2023\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_1);
            wynik3.append(wynik3_2);
            wynik3.append(wynik3_3);
            wynik3.append(wynik3_4);
        }
    }
}
