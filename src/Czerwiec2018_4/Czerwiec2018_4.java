package Czerwiec2018_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Czerwiec2018_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner1 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2018_4\\dane1.txt"));
        Scanner scanner2 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2018_4\\dane2" +
                ".txt"));
        List<List<Integer>> dane1 = new ArrayList<>();
        List<List<Integer>> dane2 = new ArrayList<>();

        while(scanner1.hasNext())
            dane1.add(Arrays.stream(scanner1.nextLine().split(" ")).map(Integer::parseInt).toList());

        while(scanner2.hasNext())
            dane2.add(Arrays.stream(scanner2.nextLine().split(" ")).map(Integer::parseInt).toList());

        //Zadanie 4.1.
        int ile_4_1 = 0;

        for(int i = 0; i < dane1.size(); i++)
        {
            if(dane1.get(i).get(9) == dane2.get(i).get(9))
                ile_4_1++;
        }

        String wynik4_1 = "4.1.\n%d\n\n".formatted(ile_4_1);

        //Zadanie 4.2.
        int ile_p_np = 0;

        for(int i = 0; i < dane1.size(); i++)
        {
            if(dane1.get(i).stream().filter(it -> it % 2 == 0).count() == 5 &&
                    dane2.get(i).stream().filter(it -> it % 2 == 0).count() == 5)
                ile_p_np++;
        }

        String wynik4_2 = "4.2.\n%d\n\n".formatted(ile_p_np);

        //Zadanie 4.3.
        StringBuilder pary = new StringBuilder();
        for(int i = 0; i < dane1.size(); i++)
        {
            if(new HashSet<>(dane1.get(i)).equals(new HashSet<>(dane2.get(i))))
            {
                pary.append("%d\n".formatted(i + 1));
            }
        }

        String wynik4_3 = "4.3.\n%s\n\n".formatted(pary);

        //Zadanie 4.4.
        StringBuilder wynik4_4 = new StringBuilder();

        for(int i = 0; i < dane1.size(); i++)
        {
           List<Integer> wynik = new ArrayList<>();

           int ind1 = 0, ind2 = 0;

           while(ind1 < 10 || ind2 < 10)
           {
               if(ind1 < 10 && ind2 < 10)
               {
                   if(dane1.get(i).get(ind1) < dane2.get(i).get(ind2))
                   {
                       wynik.add(dane1.get(i).get(ind1));
                       ind1++;
                   }
                   else
                   {
                       wynik.add(dane2.get(i).get(ind2));
                       ind2++;
                   }
               }
               else if(ind1 < 10)
               {
                   wynik.add(dane1.get(i).get(ind1));
                   ind1++;
               }
               else
               {
                   wynik.add(dane2.get(i).get(ind2));
                   ind2++;
               }
           }

           wynik4_4.append("%s\n".formatted(wynik.stream().map(Object::toString).collect(Collectors.joining(" "))));
        }

        try (FileWriter wyniki4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2018_4\\wyniki4" +
                ".txt")) {
            wyniki4.write("");
            wyniki4.append(wynik4_1);
            wyniki4.append(wynik4_2);
            wyniki4.append(wynik4_3);
        }

        try (FileWriter wyniki4_4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2018_4" +
                "\\wynik4_4" + ".txt")) {
            wyniki4_4.write("");
            wyniki4_4.append(wynik4_4);
        }
    }
}
