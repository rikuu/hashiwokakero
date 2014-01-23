package riku.hashiwokakero.logiikka;

public class Silta {
    private Saari lahto, loppu;
    private boolean tupla;
    
    Silta(Saari a, Saari b) {
        lahto = a;
        loppu = b;
        
        tupla = false;
    }
    
    boolean onTupla() {
        return tupla;
    }
    
    boolean tuplaa() {        
        if (tupla)
            return false;
                
        tupla = true;
        return true;
    }
    
    boolean yhdistaa(Saari a, Saari b) {        
        return ((lahto == a) && (loppu == b)) ||
                (lahto == b) && (loppu == a);
    }
    
    boolean yhdistaa(Saari saari) {
        return ((lahto == saari) || (loppu == saari));
    }
}
