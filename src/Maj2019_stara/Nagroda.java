package Maj2019_stara;

public class Nagroda {
    public Nagroda(int id_nagrody, String id_filmu, String id_aktora, String kategoria) {
        this.id_nagrody = id_nagrody;
        this.id_filmu = id_filmu;
        this.id_aktora = id_aktora;
        this.kategoria = kategoria;
    }

    public int id_nagrody;
    public String id_filmu;
    public String id_aktora;
    public String kategoria;
}
