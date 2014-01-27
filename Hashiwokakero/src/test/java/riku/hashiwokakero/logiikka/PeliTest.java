package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    private Peli peli;
    
    @Before
    public void setUp() {
        peli = new Peli();
    }

    @Test
    public void tyhjaRatkaistu() {
        assertEquals(peli.ratkaistu(), true);
    }
    
    @Test
    public void yksiSaariRatkaistu() {
        peli.uusiSaari();
        assertEquals(peli.ratkaistu(), true);
    }
    
    @Test
    public void saariOnValissa() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        Saari s2 = peli.uusiSaari(200, 100, 2);
        Saari s3 = peli.uusiSaari(100, 100, 1);
        
        assertEquals(peli.saariaValissa(s1, s3), true);
    }
    
    @Test
    public void saariaEiValissa() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        Saari s2 = peli.uusiSaari(200, 200, 0);
        Saari s3 = peli.uusiSaari(100, 100, 1);
        
        assertEquals(peli.saariaValissa(s1, s3), false);
    }
    
    @Test
    public void saariLoytyyKeskella() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        
        assertEquals(peli.getSaari(300, 100), s1);
    }
    
    @Test
    public void saariLoytyyYlavasen() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        
        assertEquals(peli.getSaari(283, 83), s1);
    }
    
    @Test
    public void saariLoytyyAlaoikee() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        
        assertEquals(peli.getSaari(317, 117), s1);
    }
    
    @Test
    public void saariEiLoydy() {
        Saari s1 = peli.uusiSaari(300, 100, 1);
        
        assertEquals(peli.getSaari(100, 300), null);
    }
}
