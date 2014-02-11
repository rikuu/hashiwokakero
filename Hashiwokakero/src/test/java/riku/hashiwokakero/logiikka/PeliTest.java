package riku.hashiwokakero.logiikka;

import riku.hashiwokakero.domain.Saari;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    private Peli peli;    
    private Saari s1, s2;
    
    @Before
    public void setUp() {
        peli = new Peli(2);
        
        s1 = peli.getSaaret().getSaaret().get(0);
        s2 = peli.getSaaret().getSaaret().get(1);
    }
   
    @Test
    public void nollaSaartaOnTyhja() {
        peli = new Peli(0);
        
        assertEquals(peli.getSaaret().getSaaret().isEmpty(), true);
    }
    
    @Test
    public void luoOikeanMaaranSaaria() {
        assertEquals(peli.getSaaret().getSaaret().size(), 2);
    }
    
    @Test
    public void rakentaaSillan() {
        peli.uusiSilta(s1.x, s1.y, s2.x, s2.y);
        
        assertEquals(s1.getSillat(), 1);
    }
    
    @Test
    public void loytaaSillan() {
        assertEquals(peli.onSaari(0, 0), true);
    }
    
    @Test
    public void eiLoydaSiltaa() {
        peli = new Peli(1);
        
        assertEquals(peli.onSaari(1, 1), false);
    }
    
    @Test
    public void poistaaSillan() {
        peli.uusiSilta(s1.x, s1.y, s2.x, s2.y);
        
        if (s1.x == s2.x)
            peli.poistaSilta(s1.x, Math.min(s1.y, s2.y) + 1);
        else
            peli.poistaSilta(Math.min(s1.x, s2.x) + 1, s1.y);
        
        assertEquals(s1.getSillat(), 0);
    }
    
    @Test
    public void eiPoistaaSillan() {
        peli.uusiSilta(s1.x, s1.y, s2.x, s2.y);
        
        peli.poistaSilta(s1.x - 1, s1.y + 1);
        
        assertEquals(s1.getSillat(), 1);
    }
}
