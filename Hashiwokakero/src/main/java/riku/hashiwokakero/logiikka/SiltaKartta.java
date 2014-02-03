package riku.hashiwokakero.logiikka;

import java.util.ArrayList;

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
            int akx = (800 / 2) + (s.lahto.x * 36);
            int aky = (600 / 2) + (s.lahto.y * 36);
            
            int okx = (800 / 2) + (s.loppu.x * 36);
            int oky = (600 / 2) + (s.loppu.y * 36);
            
            if (akx == okx) {
                if ((x >= (akx - 18)) && (x <= (akx + 18)) &&
                    (y <= (Math.max(aky, oky)+18)) &&
                    (y >= (Math.min(aky, oky)+18)))
                        return s;
            } else {
                if ((y >= (aky - 18)) && (y <= (aky + 18)) &&
                    (x <= (Math.max(akx, okx)+18)) &&
                    (x >= (Math.min(akx, okx)+18)))
                        return s;
            }
        }
        
        return null;
    }
}
