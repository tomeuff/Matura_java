package Umiejetnosci_podstawowe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Umiejetnosci_podstawowe {
    public static void main(String[] args) {
        //Liczba na napis
        /*int liczba_int = 1234;
        String liczba_str = Integer.toString(liczba_int);
        System.out.println(liczba_str);*/

        //Suma cyfr
        /*int liczba_int = 1234;
        int suma_cyfr = Integer.toString(liczba_int).chars().map(it -> it - 48).sum();
        System.out.println(suma_cyfr);*/

        //Napis na set-a sposób 1
        /*String napis = "abbccadddec";
        HashSet<Character> hs = napis.chars().mapToObj(it -> (char) it).collect(Collectors.toCollection(HashSet::new));
        System.out.println(hs.size());*/

        //Napis na liste znaków
        /*String napis = "abbccadddec";
        ArrayList<Character> napis_list = napis.chars().mapToObj(it -> (char) it).
                collect(Collectors.toCollection(ArrayList::new));
        System.out.println(napis_list);*/

        //Napis na hashmapę
        /*String napis = "abbccadddec";
        HashMap<Character, Long> napis_hm = new HashMap<>(napis.chars().mapToObj(it -> (char) it).
                collect(Collectors.groupingBy(it -> it, Collectors.counting())));
        System.out.println(napis_hm);*/

        //Liczba danego znaku w słowie
        String napis = "abbccadddec";
        Long ile_a = napis.chars().mapToObj(it -> (char) it ).filter(it -> it == 'a').count();
        System.out.println(ile_a);
    }
}
