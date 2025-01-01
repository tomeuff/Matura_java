package Maj2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Maj2016_5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner_studenci = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016\\studenci.txt"));
        Scanner scanner_meldunek = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016" +
                "\\meldunek.txt"));
        Scanner scanner_wypozyczenia = new Scanner(new File("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016" +
                "\\wypozyczenia.txt"));

        HashMap<String, Student> studenci = new HashMap<>();
        HashMap<Integer, Meldunek> meldunek = new HashMap<>();
        HashMap<Integer, Wypozyczenie> wypozyczenia = new HashMap<>();

        scanner_studenci.nextLine();
        while(scanner_studenci.hasNext())
        {
            String[] linia = scanner_studenci.nextLine().split("\t");
            studenci.put(linia[0], new Student(linia[0], linia[1], linia[2]));
        }

        int nr_meldunku = 1;
        scanner_meldunek.nextLine();
        while(scanner_meldunek.hasNext())
        {
            String[] linia = scanner_meldunek.nextLine().split("\t");
            meldunek.put(nr_meldunku, new Meldunek(nr_meldunku, linia[0], Integer.parseInt(linia[1])));
            nr_meldunku++;
        }

        scanner_wypozyczenia.nextLine();
        while(scanner_wypozyczenia.hasNext())
        {
            String[] linia = scanner_wypozyczenia.nextLine().split("\t");
            wypozyczenia.put(Integer.parseInt(linia[0]), new Wypozyczenie(Integer.parseInt(linia[0]), linia[1],
                    linia[2]));
        }

        //Zadanie 5.1.
        HashMap<Student, Integer> SW = new HashMap<>();

        for(Student s : studenci.values())
        {
            int ile_wyp = 0;
            for(Wypozyczenie w : wypozyczenia.values())
                if(Objects.equals(w.pesel, s.pesel))
                    ile_wyp++;
            SW.put(s, ile_wyp);
        }

        Map.Entry<Student, Integer> SW_max = Collections.max(SW.entrySet(), Map.Entry.comparingByValue());
        ArrayList<String> tytuly = new ArrayList<>();

        for(Wypozyczenie w : wypozyczenia.values())
        {
            if(Objects.equals(w.pesel, SW_max.getKey().pesel))
                tytuly.add(w.tytul);
        }

        String wynik5_1 = "5.1.\nimię i nazwisko: %s %s\ntytuły książek:\n%s\n\n".formatted(
                SW_max.getKey().imie, SW_max.getKey().nazwisko, String.join("\n", tytuly));

        //Zadanie 5.2.
        int ile_zameldowanych = meldunek.size();
        int ile_pokoi = meldunek.values().stream().map(v -> v.id_pok).collect(Collectors.toSet()).size();

        String wynik5_2 = "5.2.\n%.4f\n\n".formatted((double) ile_zameldowanych / (double) ile_pokoi);

        //Zadanie 5.3.
        long ile_k =
                studenci.entrySet().stream().filter(it -> Integer.parseInt(it.getValue().pesel.substring(9,10)) % 2 == 0).count();

        String wynik5_3 = "5.3.\nliczba kobiet: %d\nliczba mężczyzn: %d\n\n".formatted(ile_k, studenci.size() - ile_k);

        //Zadanie 5.4.
        ArrayList<Student> studenci_spoza_miasteczka = new ArrayList<>();

        for(Student s : studenci.values())
        {
            boolean czy_jest = false;
            for(Meldunek m : meldunek.values())
            {
                if(Objects.equals(m.pesel, s.pesel))
                {
                    czy_jest = true;
                    break;
                }
            }
            if(!czy_jest)
                studenci_spoza_miasteczka.add(s);
        }

        studenci_spoza_miasteczka.sort(Comparator.comparing(a -> a.nazwisko));

        String wynik5_4 = "5.4.\n%s\n".formatted(studenci_spoza_miasteczka.stream().map(it -> "%s %s\n".formatted(
                it.imie, it.nazwisko
        )).collect(Collectors.joining("")));

        //Zadanie 5.5.
        int podreczniki_spoza_miasteczka = 0;

        for(Student s : studenci_spoza_miasteczka)
        {
            for(Wypozyczenie w : wypozyczenia.values())
            {
                if(Objects.equals(s.pesel, w.pesel))
                    podreczniki_spoza_miasteczka++;
            }
        }

        HashSet<PodrecznikPokoj> PP = new HashSet<>();

        for(Meldunek m : meldunek.values())
        {
            for(Wypozyczenie w : wypozyczenia.values())
            {
                if(Objects.equals(w.pesel, m.pesel))
                    PP.add(new PodrecznikPokoj(w.tytul, m.id_pok));
            }
        }

        int podrecznik_z_miasteczka = PP.size();

        String wynik5_5 = "5.5.\n%d\n".formatted(podrecznik_z_miasteczka + podreczniki_spoza_miasteczka);

        try (FileWriter wynik5 = new FileWriter("C:\\Users\\TK\\IdeaProjects\\Matura_java\\src\\Maj2016\\wyniki5.txt")) {
            wynik5.write("");
            wynik5.append(wynik5_1);
            wynik5.append(wynik5_2);
            wynik5.append(wynik5_3);
            wynik5.append(wynik5_4);
            wynik5.append(wynik5_5);
        }
    }
}
