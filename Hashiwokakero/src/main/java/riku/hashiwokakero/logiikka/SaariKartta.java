package riku.hashiwokakero.logiikka;

import java.awt.Point;
import riku.hashiwokakero.domain.Saari;
import java.util.ArrayList;

/**
 * Pitää huolta saarista.
 */
public class SaariKartta {
    /**
     * Lista kaikista saarista.
     */
    private ArrayList<Saari> saaret;
    
    /**
     * Luo SaariKartan
     */
    public SaariKartta() {
        saaret = new ArrayList<>();
    }
    
    /**
     * Kertoo onko peli ratkaistu,
     * ts. onko kaikilla saarilla oikea määrä siltoja
     * @return true, jos ratkaistu
     */
    public boolean ratkaistu() {
        for (Saari saari : saaret) {
            if (!saari.tarpeeksiSiltoja()) {
                return false;
            }
        }
        
        return true;
    }
    
    public ArrayList<Saari> getSaaret() {
        return saaret;
    }
    
    /**
     * Palauttaa saaren, joka on koordinaattien kohdalla
     * @param x x-koordinaatti pelin ruudukossa
     * @param y y-koordinaatti pelin ruudukossa
     * @return Saari, jota halutaan tai null jos ei ole olemassa
     */
    public Saari getSaari(int x, int y) {
        for (Saari saari : saaret) {
            if ((x == saari.x) && (y == saari.y))
                return saari;
        }
        
        return null;
    }
    
    /**
     * Lisää saaren s SaariKarttaan
     * @param s saari s
     */
    public void lisaa(Saari s) {
        saaret.add(s);
    }
    
    /**
     * Tarkistaa onko mikään saari kahden annetun saaren välissä.
     * @param a Saari a
     * @param b Saari b
     * @return True, jos joku saari on x- tai y-akselilla saarten välillä
     */
    public boolean saariaValissa(Saari a, Saari b) {
        // Ei ole mielekästä edes tarkistaa onko saaria välissä, jos
        // annetut saaret eivät ole kohtisuorassa jossain mielessä.
        if ((a.x != b.x) && (a.y != b.y)) {
            return false;
        }
        
        for (Saari s : saaret) {
            if (a.x == b.x) {
                if ((s.x == a.x) &&
                    (s.y < Math.max(a.y, b.y)) && (s.y > Math.min(a.y, b.y)))
                        return true;
            } else {
                if ((s.y == a.y) &&
                    (s.x < Math.max(a.x, b.x)) && (s.x > Math.min(a.x, b.x)))
                        return true;
            }
        }
        
        return false;
    }
    
    /**
     * Laskee SaariKartan saarten keskipisteen.
     * @return keskipiste
     */
    private Point laskeKeskipiste() {
        Point max = new Point(0, 0);
        Point min = new Point(0, 0);
        
        for (Saari s : saaret) {
            max.x = Math.max(s.x, max.x);
            max.y = Math.max(s.y, max.y);
            
            min.x = Math.min(s.x, min.x);
            min.y = Math.min(s.y, min.y);
        }
        
        Point keskipiste = new Point(0, 0);
        keskipiste.x = min.x + (max.x - min.x) / 2;
        keskipiste.y = min.y + (max.y - min.y) / 2;
        
        return keskipiste;
    }
    
    /**
     * Siirtää saaret keskelle.
     */
    public void keskitaSaaret() {
        Point keskipiste = laskeKeskipiste();
        
        ArrayList<Saari> uudetSaaret = new ArrayList<>();
        for (Saari s : saaret) {
            int uusiX = s.x - keskipiste.x;
            int uusiY = s.y - keskipiste.y;
            
            Saari uusiSaari = new Saari(uusiX, uusiY, s.getVaaditutSillat());
            uudetSaaret.add(uusiSaari);
        }
        
        saaret = uudetSaaret;
    }
}
