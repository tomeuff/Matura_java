package Czerwiec2017_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Czerwiec2017_4 {
    public static boolean czy_pierwsza(Integer liczba)
    {
        if(liczba == 1)
            return false;
        for(int d = 2; d <= Math.sqrt(liczba); d++)
            if(liczba % d == 0)
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2017_4\\punkty" +
                ".txt"));
        List<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextLine());

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\n%d\n\n".formatted(dane.stream().filter(it -> czy_pierwsza(Integer.parseInt(it.split(
                " ")[0])) && czy_pierwsza(Integer.parseInt(it.split(" ")[1]))).count());

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%d\n\n".formatted(dane.stream().filter(it ->
                it.split(" ")[0].chars().mapToObj(c -> (char) c).collect(Collectors.toSet()).equals(
                        it.split(" ")[1].chars().mapToObj(c -> (char) c).collect(Collectors.toSet()))).count());

        //Zadanie 4.3.
        double max_odl = 0;
        int max_xi = 0, max_xj = 0, max_yi = 0, max_yj = 0;
        for(int i = 0; i < dane.size(); i++)
        {
            for(int j = i + 1; j < dane.size(); j++)
            {
                int xi = Integer.parseInt(dane.get(i).split(" ")[0]);
                int yi = Integer.parseInt(dane.get(i).split(" ")[1]);
                int xj = Integer.parseInt(dane.get(j).split(" ")[0]);
                int yj = Integer.parseInt(dane.get(j).split(" ")[1]);

                double odl = Math.sqrt((xj - xi) * (xj - xi) + (yj - yi) * (yj - yi));

                if(odl > max_odl)
                {
                    max_odl = odl;
                    max_xi = xi;
                    max_xj = xj;
                    max_yi = yi;
                    max_yj = yj;
                }
            }
        }
        String wynik4_3 = "4.3.\n(%d, %d)\n(%d, %d)\n%d\n\n".formatted(max_xi, max_yi, max_xj, max_yj, (int) max_odl);

        //Zadanie 4.4.
        int ile_wew = 0;
        int ile_brzeg = 0;

        for(int i = 0; i < dane.size(); i++)
        {
            int x = Integer.parseInt(dane.get(i).split(" ")[0]);
            int y = Integer.parseInt(dane.get(i).split(" ")[1]);

            if(x > -5000 && x < 5000 && y > -5000 && y < 5000)
                ile_wew++;

            if((x > -5000 && x < 5000 && (y == -5000 || y == 5000)) ||
                    (y > -5000 && y < 5000 && (x == -5000 || x == 5000)))
            {
                ile_brzeg++;
            }
        }

        String wynik4_4 = "4.4.\n%d\n%d\n%d".formatted(ile_wew, ile_brzeg, dane.size() - ile_wew - ile_brzeg);

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2017_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
