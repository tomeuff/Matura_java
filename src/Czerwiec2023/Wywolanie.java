package Czerwiec2023;

public class Wywolanie {
    public Wywolanie()
    {

    }

    public int nr;
    public int x;
    public int y;
    public int k;
    public int z;
    public int wynik = 0;

    @Override
    public String toString() {
        return "%d\t%d\t%d\t%d\t%d\t%d".formatted(this.nr, this.x, this.y, this.k, this.z, this.wynik);
    }
}
