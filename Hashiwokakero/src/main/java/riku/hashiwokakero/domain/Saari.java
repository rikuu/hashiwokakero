package riku.hashiwokakero.domain;

/**
 * Edustaa saaria.
 */
public class Saari {
    /**
     * Saaren koordinaatit
     */
    public final int x, y;
    
    /**
     * Siltojen määrä
     */
    private int sillat, vaaditutSillat;
    
    /**
     * Luo uuden saaren.
     * @param x X-koordinaatti
     * @param y Y-koordinaatti
     * @param siltoja Vaadittujen siltojen määrä
     */
    public Saari(int x, int y, int siltoja) {
        vaaditutSillat = Math.max(siltoja, 0);
        
        this.x = x;
        this.y = y;
        
        sillat = 0;
    }
    
    /**
     * Nostaa siltojen määrän laskuria.
     */
    public void lisaaSilta() {
        sillat++;
    }
    
    /**
     * Vähentää siltojen määrän laskuria yhdellä.
     * Älä pliis kutsu jos et oo SaariKartta
     */
    public void poistaSilta() {
        sillat = Math.max(sillat - 1, 0);
    }
        
    public int getSillat() {
        return sillat;
    }
    
    public int getVaaditutSillat() {
        return vaaditutSillat;
    }
    
    /**
     * Lisää vaadittuihin siltoihin x:n verran lisää
     * @param x haluttu määrä vaatia lisää siltoja
     */
    public void vaadiLisaa(int x) {
        vaaditutSillat += x;
    }
    
    /**
     * Tarkistaa onko siltoja tarpeeksi.
     * 
     * @return true jos siltoja on yhtä paljon kun vaaditaan
     */
    public boolean tarpeeksiSiltoja() {
        return (vaaditutSillat == sillat);
    }
    
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (!(obj instanceof Saari)))
            return false;
        else if (obj == this)
            return true;
        
        Saari saari = (Saari) obj;
        return ((saari.x == this.x) && (saari.y == this.y));
    }
}
