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
}
