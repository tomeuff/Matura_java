package Maj2021_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Maj2021_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2021_4\\instrukcje.txt"));
        ArrayList<Wiersz> dane = new ArrayList<>();

        while(scanner.hasNext())
        {
            String wiersz = scanner.nextLine();
            String instrukcja = wiersz.split(" ")[0];
            Character parametr = wiersz.split(" ")[1].toCharArray()[0];
            dane.add(new Wiersz(instrukcja, parametr));
        }

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\n%d\n\n".formatted(dane.stream().mapToInt(it ->
        {
            if(Objects.equals(it.instrukcja, "DOPISZ"))
                return 1;
            else if(Objects.equals(it.instrukcja, "USUN"))
                return -1;
            else return 0;
        }).sum());

        //Zadanie 4.2.
        int p = 0, k = 0, p_max = 0, k_max = 0;

        for(int i = 1; i < dane.size(); i++)
        {
            if(Objects.equals(dane.get(i).instrukcja, dane.get(i - 1).instrukcja))
            {
                k++;
                if(k - p + 1 > k_max - p_max + 1)
                {
                    k_max= k;
                    p_max = p;
                }
            }
            else
            {
                p = i;
                k = i;
            }
        }
        String wynik4_2 = "4.2.\n%s %d\n\n".formatted(dane.get(p_max).instrukcja, k_max - p_max + 1);

        //Zadanie 4.3.
        HashMap<Character, Integer> hmDopisz = new HashMap<>();

        for(Wiersz w : dane)
        {
            if(Objects.equals(w.instrukcja, "DOPISZ"))
            {
                if(hmDopisz.containsKey(w.parametr))
                    hmDopisz.replace(w.parametr, hmDopisz.get(w.parametr) + 1);
                else hmDopisz.put(w.parametr, 1);
            }
        }
        Map.Entry<Character, Integer> maxPara = Collections.max(hmDopisz.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue));

        String wynik4_3 = "4.3.\n%c %d\n\n".formatted(maxPara.getKey(), maxPara.getValue());

        //Zadanie 4.4.
        StringBuilder wynik4_4 = new StringBuilder("4.4.\n");

        for(Wiersz w : dane)
        {
            if(w.instrukcja.equals("DOPISZ"))
                wynik4_4.append(w.parametr);
            else if(w.instrukcja.equals("USUN"))
                wynik4_4.deleteCharAt(wynik4_4.length() - 1);
            else if(w.instrukcja.equals("ZMIEN"))
            {
                wynik4_4.deleteCharAt(wynik4_4.length() - 1);
                wynik4_4.append(w.parametr);
            }
            else
            {
                int gdzie = wynik4_4.indexOf(w.parametr.toString());

                if(gdzie >= 0)
                {
                    if(w.parametr + 1 <= 90)
                    {
                        char nz = (char) (w.parametr + 1);
                        wynik4_4.replace(gdzie, gdzie + 1, Character.toString(nz));
                    }
                    else
                    {
                        char nz = (char) (w.parametr + 1 - 26);
                        wynik4_4.replace(gdzie, gdzie + 1, Character.toString(nz));
                    }
                }
            }
        }

        try (FileWriter wynik4 =
                     new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2021_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
