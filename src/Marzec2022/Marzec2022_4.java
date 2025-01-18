package Marzec2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Marzec2022_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\brenna" +
                ".txt"));

        ArrayList<Pomiar> dane = new ArrayList<>();

        scanner.nextLine();

        while(scanner.hasNext())
        {
            String[] linia = scanner.nextLine().split("\t");

            dane.add(new Pomiar(linia[0], Float.parseFloat(linia[1].replace(',', '.')),
                    Float.parseFloat(linia[2].replace(',', '.'))));
        }

        //Zadanie 4.1.
        HashMap<String, Para<Float, Float>> data_amplituda = new HashMap<>();

        for(Pomiar p: dane)
        {
            String data = p.data.substring(0, 10);
            Float temperatura = p.temperatura;

            if(data_amplituda.containsKey(data))
            {
                Para<Float, Float> para = data_amplituda.get(data);

                if(temperatura > para.first)
                {
                    para.first = temperatura;
                }

                if(temperatura < para.second)
                {
                    para.second = temperatura;
                }
            }
            else
            {
                data_amplituda.put(data, new Para<Float, Float>(temperatura, temperatura));
            }
        }

        Map.Entry<String, Para<Float, Float>> max_para = Collections.max(data_amplituda.entrySet(),
                Comparator.comparing(
                it -> it.getValue().first - it.getValue().second
        ));

        String wynik4_1 = "4.1.\n%s %f\n\n".formatted(max_para.getKey(), max_para.getValue().first -
                max_para.getValue().second);

        //Zadanie 4.2.
        HashMap<String, Para<Float, Integer>> godzina_srednia_temperatura = new HashMap<>();

        for(Pomiar p: dane)
        {
            String godzina = p.data.substring(11);
            if(godzina_srednia_temperatura.containsKey(godzina))
            {
                Para<Float, Integer> para = godzina_srednia_temperatura.get(godzina);
                para.first += p.temperatura;
                para.second += 1;
            }
            else
            {
                godzina_srednia_temperatura.put(godzina, new Para<Float, Integer>(p.temperatura, 1));
            }
        }

        String wynik4_2 = "4.2.\n%s\n\n".formatted(godzina_srednia_temperatura.entrySet().stream().map(
                it -> "%s %.2f".formatted(it.getKey(), it.getValue().first / it.getValue().second)
        ).sorted().collect(Collectors.joining("\n")));

        //Zadanie 4.3.
        ArrayList<Para<String, Boolean>> data_godzina_deszcz = new ArrayList<>();

        for(Pomiar p: dane)
        {
            data_godzina_deszcz.add(new Para<String, Boolean>(p.data, p.temperatura > 0 && p.opad > 0));
        }

        int p = 0, k = 0, p_max = 0, k_max = 0;
        float suma_max = 0, suma = 0;

        for(int i = 1; i < data_godzina_deszcz.size(); i++)
        {
            if(data_godzina_deszcz.get(i).second)
            {
                k++;
                suma += dane.get(i).opad;

                if(k - p + 1 > k_max - p_max + 1)
                {
                    k_max = k;
                    p_max = p;
                    suma_max = suma;
                }
            }
            else
            {
                suma = 0;
                k = i;
                p = i + 1;
            }
        }

        String wynik4_3 = "4.3.\n%d\n%s\n%s\n%f\n\n".formatted(k_max - p_max + 1, data_godzina_deszcz.get(p_max).first,
                data_godzina_deszcz.get(k_max).first, suma_max);

        //Zadanie 4.4.
        ArrayList<Para<String, Boolean>> data_godzina_snieg = new ArrayList<>();

        HashMap<String, Integer> data_plugi = new HashMap<>();

        int ile_plugi = 0;

        for(Pomiar pomiar: dane)
        {
            data_godzina_snieg.add(new Para<String, Boolean>(pomiar.data, pomiar.temperatura <= 0 && pomiar.opad > 0));
        }

        float suma_snieg = data_godzina_snieg.get(0).second ? dane.get(0).opad : 0;

        for(int i = 1; i < dane.size(); i++)
        {
            if(suma_snieg > 4)
            {
                ile_plugi++;
                suma_snieg = 0;

                String dzien = dane.get(i).data.substring(0, 10);

                if(data_plugi.containsKey(dzien))
                {
                    data_plugi.put(dzien, data_plugi.get(dzien) + 1);
                }
                else
                {
                    data_plugi.put(dzien, 1);
                }
            }
            else
            {
                if(data_godzina_snieg.get(i).second)
                {
                    suma_snieg += dane.get(i).opad;
                }
                else if(data_godzina_deszcz.get(i).second)
                {
                    suma_snieg = 0;
                }
            }
        }

        Map.Entry<String, Integer> max_data_plugi = Collections.max(data_plugi.entrySet(),
                Map.Entry.comparingByValue());

        String wynik4_4 = "4.4.\na) %d\nb) %s %d\n".formatted(ile_plugi, max_data_plugi.getKey(), max_data_plugi.getValue());

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
