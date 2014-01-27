package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

public class Peli {
    private ArrayList<Saari> saaret;
    private SiltaKartta sillat;
    
    public Peli() {
        saaret = new ArrayList<>();
        sillat = new SiltaKartta();
    }
    
    public boolean ratkaistu() {
        for (Saari saari : saaret) {
            if (sillat.maara(saari) != saari.vaaditutSillat) {
                return false;
            }                
        }
        
        return true;
    }
    
    public void uusiSaari() {
        saaret.add(new Saari(0, 0, 0));
    }
    
    
    public Saari uusiSaari(int x, int y, int s) {
        Saari saari = new Saari(s, x, y);
        saaret.add(saari);
        return saari;
    }
    
    public SiltaKartta getSillat() {
        return sillat;
    }
    
    public ArrayList<Saari> getSaaret() {
        return saaret;
    }

    public Saari getSaari(int x, int y) {
        for (Saari s : saaret) {
            if ((x >= (s.x - 18)) && (x <= (s.x + 18)) &&
                (y >= (s.y - 18)) && (y <= (s.y + 18)))
                    return s;
        }
        
        return null;
    }
    
    public boolean saariaValissa(Saari a, Saari b) {
        for (Saari s : saaret) {
            if (a.x == b.x) {
                if ((s.x == a.x) &&
                        (s.y < Math.max(a.y, b.y)) && (s.y > Math.min(a.y, b.y))) {
                    return true;
                }
            } else {
                if ((s.y == a.y) &&
                        (s.x < Math.max(a.x, b.x)) && (s.x > Math.min(a.x, b.x))) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
