package riku.hashiwokakero.logiikka;

public class Peli {
    private SaariKartta saaret;
    private SiltaKartta sillat;
    
    private Generaattori gen;
    
    public Peli(int maara) {
        sillat = new SiltaKartta();
        saaret = new SaariKartta();
        
        gen = new Generaattori(saaret);
        
        uusiPeli(maara);
    }

    private void uusiPeli(int maara) {
        for (int i = 0; i < maara; i++)
            gen.uusiSaari();
    }
    
    public boolean ratkaistu() {
        return saaret.ratkaistu();
    }
    
    public SiltaKartta getSillat() {
        return sillat;
    }
    
    public SaariKartta getSaaret() {
        return saaret;
    }
    
    public boolean onSaari(int x, int y) {
        return (saaret.getSaari(x, y) != null);
    }
    
    public void uusiSilta(int ax, int ay, int bx, int by) {
        Saari a = saaret.getSaari(ax, ay);
        Saari b = saaret.getSaari(bx, by);
        
        if (!saaret.saariaValissa(a, b))
            sillat.lisaa(a, b);
    }
    
    public void poistaSilta(int x, int y) {
        Silta silta = sillat.getSilta(x, y);
        
        if (silta != null) {
            sillat.poista(silta.lahto, silta.loppu);
        }
    }
}
