package riku.hashiwokakero.logiikka;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import riku.hashiwokakero.domain.Saari;

public class GeneraattoriTest {
    private SaariKartta saaret;
    private Generaattori gen;
    
    private final static Point
            max = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE),
            min = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
    
    @Before
    public void setUp() {
        saaret = new SaariKartta();
        
        gen = new Generaattori(saaret, max, min);
    }
    
    @Test
    public void ekaOn00() {
        Saari s = gen.uusiSaari();
        
        assertEquals(s.x, 0);
        assertEquals(s.y, 0);
    }
    
    @Test
    public void paikanEtaisyys2() {
        // Lisää saaren 0, 0
        saaret.lisaa(gen.uusiSaari());
        
        Saari s = gen.uusiSaari();
        if (s.x == 0) {
            assertEquals(Math.abs(s.y), 2);
        } else {
            assertEquals(Math.abs(s.x), 2);
        }
    }
    
    @Test
    public void siltojaYksiTaiKaksi() {
        saaret.lisaa(gen.uusiSaari());
        
        int l[] = {0, 0};
                
        for (int i = 0; i < 100; i++) {
            Saari s = gen.uusiSaari();
            saaret.lisaa(s);
            
            assertTrue((s.getVaaditutSillat() == 1) ||
                    (s.getVaaditutSillat() == 2));
            
            l[s.getVaaditutSillat() - 1]++;
        }
        
        // Pitäis olla noin 30% isompi
        assertTrue(l[0] > l[1]);
    }
    
    @Test
    public void lisaaPohjaanSillat() {
        Saari pohja = gen.uusiSaari();
        saaret.lisaa(pohja);
        
        saaret.lisaa(gen.uusiSaari());
        
        assertTrue(pohja.getVaaditutSillat() > 0);
    }
    
    @Test
    public void eiSaartaAlleMinimin() {
        gen = new Generaattori(saaret, max, new Point(0, 0));
        
        for (int i = 0; i < 10; i++) {
            Saari s = gen.uusiSaari();
            saaret.lisaa(s);
            
            assertTrue(s.x >= 0);
            assertTrue(s.y >= 0);
        }
    }
    
    @Test
    public void eiSaartaYliMaksimin() {
        gen = new Generaattori(saaret, new Point(0, 0), min);
        
        for (int i = 0; i < 10; i++) {
            Saari s = gen.uusiSaari();
            saaret.lisaa(s);
            
            assertTrue(s.x <= 0);
            assertTrue(s.y <= 0);
        }
    }
}
