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
    private final Sillat sillasto;
    private final Saaret saaristo;
    private final Rakentaja rakentaja;
    
    /**
     * Animaatio PeliLaudan kiinnostaviin liikkeisiin.
     */
    private Animaatio animaatio;
    
    /**
     * Luo uuden PeliLaudan
     * @param peli peli, jolle lauta luodaan
     */
    public PeliLauta(Peli peli) {
        sillasto = new Sillat(peli.getSillat());
        saaristo = new Saaret(peli.getSaaret());
        
        rakentaja = new Rakentaja(peli);
        addMouseListener(rakentaja);
        addMouseMotionListener(rakentaja);
        
        setBackground(Color.black);
        setDoubleBuffered(true);
    }
    
    /**
     * Animoi x-paikkaa oikealta keskelle.
     */
    public void animoiSisaan() {
        animaatio = new Animaatio(Util.resoluutioX, 0);
    }
    
    /**
     * Animoi x-paikan keskeltä vasemmalta ulos.
     */
    public void animoiUlos() {
        animaatio = new Animaatio(0, -Util.resoluutioX);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int offset = 0;
        if (animaatio != null) {
            animaatio.step();
            offset = animaatio.getOffset();
        }
        
        sillasto.piirra(g2, offset);
        rakentaja.piirra(g2);
        saaristo.piirra(g2, offset);
    }
}

