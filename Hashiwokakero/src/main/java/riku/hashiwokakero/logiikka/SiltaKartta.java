package riku.hashiwokakero.logiikka;

import java.util.ArrayList;
import java.util.LinkedList;

import riku.hashiwokakero.domain.Silta;
import riku.hashiwokakero.domain.Saari;

/**
 * Huolehtii silloista.
 */
public class SiltaKartta {
    /**
     * Lista kaikista silloista
     */
    private final ArrayList<Silta> sillat;
    
    /**
     * Sillat ryhmitetty yhteisten saarien mukaan.
     */
    private ArrayList<ArrayList<Silta>> ryhmat;
    
    /**
     * Kertoo onko ryhmat ajantasalla.
     */
    private boolean ryhmatSynkronisoitu;
    
    /**
     * Luo uuden SiltaKartan
     */
    public SiltaKartta() {
        sillat = new ArrayList<>();
        
        ryhmat = new ArrayList<>();
        ryhmatSynkronisoitu = true;
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

        ryhmatSynkronisoitu = false;
        
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
                
                ryhmatSynkronisoitu = false;
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
    
    /**
     * Jakaa sillat ryhmiin, sen mukaan yhdistävätkö sillat yhteisiä saaria.
     * Kuuluu kutsua aina jos siltoja on lisätty tai poistettu.
     */
    private void ryhmita() {
        ryhmat.clear();
        
        // Ei voi iteroida tän läpi, koska Java on vihainen ja vaati uhrauksia.
        ArrayList<Silta> sillatJaljella = (ArrayList<Silta>) sillat.clone();
        
        for (Silta s : sillat) {
            if (!sillatJaljella.contains(s)) {
                continue;
            }
            
            ArrayList<Silta> ryhma = new ArrayList<>();
            ryhma.add(s);
            
            LinkedList<Saari> saaret = new LinkedList<>();
            saaret.push(s.lahto);
            saaret.push(s.loppu);
            
            while (!saaret.isEmpty()) {
                Saari saari = saaret.pop();
                
                for (Silta silta : sillat) {
                    if (!sillatJaljella.contains(silta) || (silta == s)) {
                        continue;
                    }
                    
                    if (silta.yhdistaa(saari) && !ryhma.contains(silta)) {
                        sillatJaljella.remove(silta);
                        
                        ryhma.add(silta);

                        if (silta.lahto == saari) {
                            saaret.push(silta.loppu);
                        } else {
                            saaret.push(silta.lahto);
                        }
                    }
                }
            }
            
            ryhmat.add(ryhma);
        }
    }
    
    public ArrayList<ArrayList<Silta>> getRyhmat() {
        if (!ryhmatSynkronisoitu) {
            ryhmita();
        }
            
        return ryhmat;
    }
}
