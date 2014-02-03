package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaariKarttaTest {
    private SaariKartta saaret;
    
    @Before
    public void setUp() {
        saaret = new SaariKartta();
    }
    
    @Test
    public void tyhjaRatkaistu() {
        assertEquals(saaret.ratkaistu(), true);
    }
    
    @Test
    public void yksiSaariRatkaistu() {
        saaret.lisaa(new Saari(0, 0, 0));
        assertEquals(saaret.ratkaistu(), true);
    }
    
    @Test
    public void saariOnValissa() {
        Saari s1 = new Saari(-1, 0, 1);
        Saari s2 = new Saari(0, 0, 2);
        Saari s3 = new Saari(1, 0, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);

        assertEquals(saaret.saariaValissa(s1, s3), true);
    }
    
    @Test
    public void saariaEiValissa() {
        Saari s1 = new Saari(-1, 0, 1);
        Saari s2 = new Saari(0, 1, 2);
        Saari s3 = new Saari(1, 0, 1);
        
        saaret.lisaa(s1);
        saaret.lisaa(s2);
        saaret.lisaa(s3);
        
        assertEquals(saaret.saariaValissa(s1, s3), false);
    }
    
    @Test
    public void saariLoytyy() {
        Saari s1 = new Saari(0, 0, 0);
        saaret.lisaa(s1);
        
        assertEquals(saaret.getSaari(0, 0), s1);
    }
    
    @Test
    public void saariEiLoydy() {
        Saari s1 = new Saari(0, 0, 0);
        saaret.lisaa(s1);
        
        assertEquals(saaret.getSaari(1, 0), null);
    }
}
