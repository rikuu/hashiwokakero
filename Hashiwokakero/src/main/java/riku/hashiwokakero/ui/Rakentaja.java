package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import riku.hashiwokakero.logiikka.Peli;
import riku.hashiwokakero.logiikka.Saari;

public class Rakentaja implements MouseListener, MouseMotionListener {
    private Peli peli;
    
    private boolean raahaus;
    private Saari lahto;
    private Point hiiri;

    public Rakentaja(Peli p) {
        peli = p;
        
        raahaus = false;
    }
    
    public void piirra(Graphics g2) {
        if (raahaus) {
            g2.setColor(Color.white);
            g2.drawLine(lahto.x, lahto.y, hiiri.x, hiiri.y);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Saari saari = peli.getSaari(e.getX(), e.getY());
        
        if (raahaus) {
            raahaus = false;
            
            if ((saari != null) && !peli.saariaValissa(lahto, saari)) {
                peli.uusiSilta(lahto, saari);
            }
        } else {
            lahto = saari;
            raahaus = (saari != null);
            hiiri = e.getPoint();
        }
        
        ((JComponent) e.getSource()).repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (raahaus) {
            hiiri = e.getPoint();
            
            ((JComponent) e.getSource()).repaint();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {        
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
