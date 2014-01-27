package riku.hashiwokakero.ui;

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
    private Saari raahausLahto;
    private Point hiiri;

    public Rakentaja(Peli p) {
        peli = p;
        
        raahaus = false;
    }
    
    public boolean raahaa() {
        return raahaus;
    }
    
    public Saari getLahto() {
        return raahausLahto;
    }
    
    public Point getPaikka() {
        return hiiri;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Saari saari = peli.getSaari(e.getX(), e.getY());
        
        if (raahaus) {
            raahaus = false;
            
            if ((saari != null) && !peli.saariaValissa(raahausLahto, saari)) {
                peli.getSillat().lisaa(raahausLahto, saari);
            }
        } else {
            raahausLahto = saari;
            raahaus = (saari != null);
            hiiri = e.getPoint();
        }
        
        JComponent s = (JComponent) e.getSource();
        s.repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (raahaus) {
            hiiri = e.getPoint();
            
            JComponent s = (JComponent) e.getSource();
            s.repaint();
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
