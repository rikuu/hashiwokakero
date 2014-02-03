package riku.hashiwokakero.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    private Peli peli;
    
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
    
    @Test
    public void nollaSaartaOnTyhja() {
        peli = new Peli(0);
        
        assertEquals(peli.getSaaret().getSaaret().isEmpty(), true);
    }
    
    @Test
    public void luoOikeanMaaranSaaria() {
        peli = new Peli(2);
        
        assertEquals(peli.getSaaret().getSaaret().size(), 2);
    }    
}
