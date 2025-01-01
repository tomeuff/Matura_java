package Maj2016;

import java.util.Objects;

public class PodrecznikPokoj {
    public PodrecznikPokoj(String tytul, int id_pok) {
        this.tytul = tytul;
        this.id_pok = id_pok;
    }

    public String tytul;
    public int id_pok;

    @Override
    public boolean equals(Object obj) {
        return ((PodrecznikPokoj) obj).tytul.equals(this.tytul) && ((PodrecznikPokoj) obj).id_pok == this.id_pok;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tytul, id_pok);
    }
}
