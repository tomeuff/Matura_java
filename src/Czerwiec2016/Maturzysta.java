package Czerwiec2016;

public class Maturzysta {
    public Maturzysta(int id_zdajacego, String nazwisko, String imie, String PESEL, String data_urodzenia) {
        Id_zdajacego = id_zdajacego;
        Nazwisko = nazwisko;
        Imie = imie;
        this.PESEL = PESEL;
        Data_urodzenia = data_urodzenia;
    }

    public int Id_zdajacego;
    public String Nazwisko;
    public String Imie;
    public String PESEL;
    public String Data_urodzenia;
}
