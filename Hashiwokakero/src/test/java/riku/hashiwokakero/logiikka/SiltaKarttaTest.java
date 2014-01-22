package riku.hashiwokakero.logiikka;

import riku.hashiwokakero.logiikka.Saari;
import riku.hashiwokakero.logiikka.SiltaKartta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SiltaKarttaTest {
    private SiltaKartta kartta;
    private Saari a, b;
    
    @Before
    public void setUp() {
        kartta = new SiltaKartta();
        
        a = new Saari(0, 0, 0);
        b = new Saari(1, 1, 1);
    }

    @Test
    public void aluussaTyhja() {
        assertEquals(kartta.maara(), 0);
    }
    
    @Test
    public void lisaaYhden() {
        kartta.lisaa(a, b);
        assertEquals(kartta.maara(), 1);
    }
    
    @Test
    public void lisaaSamanKerran() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        assertEquals(kartta.maara(), 2);
    }
    
    @Test
    public void lisaaSamanKahdesti() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        assertEquals(kartta.lisaa(a, b), false);
        
        assertEquals(kartta.maara(), 2);
    }
    
    @Test
    public void poistaaYhden() {
        kartta.lisaa(a, b);
        
        kartta.poista(a, b);
        assertEquals(kartta.maara(), 0);
    }
    
    @Test
    public void poistaaTuplan() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        kartta.poista(a, b);
        assertEquals(kartta.maara(), 0);
    }
}
