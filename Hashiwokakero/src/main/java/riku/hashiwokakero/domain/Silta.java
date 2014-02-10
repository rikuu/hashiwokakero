package riku.hashiwokakero.domain;

/**
 * Yhdistää saaria.
 */
public class Silta {
    public final Saari lahto, loppu;
    private boolean tupla;
    
    public Silta(Saari a, Saari b) {
        lahto = a;
        loppu = b;
        
        tupla = false;
        
        lahto.lisaaSilta();
        loppu.lisaaSilta();
    }
    
    public void romauta() {
        if (tupla) {
            lahto.poistaSilta();
            loppu.poistaSilta();
        }
        
        lahto.poistaSilta();
        loppu.poistaSilta();
    }
    
    public boolean onTupla() {
        return tupla;
    }
    
    public void tuplaa() {
        if (!tupla) {
            tupla = true;

            lahto.lisaaSilta();
            loppu.lisaaSilta();
        }
    }
    
    public boolean yhdistaa(Saari a, Saari b) {        
        return ((lahto == a) && (loppu == b)) ||
                (lahto == b) && (loppu == a);
    }
    
    public boolean yhdistaa(Saari saari) {
        return ((lahto == saari) || (loppu == saari));
    }
}
