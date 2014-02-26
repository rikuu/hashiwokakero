package riku.hashiwokakero.domain;

/**
 * Yhdistää saaria.
 */
public class Silta {
    /**
     * Saaret, joita silta yhdistää.
     */
    public final Saari lahto, loppu;
    
    /**
     * True, jos silta edustaa kahta saman, kahden saaren, välistä siltaa.
     */
    private boolean tupla;
    
    /**
     * Luo uuden sillan
     * @param a Saari a
     * @param b Saari b
     */
    public Silta(Saari a, Saari b) {
        lahto = a;
        loppu = b;
        
        tupla = false;
        
        lahto.lisaaSilta();
        loppu.lisaaSilta();
    }
    
    /**
     * Vähentää yhdistävien saarten siltamäärää yhdellä,
     * kahdella jos on tuplasilta
     */
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
    
    /**
     * Asettaa sillan tuplaksi ja sanoo saarille että nyt on toinenki silta.
     */
    public void tuplaa() {
        if (!tupla) {
            tupla = true;

            lahto.lisaaSilta();
            loppu.lisaaSilta();
        }
    }
    
    /**
     * Kertoo yhdistääkö silta annettuja saaria.
     * @param a Saari a
     * @param b Saari b
     * @return No yhdistääkö
     */
    public boolean yhdistaa(Saari a, Saari b) {
        return ((lahto == a) && (loppu == b)) ||
                (lahto == b) && (loppu == a);
    }
    
    public boolean yhdistaa(Saari a) {
        return ((lahto == a) || (loppu == a));
    }
}
