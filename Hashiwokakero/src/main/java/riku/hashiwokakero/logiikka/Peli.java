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
            if (sillat.maara(saari) != saari.getVaaditut()) {
                return false;
            }                
        }
        
        return true;
    }
    
    void uusiSaari() {
        saaret.add(new Saari(0, 0, 0));
    }
}
