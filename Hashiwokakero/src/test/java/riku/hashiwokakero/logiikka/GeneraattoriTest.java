package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import riku.hashiwokakero.domain.Saari;

public class GeneraattoriTest {
    private SaariKartta saaret;
    private Generaattori gen;
    
    @Before
    public void setUp() {
        saaret = new SaariKartta();
        gen = new Generaattori(saaret);
    }
    
    @Test
    public void ekaOn00() {
        Saari s = gen.uusiSaari();
        
        assertEquals(s.x, 0);
        assertEquals(s.y, 0);
    }
    
    @Test
    public void paikanEtaisyys2() {
        // Lisää saaren 0, 0
        saaret.lisaa(gen.uusiSaari());
        
        Saari s = gen.uusiSaari();
        if (s.x == 0) {
            assertEquals(Math.abs(s.y), 2);
        } else {
            assertEquals(Math.abs(s.x), 2);
        }
    }
}
