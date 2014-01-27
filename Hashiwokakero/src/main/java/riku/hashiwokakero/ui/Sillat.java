package riku.hashiwokakero.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import riku.hashiwokakero.logiikka.Silta;
import riku.hashiwokakero.logiikka.SiltaKartta;

public class Sillat {
    private final SiltaKartta sillat;
    
    public Sillat(SiltaKartta s) {
        sillat = s;
    }
    
    public void piirra(Graphics2D g2) {
        g2.setStroke(new BasicStroke(6)); 
        g2.setColor(Color.white);
        
        for (Silta s : sillat.getSillat()) {
            if (s.onTupla()) {
                int offsetX, offsetY;
                if (s.lahto.x == s.loppu.x) {
                    offsetX = 6;
                    offsetY = 0;
                } else {
                    offsetX = 0;
                    offsetY = 6;
                }

                g2.drawLine(s.lahto.x + offsetX,
                        s.lahto.y + offsetY,
                        s.loppu.x + offsetX,
                        s.loppu.y + offsetY);

                g2.drawLine(s.lahto.x - offsetX,
                        s.lahto.y - offsetY,
                        s.loppu.x - offsetX,
                        s.loppu.y - offsetY);
            } else {
                g2.drawLine(s.lahto.x, s.lahto.y,
                        s.loppu.x, s.loppu.y);
            }
        }
    }
}
