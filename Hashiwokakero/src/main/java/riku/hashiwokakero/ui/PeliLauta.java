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
    /**
     * SiltojenPiirtaja piirtamaan siltoja
     */
    private final SiltojenPiirtaja siltojenPiirtaja;
    
    /**
     * SaarienPiirtaja piirtamaan siltoja
     */
    private final SaarienPiirtaja saarienPiirtaja;
    
    /**
     * SiltojenRakentaja rakentamaan siltoja
     */
    private final SiltojenRakentaja siltojenRakentaja;
    
    /**
     * Animaatio PeliLaudan kiinnostaviin liikkeisiin.
     */
    private Animaatio animaatio;
    
    /**
     * Luo uuden PeliLaudan
     * @param peli peli, jolle lauta luodaan
     */
    public PeliLauta(Peli peli) {
        siltojenPiirtaja = new SiltojenPiirtaja(peli.getSillat());
        saarienPiirtaja = new SaarienPiirtaja(peli.getSaaret());
        
        siltojenRakentaja = new SiltojenRakentaja(peli);
        addMouseListener(siltojenRakentaja);
        addMouseMotionListener(siltojenRakentaja);
        
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
        
        int siirtyma = 0;
        if (animaatio != null) {
            animaatio.step();
            siirtyma = animaatio.getArvo();
        }
        
        siltojenPiirtaja.piirra(g2, siirtyma);
        siltojenRakentaja.piirra(g2);
        saarienPiirtaja.piirra(g2, siirtyma);
    }
}

