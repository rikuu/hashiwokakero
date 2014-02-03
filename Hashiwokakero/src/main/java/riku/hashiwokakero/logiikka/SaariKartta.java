package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

public class SaariKartta {
    private ArrayList<Saari> saaret;
    
    public SaariKartta() {
        saaret = new ArrayList<>();
    }
    
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
    
    public Saari getSaari(int x, int y) {
        for (Saari saari : saaret) {
            if ((x == saari.x) && (y == saari.y))
                return saari;
        }
        
        return null;
    }
    
    public void lisaa(Saari s) {
        saaret.add(s);
    }
    
    public boolean saariaValissa(Saari a, Saari b) {
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
}
