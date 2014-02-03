package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    private Peli peli;
    
    @Before
    public void setUp() {        
    }

    @Test
    public void tyhjaRatkaistu() {
        peli = new Peli(0);
        
        assertEquals(peli.ratkaistu(), true);
    }
    
    @Test
    public void kaksiSaartaRatkaistu() {
        peli = new Peli(2);
        
        assertEquals(peli.ratkaistu(), false);
    }
}
