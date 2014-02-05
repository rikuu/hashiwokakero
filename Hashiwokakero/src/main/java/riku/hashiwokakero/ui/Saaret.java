package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics2D;

import riku.hashiwokakero.domain.Saari;
import riku.hashiwokakero.logiikka.SaariKartta;

/**
 * Piirtää saaret ruudulle
 */
public class Saaret {
    private SaariKartta saaret;

    public Saaret(SaariKartta saaret) {
        this.saaret = saaret;
    }
    
    private static final Color[] VARIT = {
        new Color(0, 0, 0),
        
        new Color(70, 137, 102),
        new Color(255, 240, 165),
        new Color(255, 176, 59),
        new Color(182, 73, 38),
        
        new Color(255, 255, 255),
        new Color(255, 255, 255),
        new Color(255, 255, 255)
    };
    
    private Color puolitaAlfa(Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getGreen(),
                c.getAlpha() / 2);
    }
    
    public void piirra(Graphics2D g2) {        
        for (Saari saari : saaret.getSaaret()) {
            int keskix = (800 / 2) + (saari.x * 36) - (36 / 2);
            int keskiy = (600 / 2) + (saari.y * 36) - (36 / 2);
            
            g2.setColor(puolitaAlfa(VARIT[saari.getSillat()]));
            g2.fillRect(keskix - 6, keskiy - 6, 48, 48);
            
            g2.setColor(VARIT[saari.getVaaditutSillat()]);
            g2.fillRect(keskix, keskiy, 36, 36);
        }
    }
    
}
