package Marzec2021_4;

import java.util.ArrayList;

public class Galeria {
    public String kraj;
    public String miasto;
    public ArrayList<Integer> lokale;
    public int c_powierzchnia;
    public int l_r_lokali;
    public Galeria(String kraj, String miasto, ArrayList<Integer> lokale)
    {
        this.kraj = kraj;
        this.miasto = miasto;
        this.lokale = lokale;
    }
}
