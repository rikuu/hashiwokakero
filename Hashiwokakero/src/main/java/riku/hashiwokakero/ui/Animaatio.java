package riku.hashiwokakero.ui;

public class Animaatio {
    private final int alku, loppu;
    private double aika;
    
    public Animaatio(int alku, int loppu) {
        this.alku = alku;
        this.loppu = loppu;
        
        aika = 0;
    }
    
    public int getOffset() {
        if (aika <= 1.0) {
            return (int) (alku + (loppu - alku) * aika);
        }
        
        return 0;
    }
    
    public void step() {
        aika += 0.01;
    }
}
