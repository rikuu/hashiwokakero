package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Kuuntelee hiiren tekemisiä ja sen mukaan joko rakentaa
 * tai purkaa siltoja.
 */
public class Rakentaja implements MouseListener, MouseMotionListener {
    private Peli peli;
    
    private boolean raahaus;
    private Point lahto, hiiri;

    public Rakentaja(Peli peli) {
        this.peli = peli;
        
        raahaus = false;
    }
    
    public void piirra(Graphics g2) {
        if (raahaus) {
            g2.setColor(Color.white);
            g2.drawLine(lahto.x, lahto.y, hiiri.x, hiiri.y);
        }
    }
   
    @Override
    public void mouseMoved(MouseEvent e) {
        if (raahaus) {
            hiiri = e.getPoint();
            
            ((JComponent) e.getSource()).repaint();
        }
    }
    
    private static Point ruudukkoon(Point p) {
        double dx = ((double) (p.x - 400)) / 36.0;
        double dy = ((double) (p.y - 300)) / 36.0;

        int x = (int) Math.round(dx);
        int y = (int) Math.round(dy);
        
        return new Point(x, y);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Point r = ruudukkoon(e.getPoint());
        
        if (raahaus) {
            raahaus = false;
            
            if (peli.onSaari(r.x, r.y)) {
                Point l = ruudukkoon(lahto);
                peli.uusiSilta(l.x, l.y, r.x, r.y);
            }
        } else {
            if (peli.onSaari(r.x, r.y)) {
                raahaus = true;
                lahto = hiiri = e.getPoint();
            } else {
                peli.poistaSilta(r.x, r.y);
            }
        }
        
        ((JComponent) e.getSource()).repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
