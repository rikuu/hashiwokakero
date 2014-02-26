package riku.hashiwokakero.logiikka;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import riku.hashiwokakero.domain.Saari;
import riku.hashiwokakero.domain.Silta;

public class RyhmitysTest {
    private SiltaKartta kartta;
    private Saari a, b, c, d;
    private ArrayList<ArrayList<Silta>> ryhmat;
    
    @Before
    public void setUp() {
        kartta = new SiltaKartta();
        
        a = new Saari(0, 0, 2);
        b = new Saari(2, 0, 2);
        c = new Saari(0, 2, 2);
        d = new Saari(2, 2, 2);
        
        kartta.lisaa(a, b);
        kartta.lisaa(a, c);
        kartta.lisaa(d, b);
        kartta.lisaa(d, c);
    }
    
    @Test
    public void ryhmiaYksiJosSaaretYhdistettyOikein() {
        ryhmat = kartta.getRyhmat();
        
        assertEquals(ryhmat.size(), 1);
    }
    
    @Test
    public void ryhmiaKaksiJosYhdistettyKahdeksi() {
        kartta = new SiltaKartta();
        
        kartta.lisaa(a, b);
        kartta.lisaa(d, c);
        
        ryhmat = kartta.getRyhmat();
        
        assertEquals(ryhmat.size(), 2);
    }
    
    @Test
    public void sisaltaaKaikkiSillat() {
        ryhmat = kartta.getRyhmat();        
        ArrayList<Silta> ryhma = ryhmat.get(0);
        
        int eriSiltoja = 0;
        for (Silta s1 : ryhma) {
            eriSiltoja++;
            
            for (Silta s2 : kartta.getSillat()) {
                if (s1 == s2) {
                    eriSiltoja--;
                }
            }
        }
        
        assertEquals(eriSiltoja, 0);
    }
    
    @Test
    public void tyhjentaaPaivitystenValissaLahdosta() {
        ryhmat = kartta.getRyhmat();
        kartta.poista(a, b);
       
        ryhmat = kartta.getRyhmat();
        assertEquals(ryhmat.size(), 1);
    }
}
