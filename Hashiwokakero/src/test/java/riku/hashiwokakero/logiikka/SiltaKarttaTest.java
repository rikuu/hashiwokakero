package riku.hashiwokakero.logiikka;

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
        b = new Saari(1, 0, 0);
    }

    private void saaretMaara(int maara) {
        assertEquals(kartta.maara(a), maara);
        assertEquals(kartta.maara(b), maara);
    } 
    
    @Test
    public void aluussaTyhja() {
        saaretMaara(0);
    }
    
    @Test
    public void lisaaYhden() {
        kartta.lisaa(a, b);
        
        saaretMaara(1);
    }
    
    @Test
    public void lisaaSamanKerran() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        saaretMaara(2);
    }
    
    @Test
    public void lisaaSamanKahdesti() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        saaretMaara(2);
    }
    
    @Test
    public void poistaaYhden() {
        kartta.lisaa(a, b);        
        kartta.poista(a, b);
        
        saaretMaara(0);
    }
    
    @Test
    public void poistaaTuplan() {
        kartta.lisaa(a, b);
        kartta.lisaa(a, b);
        
        kartta.poista(a, b);
        saaretMaara(0);
    }
}
