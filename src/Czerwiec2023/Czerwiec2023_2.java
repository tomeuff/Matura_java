package Czerwiec2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Czerwiec2023_2 {
    public static int[] posortowane(int n, char[] s)
    {
        int[] T = new int[n];

        for(int i = 0; i < n; i++)
            T[i] = i + 1;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n - 1; j++)
            {
                if(czy_mniejszy(n, s, T[j + 1] - 1, T[j] - 1))
                {
                    int temp = T[j];
                    T[j] = T[j + 1];
                    T[j + 1] = temp;
                }
            }
        }

        return T;
    }
    public static int pierwszy_if = 0;
    public static boolean czy_mniejszy(int n, char[] s, int k1, int k2)
    {
        int i = k1;
        int j = k2;

        while(i < n && j < n)
        {
            pierwszy_if++;
            if(s[i] == s[j])
            {
                i = i + 1;
                j = j + 1;
            }
            else
            {
                if(s[i] < s[j])
                    return true;
                else
                    return false;
            }
        }
        if(j < n)
            return true;
        else
            return false;
    }
    public static void main(String[] args) throws IOException {
        /*char[] s = new char[]{'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'};
        int n = s.length;
        int k1 = 0; //1
        int k2 = 4; //5

        czy_mniejszy(n, s, k1, k2);
        System.out.println(pierwszy_if);*/

        //Zadanie 2.2.
        StringBuilder wynik2_2_sb = new StringBuilder();
        for(int i = 1; i <= 3; i++)
        {
            Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023" +
                    "\\slowa%d.txt".formatted(i)));
            int n = scanner.nextInt();
            char[] s = scanner.next().toCharArray();
            int k1 = scanner.nextInt();
            int k2 = scanner.nextInt();

            wynik2_2_sb.append("%s\n".formatted(czy_mniejszy(n, s, k1 - 1, k2 - 1) ? "TAK" : "NIE"));
        }

        try (FileWriter wynik2_2 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023\\wyniki2_2" +
                ".txt")) {
            wynik2_2.write(wynik2_2_sb.toString());
        }

        //Zadanie 2.3.
        /*int[] T = posortowane(11, new char[]{'k', 'a', 'l', 'a', 'f', 'i', 'o', 'r', 'o', 'w', 'a'});
        System.out.println(Arrays.stream(T).mapToObj(Integer::toString).collect(Collectors.joining(", ")));*/

        //Zadanie 2.4.
        StringBuilder wynik2_4_sb = new StringBuilder();

        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023\\slowa4.txt"));
        while(scanner.hasNext())
        {
            int n = scanner.nextInt();
            char[] s = scanner.next().toCharArray();

            int T[] = posortowane(n, s);
            wynik2_4_sb.append(new String(s).substring(T[0] - 1)).append("\n");
        }

        try (FileWriter wynik2_4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2023\\wyniki2_4" +
                ".txt")) {
            wynik2_4.write(wynik2_4_sb.toString());
        }
    }
}
