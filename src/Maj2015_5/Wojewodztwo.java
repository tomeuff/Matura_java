package Maj2015_5;

import java.util.HashMap;

public class Wojewodztwo
{
    public Wojewodztwo(String nazwa_woj, int lk2013, int lm2013, int lk2014, int lm2014)
    {
        this.nazwa_woj = nazwa_woj;
        this.woj = nazwa_woj.substring(0, 3);
        this.region = nazwa_woj.substring(3, 4);
        this.Kobiety.put(2013, lk2013);
        this.Kobiety.put(2014, lk2014);
        this.Mezczyzni.put(2013, lm2013);
        this.Mezczyzni.put(2014, lm2014);
        this.Ludnosc.put(2013, lk2013 + lm2013);
        this.Ludnosc.put(2014, lk2014 + lm2014);
        this.tempo = Math.floor((double) this.Ludnosc.get(2014) / (double) this.Ludnosc.get(2013) * 10000) / 10000;
    }
    public String nazwa_woj;
    public HashMap<Integer, Integer> Kobiety = new HashMap<>();
    public HashMap<Integer, Integer> Mezczyzni = new HashMap<>();
    public HashMap<Integer, Integer> Ludnosc = new HashMap<>();
    public String woj;
    public String region;
    public double tempo;
}
