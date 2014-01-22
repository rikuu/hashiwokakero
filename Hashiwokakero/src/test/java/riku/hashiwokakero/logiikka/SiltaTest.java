package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SiltaTest {
    private Saari a, b;
    private Silta silta;
    
    @Before
    public void setUp() {
        a = new Saari(0, 0, 0);
        b = new Saari(1, 1, 1);
        
        silta = new Silta(a, b);
    }
    
    @Test
    public void yhdistaaSaaret() {
        assertEquals(silta.yhdistaa(a, b), true);
    }
    
    @Test
    public void yhdistaaSaaretToisinpain() {
        assertEquals(silta.yhdistaa(b, a), true);
    }
    
    @Test
    public void alussaEiTupla() {
        assertEquals(silta.onTupla(), false);
    }
    
    @Test
    public void tuplaTuplaa() {
        assertEquals(silta.tuplaa(), true);
        assertEquals(silta.onTupla(), true);
    }
    
    @Test
    public void tuplaTuplaEiTuplaa() {
        assertEquals(silta.tuplaa(), true);
        assertEquals(silta.tuplaa(), false);
        assertEquals(silta.onTupla(), true);
    }
}
