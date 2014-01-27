package riku.hashiwokakero.logiikka;

public class Silta {
    public final Saari lahto, loppu;
    private boolean tupla;
    
    Silta(Saari a, Saari b) {
        lahto = a;
        loppu = b;
        
        tupla = false;
    }
    
    public boolean onTupla() {
        return tupla;
    }
    
    public boolean tuplaa() {        
        if (tupla)
            return false;
                
        tupla = true;
        return true;
    }
    
    public boolean yhdistaa(Saari a, Saari b) {        
        return ((lahto == a) && (loppu == b)) ||
                (lahto == b) && (loppu == a);
    }
    
    public boolean yhdistaa(Saari saari) {
        return ((lahto == saari) || (loppu == saari));
    }
}
