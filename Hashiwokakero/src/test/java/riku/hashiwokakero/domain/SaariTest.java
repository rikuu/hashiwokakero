package riku.hashiwokakero.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaariTest {
    private Saari saari;
        
    @Before
    public void setUp() {
        saari = new Saari(0, 0, 0);
    }
    
    @Test
    public void eiSiltojaAlussa() {
        assertEquals(saari.getSillat(), 0);
    }
    
    @Test
    public void lisaaSillan() {
        saari.lisaaSilta();
        
        assertEquals(saari.getSillat(), 1);
    }
    
    @Test
    public void poistaaSillan() {
        saari.lisaaSilta();
        saari.poistaSilta();
        
        assertEquals(saari.getSillat(), 0);
    }
    
    @Test
    public void poistaaSillanVainKerran() {
        saari.lisaaSilta();
        
        saari.poistaSilta();
        saari.poistaSilta();
        
        assertEquals(saari.getSillat(), 0);
    }
    
    @Test
    public void vaatiiAlussaOikein() {
        assertEquals(saari.getVaaditutSillat(), 0);
    }
    
    @Test
    public void lisaaVaaditunSillan() {
        saari.vaadiLisaa(1);
        
        assertEquals(saari.getVaaditutSillat(), 1);
    }
    
    @Test
    public void samaSaari() {
        assertTrue(saari.equals(saari));
    }
    
    @Test
    public void eriSaari() {
        assertFalse(saari.equals(new Saari(1, 1, 0)));
    }
    
    @Test
    public void samaPaikkaEriSaari() {
        assertTrue(saari.equals(new Saari(0, 0, 0)));
    }
    
    @Test
    public void eiEdesSaari() {
        assertFalse(saari.equals(1));
    }
    
    @Test
    public void eiNegatiivisiaSiltaVaatimuksia() {
        Saari s = new Saari(0, 0, -1);
        assertEquals(s.getVaaditutSillat(), 0);
    }
}
