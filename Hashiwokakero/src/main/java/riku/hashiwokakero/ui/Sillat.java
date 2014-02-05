package riku.hashiwokakero.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import riku.hashiwokakero.domain.Silta;

import riku.hashiwokakero.logiikka.SiltaKartta;

/**
 * Piirtää sillat ruudulle
 */
public class Sillat {
    private final SiltaKartta sillat;
    
    public Sillat(SiltaKartta s) {
        sillat = s;
    }
    
    public void piirra(Graphics2D g2) {
        // Onko varmasti looginen omana luokkana;
        // käyttää pelkästään yhtä funktiota.
        
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.white);
        
        for (Silta s : sillat.getSillat()) {
            int kx1 = (800 / 2) + (s.lahto.x * 36);
            int ky1 = (600 / 2) + (s.lahto.y * 36);
            
            int kx2 = (800 / 2) + (s.loppu.x * 36);
            int ky2 = (600 / 2) + (s.loppu.y * 36);
            
            if (s.onTupla()) {
                int offsetX, offsetY;
                if (s.lahto.x == s.loppu.x) {
                    offsetX = 6;
                    offsetY = 0;
                } else {
                    offsetX = 0;
                    offsetY = 6;
                }

                g2.drawLine(kx1 + offsetX, ky1 + offsetY,
                        kx2 + offsetX, ky2 + offsetY);

                g2.drawLine(kx1 - offsetX, ky1 - offsetY,
                        kx2 - offsetX, ky2 - offsetY);
            } else {
                g2.drawLine(kx1, ky1, kx2, ky2);
            }
        }
    }
}
