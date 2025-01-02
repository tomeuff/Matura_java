package Maj2019_stara;

import java.util.ArrayList;

public class AktorNagrody {
    public AktorNagrody(Aktor aktor, ArrayList<Film> pierwszoplanowe, ArrayList<Film> drugoplanowe) {
        this.aktor = aktor;
        this.pierwszoplanowe = pierwszoplanowe;
        this.drugoplanowe = drugoplanowe;
    }

    public Aktor aktor;
    public ArrayList<Film> pierwszoplanowe;
    public ArrayList<Film> drugoplanowe;
}
