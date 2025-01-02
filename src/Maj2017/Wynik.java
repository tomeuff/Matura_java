package Maj2017;

public class Wynik {
    public Wynik(String data_meczu, String rodzaj_meczu, String gdzie, int id_druzyny, String nr_licencji, int bramki_zdobyte, int bramki_stracone) {
        Data_meczu = data_meczu;
        Rodzaj_meczu = rodzaj_meczu;
        Gdzie = gdzie;
        Id_druzyny = id_druzyny;
        Nr_licencji = nr_licencji;
        Bramki_zdobyte = bramki_zdobyte;
        Bramki_stracone = bramki_stracone;
    }

    public String Data_meczu;
    public String Rodzaj_meczu;
    public String Gdzie;
    public int Id_druzyny;
    public String Nr_licencji;
    public int Bramki_zdobyte;
    public int Bramki_stracone;
}
