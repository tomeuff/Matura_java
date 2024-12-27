package Czerwiec2021_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Czerwiec2021_4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2021_4\\napisy.txt"));
        ArrayList<String> dane = new ArrayList<>();

        while(scanner.hasNext())
            dane.add(scanner.nextLine());

        //Zadanie 4.1.
        String wynik4_1 = "4.1.\n%d\n\n".formatted(dane.stream().mapToInt(it1 ->
                it1.chars().filter(it2 -> it2 >= 48 && it2 <= 57).map(it2 -> 1).sum()).sum());

        //Zadanie 4.2.
        StringBuilder wynik4_2 = new StringBuilder("4.2.\n");

        for(int i = 19; i < dane.size(); i += 20)
            wynik4_2.append(dane.get(i).charAt(i / 20));
        wynik4_2.append("\n\n");

        //Zadanie 4.3.
        StringBuilder wynik4_3 = new StringBuilder("4.3.\n");
        for(String s : dane)
        {
            StringBuilder sb1 = new StringBuilder(s);
            StringBuilder sb2 = new StringBuilder(s);

            sb1.append(sb1.charAt(0));
            sb2.insert(0, sb2.charAt(sb2.length() - 1));

            StringBuilder sb1_odwr = new StringBuilder(sb1).reverse();
            StringBuilder sb2_odwr = new StringBuilder(sb2).reverse();

            if(sb1.toString().contentEquals(sb1_odwr))
                wynik4_3.append(sb1.charAt(25));
            else if(sb2.toString().contentEquals(sb2_odwr))
                wynik4_3.append(sb2.charAt(25));

        }
        wynik4_3.append("\n\n");

        //Zadanie 4.4.
        StringBuilder wynik4_4 = new StringBuilder("4.4.\n");
        int ileX = 0;
        boolean czy_koniec = false;
        for(String s : dane)
        {
            if(czy_koniec)
                break;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length(); i++)
            {
                if(s.charAt(i) >= 48 && s.charAt(i) <= 57)
                {
                    sb.append(s.charAt(i));
                    if(sb.length() == 2)
                    {
                        char znak = (char) Integer.parseInt(sb.toString());
                        if(znak == 'X')
                        {
                            ileX++;
                            if(ileX == 3)
                            {
                                czy_koniec = true;
                                wynik4_4.append(znak);
                                break;
                            }
                        }
                        else
                            ileX = 0;

                        if(znak >= 65 && znak <= 90)
                            wynik4_4.append(znak);
                        sb = new StringBuilder();
                    }
                }
            }
        }
        wynik4_4.append("\n\n");

        try (FileWriter wynik4 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Czerwiec2021_4\\wyniki4.txt")) {
            wynik4.write("");
            wynik4.append(wynik4_1);
            wynik4.append(wynik4_2);
            wynik4.append(wynik4_3);
            wynik4.append(wynik4_4);
        }
    }
}
