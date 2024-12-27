package Maj2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maj2024_3 {
    public static int NWD(int a, int b)
    {
        while(b != 0)
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static int nieparzysty_skrot(int n)
    {
        int m = 0;
        int pot10 = 1;
        while(n > 0)
        {
            int c = n % 10;
            if(c % 2 == 1)
            {
                m = m + c * pot10;
                pot10 *= 10;
            }
            n /= 10;
        }

        return m;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner1 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2024\\skrot.txt"));
        ArrayList<Integer> dane1 = new ArrayList<>();

        while(scanner1.hasNext())
                dane1.add(scanner1.nextInt());

        Scanner scanner2 = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2024\\skrot2.txt"));
        ArrayList<Integer> dane2 = new ArrayList<>();

        while(scanner2.hasNext())
            dane2.add(scanner2.nextInt());

        //Zadanie 3.2.
        int max_np_skr = 0;
        int ile_np_skr = 0;

        for(Integer liczba : dane1)
            if(nieparzysty_skrot(liczba) == 0)
            {
                ile_np_skr++;
                if(liczba > max_np_skr)
                    max_np_skr = liczba;
            }
        String wynik3_2 = "3.2.\n%d %d\n\n".formatted(ile_np_skr, max_np_skr);

        //Zadanie 3.3.
        StringBuilder wynik3_3 = new StringBuilder("3.3.\n");
        for(Integer liczba : dane2)
        {
            int np_skr = nieparzysty_skrot(liczba);
            if(NWD(liczba, np_skr) == 7)
                wynik3_3.append("%d\n".formatted(liczba));
        }
        wynik3_3.append("\n");

        try (FileWriter wynik3 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2024\\wyniki3.txt")) {
            wynik3.write("");
            wynik3.append(wynik3_2);
            wynik3.append(wynik3_3);
        }
    }
}
