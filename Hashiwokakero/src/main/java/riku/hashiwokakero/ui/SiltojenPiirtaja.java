package riku.hashiwokakero.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import riku.hashiwokakero.domain.Silta;

import riku.hashiwokakero.logiikka.SiltaKartta;

/**
 * Piirtää sillat ruudulle
 */
public class SiltojenPiirtaja {
    /**
     * Nykyisen pelin SiltaKartta
     */
    private final SiltaKartta sillat;
    
    /**
     * Luo uuden SiltojenPiirtajan
     * @param s SiltaKartta nykyiselle pelille
     */
    public SiltojenPiirtaja(SiltaKartta s) {
        sillat = s;
    }
    
    /**
     * Piirtää kaikki sillat.
     * @param g2 Javan Graphics2D piirtämiseen
     * @param animaatioSiirtyma Kuinka paljon kuuluu kuvaa siirtää x-akselilla.
     */
    public void piirra(Graphics2D g2, int animaatioSiirtyma) {
        g2.setStroke(new BasicStroke(6));
        
        ArrayList<ArrayList<Silta>> ryhmat = sillat.getRyhmat();
        
        for (Silta s : sillat.getSillat()) {
            g2.setColor(Util.ryhmanVari(s, ryhmat));
            
            Point lahto = Util.ruudulle(s.lahto.x, s.lahto.y);
            Point loppu = Util.ruudulle(s.loppu.x, s.loppu.y);
            
            lahto.x += animaatioSiirtyma;
            loppu.x += animaatioSiirtyma;
            
            if (s.onTupla()) {
                int siirtymaX = 0, siirtymaY = 0;
                if (s.lahto.x == s.loppu.x) {
                    siirtymaX = 6;
                } else {
                    siirtymaY = 6;
                }

                g2.drawLine(lahto.x + siirtymaX, lahto.y + siirtymaY,
                        loppu.x + siirtymaX, loppu.y + siirtymaY);

                g2.drawLine(lahto.x - siirtymaX, lahto.y - siirtymaY,
                        loppu.x - siirtymaX, loppu.y - siirtymaY);
            } else {
                g2.drawLine(lahto.x, lahto.y, loppu.x, loppu.y);
            }
        }
    }
}
