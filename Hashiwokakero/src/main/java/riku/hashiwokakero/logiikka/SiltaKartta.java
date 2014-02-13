package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

import riku.hashiwokakero.domain.Silta;
import riku.hashiwokakero.domain.Saari;

/**
 * Huolehtii silloista.
 */
public class SiltaKartta {
    /**
     * Lista kaikista silloista
     */
    private ArrayList<Silta> sillat;
    
    /**
     * Luo uuden SiltaKartan
     */
    public SiltaKartta() {
        sillat = new ArrayList<>();
    }
    
    /**
     * Lisää sillan karttaan, jos a != b ja ovat kohtisuorassa.
     * Tuplaa sillan jos silta on jo olemssa.
     * @param a Saari a
     * @param b Saari b
     */
    public void lisaa(Saari a, Saari b) {
        if (((a.x != b.x) && (a.y != b.y)) || (a == b))
            return;

        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                s.tuplaa();
                return;
            }
        }
        
        sillat.add(new Silta(a, b));
    }
    
    /**
     * Poistaa sillat, jotka yhdistävät saaret a ja b.
     * @param a Saari a
     * @param b Saari b
     */
    public void poista(Saari a, Saari b) {
        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                s.romauta();
                sillat.remove(s);
                
                return;
            }
        }
    }
    
    public ArrayList<Silta> getSillat() {
        return sillat;
    }
    
    /**
     * Etsii sillan, joka on koordinaattien alla.
     * Silta on koordinaattien alla, jos sen yhdistävät saaret ovat
     * joko y- tai x-akselilla, ylä- ja alapuolella.
     * 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return haluttu silta
     */
    public Silta getSilta(int x, int y) {
        for (Silta s : sillat) {
            if (s.lahto.x == s.loppu.x) {
                if ((x == s.lahto.x) &&
                    (y <= Math.max(s.lahto.y, s.loppu.y)) &&
                    (y >= Math.min(s.lahto.y, s.loppu.y)))
                        return s;
            } else {
                if ((y == s.lahto.y) &&
                    (x <= Math.max(s.lahto.x, s.loppu.x)) &&
                    (x >= Math.min(s.lahto.x, s.loppu.x)))
                        return s;
            }
        }
        
        return null;
    }
}
