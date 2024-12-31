package Czerwiec2015;

public class Dzien {
    public Dzien(String data, int kostka_dostawa, int orzech_dostawa, int mial_dostawa) {
        this.data = data;
        this.kostka_dostawa = kostka_dostawa;
        this.orzech_dostawa = orzech_dostawa;
        this.mial_dostawa = mial_dostawa;
    }

    public String data;
    public int kostka_dostawa;
    public int orzech_dostawa;
    public int mial_dostawa;
    public int kostka_przed_dostawa;
    public int orzech_przed_dostawa;
    public int mial_przed_dostawa;
    public int kostka_po_dostawie;
    public int orzech_po_dostawie;
    public int mial_po_dostawie;
    public int kostka_po_paleniu;
    public int orzech_po_paleniu;
    public int mial_po_paleniu;
}
