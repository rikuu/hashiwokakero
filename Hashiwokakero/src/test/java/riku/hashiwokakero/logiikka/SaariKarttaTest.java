package riku.hashiwokakero.logiikka;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import riku.hashiwokakero.domain.Saari;

public class SaariKarttaTest {
    private SaariKartta saaret;
    
    @Before
    public void setUp() {
        saaret = new SaariKartta();
    }
    
    @Test
    public void tyhjaRatkaistu() {
        assertTrue(saaret.ratkaistu());
    }
    
    @Test
    public void yksiSaariRatkaistu() {
        saaret.lisaa(new Saari(0, 0, 0));
        assertTrue(saaret.ratkaistu());
    }
    
    @Test
    public void kaksiSaartaEiRatkaistu() {
        saaret.lisaa(new Saari(0, 2, 1));
        saaret.lisaa(new Saari(0, 0, 1));
        
        assertFalse(saaret.ratkaistu());
    }
    
    @Test
    public void saariOnValissaYAkselilla() {
        Saari s1 = new Saari(-1, 0, 1);
        Saari s2 = new Saari(0,  0, 2);
        Saari s3 = new Saari(1,  0, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);

        assertTrue(saaret.saariaValissa(s1, s3));
    }
    
    @Test
    public void saariOnValissaXAkselilla() {
        Saari s1 = new Saari(0,  1, 1);
        Saari s2 = new Saari(0,  0, 2);
        Saari s3 = new Saari(0, -1, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);

        assertTrue(saaret.saariaValissa(s1, s3));
    }
    
    @Test
    public void saariaEiValissaYAkselilla() {
        Saari s1 = new Saari(-1, 0, 1);
        Saari s2 = new Saari(0,  1, 2);
        Saari s3 = new Saari(1,  0, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);
        
        assertFalse(saaret.saariaValissa(s1, s3));
    }
    
    @Test
    public void saariaEiValissaXAkselilla() {
        Saari s1 = new Saari(0,  1, 1);
        Saari s2 = new Saari(1,  1, 2);
        Saari s3 = new Saari(0, -1, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);
        
        assertFalse(saaret.saariaValissa(s1, s3));
    }
    
    @Test
    public void saariaEiValissaJosHuonotSaaret() {
        Saari s1 = new Saari(-1, 0, 1);
        Saari s2 = new Saari(0,  0, 2);
        Saari s3 = new Saari(1,  1, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);
        
        assertFalse(saaret.saariaValissa(s1, s3));
    }
    
    @Test
    public void saariLoytyy() {
        Saari s1 = new Saari(0, 0, 0);
        saaret.lisaa(s1);
        
        assertEquals(saaret.getSaari(0, 0), s1);
    }
    
    @Test
    public void saariEiLoydy() {
        saaret.lisaa(new Saari(0, 0, 0));
        
        assertNull(saaret.getSaari(1, 0));
    }
    
    @Test
    public void keskittaaYhdenSaaren() {
        saaret.lisaa(new Saari(0, 0, 0));        
        saaret.keskitaSaaret();
        
        Saari s = saaret.getSaaret().get(0);
        
        assertEquals(s.x, 0);
        assertEquals(s.y, 0);
    }
    
    @Test
    public void keskittaaKaksiSaarta() {
        saaret.lisaa(new Saari(0, 0, 1));
        saaret.lisaa(new Saari(2, 2, 1));
        
        saaret.keskitaSaaret();
        
        Saari s1 = saaret.getSaaret().get(0);
        Saari s2 = saaret.getSaaret().get(1);
        
        assertEquals(s1.x, -1);
        assertEquals(s1.y, -1);
        
        assertEquals(s2.x, 1);
        assertEquals(s2.y, 1);
    }
    
    @Test
    public void laskeeKeskipisteenOikein() {
        saaret.lisaa(new Saari(-1, 0, 1));
        saaret.lisaa(new Saari(2, -2, 1));
        
        Point keski = saaret.laskeKeskipiste();
        assertEquals(keski.x, 0);
        assertEquals(keski.y, -1);
    }
}
