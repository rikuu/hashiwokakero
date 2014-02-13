package riku.hashiwokakero.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import riku.hashiwokakero.domain.Silta;

import riku.hashiwokakero.logiikka.SiltaKartta;

/**
 * Piirtää sillat ruudulle
 */
public class Sillat {
    /**
     * Nykyisen pelin SiltaKartta
     */
    private final SiltaKartta sillat;
    
    /**
     * @param s Siltakartta nykyiselle pelille
     */
    public Sillat(SiltaKartta s) {
        sillat = s;
    }
    
    /**
     * Piirtää kaikki sillat.
     * @param g2 
     */
    public void piirra(Graphics2D g2) {        
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.white);
        
        for (Silta s : sillat.getSillat()) {
            Point lahto = Util.ruudulle(s.lahto.x, s.lahto.y);
            Point loppu = Util.ruudulle(s.loppu.x, s.loppu.y);
            
            if (s.onTupla()) {
                // Piirtää kaksi siltaa vierekkäin, jos on tupla
                
                int offsetX, offsetY;
                if (s.lahto.x == s.loppu.x) {
                    offsetX = 6;
                    offsetY = 0;
                } else {
                    offsetX = 0;
                    offsetY = 6;
                }

                g2.drawLine(lahto.x + offsetX, lahto.y + offsetY,
                        loppu.x + offsetX, loppu.y + offsetY);

                g2.drawLine(lahto.x - offsetX, lahto.y - offsetY,
                        loppu.x - offsetX, loppu.y - offsetY);
            } else {
                g2.drawLine(lahto.x, lahto.y, loppu.x, loppu.y);
            }
        }
    }
}
