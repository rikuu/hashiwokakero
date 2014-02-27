package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import riku.hashiwokakero.domain.Saari;
import riku.hashiwokakero.domain.Silta;

import riku.hashiwokakero.logiikka.Peli;
import riku.hashiwokakero.logiikka.SiltaKartta;

/**
 * Kuuntelee hiiren tekemisiä ja sen mukaan joko rakentaa
 * tai purkaa siltoja.
 */
public class SiltojenRakentaja implements MouseListener, MouseMotionListener {
    /**
     * Nykyinen peli
     */
    private final Peli peli;
    
    /**
     * Onko lähtöpaikka uudelle sillalle on valittu.
     */
    private boolean raahaus;
    
    /**
     * Lahtopiste ja hiiren nykyinen paikka
     */
    private Point lahto, hiiri;

    /**
     * @param peli Nykyinen peli
     */
    public SiltojenRakentaja(Peli peli) {
        this.peli = peli;
        
        raahaus = false;
    }
    
    /**
     * Hakee sillan joka yhdistää saaren, johon rakennetaan siltaa ja ottaa sen
     * sillan ryhmän värin rakennettavan sillan väriksi.
     * @return ryhmän väri
     */
    private Color lahdonRyhmanVari() {
        Point ruudukko = Util.ruudukkoon(lahto);
        SiltaKartta sillat = peli.getSillat();
        Silta muuSilta = sillat.getSilta(ruudukko.x, ruudukko.y);
        return Util.ryhmanVari(muuSilta, sillat.getRyhmat());
    }
    
    /**
     * Piirtää viivan lähdöstä hiiren nykyiseen paikkaan.
     * @param g2 
     */
    public void piirra(Graphics2D g2) {
        if (raahaus) {
            g2.setColor(lahdonRyhmanVari());
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
    
    @Override
    public void mousePressed(MouseEvent e) {
        Point ruudukko = Util.ruudukkoon(e.getPoint());
        
        if (raahaus) {
            raahaus = false;
            
            if (peli.onSaari(ruudukko.x, ruudukko.y)) {
                Point l = Util.ruudukkoon(lahto);
                peli.uusiSilta(l.x, l.y, ruudukko.x, ruudukko.y);
            }
        } else {
            if (peli.onSaari(ruudukko.x, ruudukko.y)) {
                raahaus = true;
                
                Saari saari = peli.getSaaret().getSaari(ruudukko.x, ruudukko.y);
                lahto = Util.ruudulle(saari.x, saari.y);
                
                hiiri = e.getPoint();
            } else {
                peli.poistaSilta(ruudukko.x, ruudukko.y);
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
