package Marzec2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Marzec2022_1 {
    final static int n = 125;
    final static int w = 8;
    final static int k = 8;
    public static char[][][] dane;
    public static boolean czy_szachuje(int plansza, int wier, int kol)
    {
        char jaki_krol = dane[plansza][wier][kol] == 'w' ? 'K' : 'k';

        for(int i = wier + 1; i < w; i++)
        {
            if(dane[plansza][i][kol] == jaki_krol)
            {
                return true;
            }
            else if(dane[plansza][i][kol] != '.')
            {
                break;
            }
        }

        for(int i = wier - 1; i >= 0; i--)
        {
            if(dane[plansza][i][kol] == jaki_krol)
            {
                return true;
            }
            else if(dane[plansza][i][kol] != '.')
            {
                break;
            }
        }

        for(int i = kol + 1; i < k; i++)
        {
            if(dane[plansza][wier][i] == jaki_krol)
            {
                return true;
            }
            else if(dane[plansza][wier][i] != '.')
            {
                break;
            }
        }

        for(int i = kol - 1; i >= 0; i--)
        {
            if(dane[plansza][wier][i] == jaki_krol)
            {
                return true;
            }
            else if(dane[plansza][wier][i] != '.')
            {
                break;
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\szachy.txt"));
        dane = new char[n][w][k];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < w; j++)
            {
                dane[i][j] = scanner.nextLine().toCharArray();
            }
            scanner.nextLine();
        }

        //Zadanie 1.1.
        int ile_plansz_z_pusta_kolumna = 0;
        int max_liczba_pustych_kolumn = 0;

        for(int i = 0; i < n; i++)
        {
            int liczba_pustych_kolumn = 0;

            for(int kol = 0; kol < k; kol++)
            {
                int ile_pustych_pol = 0;

                for(int wier = 0; wier < w; wier++)
                {
                    if(dane[i][wier][kol] == '.')
                    {
                        ile_pustych_pol++;
                    }
                }
                if(ile_pustych_pol == 8)
                {
                    liczba_pustych_kolumn++;
                }
            }
            if(liczba_pustych_kolumn > 0)
            {
                ile_plansz_z_pusta_kolumna++;

                if(liczba_pustych_kolumn > max_liczba_pustych_kolumn)
                {
                    max_liczba_pustych_kolumn = liczba_pustych_kolumn;
                }
            }
        }

        String wynik1_1 = "1.1.\n%d %d\n\n".formatted(ile_plansz_z_pusta_kolumna, max_liczba_pustych_kolumn);

        //Zadanie 1.2.
        int liczba_plansz_z_rownowaga = 0;
        int min_liczba_bierek = 64;

        for(int i = 0; i < n; i++)
        {
            ArrayList<Character> stan_biale = new ArrayList<>();
            ArrayList<Character> stan_czarne = new ArrayList<>();

            for(int wier = 0; wier < w; wier++)
            {
                for(int kol = 0; kol < k; kol++)
                {
                    if(dane[i][wier][kol] != '.')
                    {
                        if(dane[i][wier][kol] > 90)
                        {
                            stan_czarne.add((char) (dane[i][wier][kol] - 32));
                        }
                        else
                        {
                            stan_biale.add(dane[i][wier][kol]);
                        }
                    }
                }
            }

            Collections.sort(stan_biale);
            Collections.sort(stan_czarne);

            if(stan_biale.equals(stan_czarne))
            {
                liczba_plansz_z_rownowaga++;

                if(stan_biale.size() * 2 < min_liczba_bierek)
                {
                    min_liczba_bierek = stan_biale.size() * 2;
                }
            }
        }

        String wynik1_2 = "1.2.\n%d %d\n\n".formatted(liczba_plansz_z_rownowaga, min_liczba_bierek);

        //Zadanie 1.3.
        int ile_razy_biala_w_szachuje_czarnego_k = 0;
        int ile_razy_czarna_w_szachuje_bialego_k = 0;

        for(int i = 0; i < n; i++)
        {
            for(int wier = 0; wier < w; wier++)
            {
                for(int kol = 0; kol < k; kol++)
                {
                    if(dane[i][wier][kol] == 'W')
                    {
                        if(czy_szachuje(i, wier, kol))
                            ile_razy_biala_w_szachuje_czarnego_k++;
                    }
                    else if(dane[i][wier][kol] == 'w')
                    {
                        if(czy_szachuje(i, wier, kol))
                            ile_razy_czarna_w_szachuje_bialego_k++;
                    }
                }
            }
        }
        String wynik1_3 = "1.3.\n%d %d\n".formatted(ile_razy_biala_w_szachuje_czarnego_k, ile_razy_czarna_w_szachuje_bialego_k);

        try (FileWriter wynik1 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Marzec2022\\wyniki1.txt")) {
            wynik1.write("");
            wynik1.append(wynik1_1);
            wynik1.append(wynik1_2);
            wynik1.append(wynik1_3);
        }
    }
}
