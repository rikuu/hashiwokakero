package riku.hashiwokakero.ui;

/**
 * Yleinen luokka animaatioiden hoitamiseen.
 * @author riku
 */
public class Animaatio {
    /**
     * Animaation alku ja loppu.
     */
    private final int alku, loppu;
    
    /**
     * Nykyinen hetki animaatiossa. Arvo on 0..1
     */
    private double aika;
    
    /**
     * Luo luokan.
     * @param alku Animaation alku-arvo
     * @param loppu Animaation loppu-arvo
     */
    public Animaatio(int alku, int loppu) {
        this.alku = alku;
        this.loppu = loppu;
        
        aika = 0;
    }
    
    /**
     * Lineaarisesti interpoloi animaation paikan nykyisen hetken.
     * @return Animaation nykyinen arvo
     */
    public int getOffset() {
        if (aika <= 1.0) {
            return (int) (alku + (loppu - alku) * aika);
        }
        
        return loppu;
    }
    
    /**
     * Etenee animaatiota yhden sadasosan.
     */
    public void step() {
        aika += 0.01;
    }
}
