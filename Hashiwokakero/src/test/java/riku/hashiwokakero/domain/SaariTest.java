package riku.hashiwokakero.domain;

import riku.hashiwokakero.domain.Saari;
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
}
