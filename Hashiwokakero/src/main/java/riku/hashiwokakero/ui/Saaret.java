package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

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

    public void piirra(Graphics2D g2) {        
        for (Saari saari : saaret.getSaaret()) {
            Point rsaari = Util.ruudulle(saari.x, saari.y);
            
            g2.setColor(Util.puolitaAlfa(VARIT[saari.getSillat()]));
            g2.fillRect(rsaari.x - 24, rsaari.y - 24, 48, 48);
            
            g2.setColor(VARIT[saari.getVaaditutSillat()]);
            g2.fillRect(rsaari.x - 18, rsaari.y - 18, 36, 36);
        }
    }
    
}
