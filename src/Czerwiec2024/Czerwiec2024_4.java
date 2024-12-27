package Czerwiec2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Czerwiec2024_4 {
    public static void main(String[] args) throws IOException {
        //Zadanie 4.1.
        /*int p = 6;
        int r = 4;
        int[][] T = new int[r][p];

        T[0] = new int[]{3, 1, 6, 5, 4, 5};

        for(int i = 1; i < r; i++)
            for(int j = 0; j < p; j++)
                T[i][j] = T[0][T[i - 1][j] - 1];

        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < p; j++)
            {
                System.out.print(T[i][j] + "\t");
            }
            System.out.println();
        }*/

        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2024\\odbiorcy.txt"));
        ArrayList<Integer> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextInt());

        int n = dane.size();

        //Zadanie 4.2.
        String wynik4_2 = "4.2.\n%d\n\n".formatted(n - new HashSet<>(dane).size());

        //Zadanie 4.3.
        int p = n;
        int r = 1000;
        int[][] T = new int[r][p];
        boolean czy_stop = false;
        int min_r = 0;
        int min_p = 0;

        for(int i = 0; i < n; i++)
            T[0][i] = dane.get(i);

        for(int i = 1; i < r; i++)
        {
            if(!czy_stop)
            {
                for(int j = 0; j < p; j++)
                {
                    T[i][j] = T[0][T[i - 1][j] - 1];
                    if(j + 1 == T[i - 1][j])
                    {
                        min_r = i + 1;
                        min_p = j + 1;
                        czy_stop = true;
                        break;
                    }
                }
            }
        }

        String wynik4_3 = "4.3.\n%d %d\n\n".formatted(min_r, min_p);

        //Zadanie 4.4.
        StringBuilder wynik4_4 = new StringBuilder("4.4.\n");

        int[] l_p = new int[n];
        for(Integer k : dane)
            l_p[k - 1]++;
        wynik4_4.append("%d ".formatted(Arrays.stream(l_p).max().getAsInt()));

        for(int i = 1; i < r; i++)
        {
            for(int j = 0; j < p; j++)
            {
                T[i][j] = T[0][T[i - 1][j] - 1];
            }

            if(i + 1 == 2 || i + 1 == 4 || i + 1 == 8)
            {
                l_p = new int[n];
                for(Integer k : T[i])
                    l_p[k - 1]++;
                wynik4_4.append("%d ".formatted(Arrays.stream(l_p).max().getAsInt()));
            }
        }

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2024\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
