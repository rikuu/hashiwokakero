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
        // peli.uusiSaari();
        assertEquals(peli.ratkaistu(), true);
    }
}
