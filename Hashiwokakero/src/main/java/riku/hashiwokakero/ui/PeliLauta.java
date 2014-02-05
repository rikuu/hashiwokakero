package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Käyttöliittyäpuolen vastakappale Peli-luokalle;
 * sitoo yhteen eri palaset.
 * @see Peli
 */
public class PeliLauta extends JPanel {
    private Sillat sillasto;
    private Saaret saaristo;    
    private Rakentaja rakentaja;
    
    public PeliLauta(Peli peli) {
        sillasto = new Sillat(peli.getSillat());
        saaristo = new Saaret(peli.getSaaret());
        
        rakentaja = new Rakentaja(peli);
        addMouseListener(rakentaja);
        addMouseMotionListener(rakentaja);
        
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        sillasto.piirra(g2);
        rakentaja.piirra(g2);
        saaristo.piirra(g2);
    }
}
