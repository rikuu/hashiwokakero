package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

public class SiltaKartta {
    private ArrayList<Silta> sillat;
    
    SiltaKartta() {
        sillat = new ArrayList<>();
    }
    
    boolean lisaa(Saari a, Saari b) {
        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                return s.tuplaa();
            }
        }
        
        sillat.add(new Silta(a, b));
        return true;
    }
    
    void poista(Saari a, Saari b) {
        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {                
                sillat.remove(s);
                return;
            }
        }
    }
    
    int maara() {
        int summa = 0;
        
        for (Silta s : sillat) {
            summa += (s.onTupla()) ? 2 : 1;
        }
        
        return summa;
    }
}
