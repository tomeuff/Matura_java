package Maj2012_5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Maj2012_5 {
    public static void main(String[] args) throws IOException {
        int[][] T = new int[30][30];

        for(int i = 0; i < 30; i++)
            T[i][0] = 1;

        for(int i = 1; i < 30; i++)
            T[0][i] = 0;

        for(int i = 1; i < 30; i++)
            for(int j = 1; j < 30; j++)
                T[i][j] = T[i - 1][j - 1] + T[i - 1][j];

        //a)
        int max_10 = Arrays.stream(T[9]).max().getAsInt();
        int max_20 = Arrays.stream(T[19]).max().getAsInt();
        int max_30 = Arrays.stream(T[29]).max().getAsInt();
        String wynik_a = "a)\n10: %d\n20: %d\n30: %d\n\n".formatted(max_10, max_20, max_30);

        //b)
        StringBuilder wynik_b = new StringBuilder("b)\n");
        for(int i = 0; i < 30; i++)
        {
            String wiersz = Arrays.stream(T[i]).filter(it -> it != 0).mapToObj(Integer::toString).collect(Collectors.joining());
            wynik_b.append("%d %d\n".formatted(i + 1, wiersz.length()));
        }
        wynik_b.append("\n");

        //c)
        StringBuilder wynik_c = new StringBuilder("b)\n");
        for(int i = 0; i < 30; i++)
        {
            long wiersz = Arrays.stream(T[i]).filter(it -> it != 0 && it % 5 == 0).count();
            if(wiersz == 0)
                wynik_c.append("%d\n".formatted(i + 1));
        }
        wynik_c.append("\n");

        //d)
        StringBuilder wynik_d = new StringBuilder("d)\n");
        for(int i = 0; i < 30; i++)
        {
            for(int j = 0; j < 30; j++)
                wynik_d.append("%c\t".formatted(T[i][j] % 3 == 0 && T[i][j] != 0 ? 'X' : ' '));
            wynik_d.append("\n");
        }

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2012_5\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik_a);
            wynik5.append(wynik_b);
            wynik5.append(wynik_c);
            wynik5.append(wynik_d);
        }
    }
}
