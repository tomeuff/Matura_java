package Maj2017;

public class Magazyn {
    public Magazyn(String data, int stan_przed_transakcja, int stan_po_transakcji, int stan_po_uzupelnieniu) {
        this.data = data;
        this.stan_przed_transakcja = stan_przed_transakcja;
        this.stan_po_transakcji = stan_po_transakcji;
        this.stan_po_uzupelnieniu = stan_po_uzupelnieniu;
    }

    public String data;
    public int stan_przed_transakcja;
    public int stan_po_transakcji;
    public int stan_po_uzupelnieniu;
}
