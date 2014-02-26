package riku.hashiwokakero.logiikka;

import java.awt.Point;
import riku.hashiwokakero.domain.Saari;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    private Peli peli; 
    private Saari s1, s2;
    private Tapahtuma t;
    
    private final static Point
            max = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE),
            min = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    
    private class Tapahtuma implements Peli.RatkaisuTapahtuma {
        public boolean kutsuttu;
        
        public Tapahtuma() {
            kutsuttu = false;
        }
        
        @Override
        public void peliRatkaistu() {
            kutsuttu = true;
        }
    }

    @Before
    public void setUp() {
        peli = new Peli(2, max, min);
        
        t = new Tapahtuma();
        peli.setTapahtuma(t);
        
        s1 = peli.getSaaret().getSaaret().get(0);
        s2 = peli.getSaaret().getSaaret().get(1);
    }
   
    @Test
    public void nollaSaartaOnTyhja() {
        peli = new Peli(0, max, min);
        
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
        assertEquals(peli.onSaari(s1.x, s1.y), true);
    }
    
    @Test
    public void eiLoydaSiltaa() {
        peli = new Peli(1, max, min);
        
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
    
    @Test
    public void sillatEiNull() {
        assertNotNull(peli.getSillat());
    }
    
    @Test
    public void eiKutsuTapahtumaa() {
        peli.poistaSilta(0, 0);
        
        assertFalse(t.kutsuttu);
    }
    
    @Test
    public void uusiSiltaKutsuuTapahtuman() {
        if (s1.getVaaditutSillat() == 2) {
            peli.uusiSilta(s1.x, s1.y, s2.x, s2.y);
        }
        
        peli.uusiSilta(s1.x, s1.y, s2.x, s2.y);
                
        assertTrue(t.kutsuttu);
    }
    
    @Test
    public void poistaSiltaKutsuuTapahtuman() {
        SaariKartta saaret = new SaariKartta();
        
        saaret.lisaa(new Saari(2, 0, 1));
        saaret.lisaa(new Saari(0, 0, 1));        
        saaret.lisaa(new Saari(0, 2, 2));
        saaret.lisaa(new Saari(2, 2, 2));
        
        peli = new Peli(saaret);
        peli.setTapahtuma(t);
        
        peli.uusiSilta(2, 0, 0, 0);
        peli.uusiSilta(0, 0, 0, 2);
        peli.uusiSilta(0, 2, 2, 2);
        peli.uusiSilta(2, 2, 2, 0);
        
        assertFalse(t.kutsuttu);
        
        peli.poistaSilta(1, 0);
        
        assertTrue(t.kutsuttu);
    }
        
    @Test
    public void keskittaaSaaret() {
        // Etäisyyden nollasta (= keskellä ruudukkoa) neliö
        int etaisyys1 = s1.x*s1.x + s1.y*s1.y;
        int etaisyys2 = s2.x*s2.x + s2.y*s2.y;
        
        assertEquals(etaisyys1, etaisyys2);
    }
}
