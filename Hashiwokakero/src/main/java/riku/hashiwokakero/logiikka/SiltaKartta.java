package riku.hashiwokakero.logiikka;

import riku.hashiwokakero.domain.Silta;
import riku.hashiwokakero.domain.Saari;
import java.util.ArrayList;

/**
 * Huolehtii silloista.
 */
public class SiltaKartta {
    private ArrayList<Silta> sillat;
    
    public SiltaKartta() {
        sillat = new ArrayList<>();
    }
    
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
    
    public void poista(Saari a, Saari b) {
        for (Silta s : sillat) {
            if (s.yhdistaa(a, b)) {
                s.romauta();
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
    
    public ArrayList<Silta> getSillat() {
        return sillat;
    }
    
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
