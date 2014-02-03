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
        // 18 = saaren leveys / 2
        // voi olla parempi miettii onko nyt kyse
        // logiikka- vai ui-pakkauksen tarpeista
        
        for (Saari saari : saaret) {
            int keskix = (800 / 2) + (saari.x * 36);
            int keskiy = (600 / 2) + (saari.y * 36);
            
            if ((x >= (keskix - 18)) && (x <= (keskix + 18)) &&
                (y >= (keskiy - 18)) && (y <= (keskiy + 18)))
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
