package Grudzien2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grudzien2024_4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Grudzien2024\\prostokaty.txt"));
        ArrayList<Prostokat> dane = new ArrayList<>();

        while(scanner.hasNext())
        {
            String wiersz = scanner.nextLine();
            dane.add(new Prostokat(Integer.parseInt(wiersz.split(" ")[0]),
                    Integer.parseInt(wiersz.split(" ")[1])));
        }

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\nnajmniejsze: %d\nnajwiększe: %d\n\n".formatted(
                dane.stream().mapToInt(it -> it.h * it.s).min().getAsInt(),
                dane.stream().mapToInt(it -> it.h * it.s).max().getAsInt());
        System.out.println(wynik4_1);

        //Zadanie 4.2.
        int p = 0, k = 0, p_max = 0, k_max = 0;

        for(int i = 1; i < dane.size(); i++)
        {
            if(dane.get(i).h <= dane.get(i - 1).h && dane.get(i).s <= dane.get(i - 1).s)
            {
                k++;
                if(k - p + 1 > k_max - p_max + 1)
                {
                    k_max = k;
                    p_max = p;
                }
            }
            else
            {
                k = i;
                p = i;
            }
        }

        String wynik4_2 = "4.2.\n%d %d % d\n\n".formatted(k_max - p_max + 1, dane.get(k_max).h, dane.get(k_max).s);

        //Zadanie 4.3.
        int max_2 = 0, max_3 = 0, max_5 = 0;

        for(int i = 0; i < dane.size(); i++)
        {
            int s = dane.get(i).s;
            for(int j = i + 1; j < dane.size(); j++)
            {
                if(dane.get(i).h == dane.get(j).h)
                {
                    s = dane.get(i).s + dane.get(j).s;
                    if(s > max_2)
                        max_2 = s;
                    for(int l = j + 1; l < dane.size(); l++)
                    {
                        if(dane.get(j).h == dane.get(l).h)
                        {
                            s = dane.get(i).s + dane.get(j).s + dane.get(l).s;
                            if(s > max_3)
                                max_3 = s;
                            for(int m = l + 1; m < dane.size(); m++)
                            {
                                if(dane.get(l).h == dane.get(m).h)
                                {
                                    s = dane.get(i).s + dane.get(j).s + dane.get(l).s + dane.get(m).s;
                                    for(int n = m + 1; n < dane.size(); n++)
                                    {
                                        if(dane.get(m).h == dane.get(n).h)
                                        {
                                            s = dane.get(i).s + dane.get(j).s + dane.get(l).s + dane.get(m).s + dane.get(n).s;
                                            if(s > max_5)
                                                max_5 = s;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String wynik4_3 = "4.3.\nmax 2: %d\nmax 3: %d\nmax 5: %d\n\n".formatted(max_2, max_3, max_5);

        System.out.println(wynik4_3);
    }
}
