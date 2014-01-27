package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

public class SiltaKartta {
    private ArrayList<Silta> sillat;
    
    public SiltaKartta() {
        sillat = new ArrayList<>();
    }
    
    public boolean lisaa(Saari a, Saari b) {
        if (((a.x != b.x) && (a.y != b.y)) || (a == b))
            return false;

        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                return s.tuplaa();
            }
        }
        
        sillat.add(new Silta(a, b));
        return true;
    }
    
    public void poista(Saari a, Saari b) {
        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                sillat.remove(s);                
                return;
            }
        }
    }
    
    public int maara(Saari saari) {
        int summa = 0;
        
        for (Silta s : sillat) {
            if (s.yhdistaa(saari))
                summa += s.onTupla() ? 2 : 1;
        }
        
        return summa;
    }
}
