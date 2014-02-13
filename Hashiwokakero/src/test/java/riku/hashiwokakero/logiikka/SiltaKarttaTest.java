package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import riku.hashiwokakero.domain.Saari;
import riku.hashiwokakero.domain.Silta;

public class SiltaKarttaTest {
    private SiltaKartta kartta;
    private Saari a, b, c;
    
    @Before
    public void setUp() {
        kartta = new SiltaKartta();
        
        a = new Saari(0, 0, 0);
        b = new Saari(2, 0, 0);
        c = new Saari(0, 2, 0);
    }

    private void saaretMaara(int maara) {
        assertEquals(a.getSillat(), maara);
        assertEquals(b.getSillat(), maara);
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
    
    @Test
    public void siltaItseensa() {
        kartta.lisaa(a, a);
        
        saaretMaara(0);
    }
    
    @Test
    public void huonoSilta() {
        kartta.lisaa(b, c);
        
        assertEquals(c.getSillat(), 0);
        saaretMaara(0);
    }
    
    @Test
    public void listaEiOleNull() {
        assertNotNull(kartta.getSillat());
    }
    
    @Test
    public void loytaaSillanXAkselilta() {
        kartta.lisaa(a, b);
        
        Silta s = kartta.getSilta(1, 0);
        assertEquals(s.lahto, a);
        assertEquals(s.loppu, b);
    }
    
    @Test
    public void loytaaSillanYAkselilta() {
        kartta.lisaa(a, c);
        
        Silta s = kartta.getSilta(0, 1);
        assertEquals(s.lahto, a);
        assertEquals(s.loppu, c);
    }
    
    @Test
    public void eiLoydaSiltaaXAkselilta() {
        kartta.lisaa(a, c);
        
        assertNull(kartta.getSilta(1, 0));
    }
    
    @Test
    public void eiLoydaSiltaaYAkselilta() {
        kartta.lisaa(a, b);
        
        assertNull(kartta.getSilta(0, 1));
    }
}
