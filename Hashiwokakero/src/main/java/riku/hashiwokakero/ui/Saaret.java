package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import riku.hashiwokakero.domain.Saari;

import riku.hashiwokakero.logiikka.SaariKartta;

/**
 * Piirtää saaret ruudulle.
 */
public class Saaret {
    private final SaariKartta saaret;
    
    /**
     * @param saaret Pelin käytössä oleva SaariKartta
     */
    public Saaret(SaariKartta saaret) {
        this.saaret = saaret;
    }
    
    /**
     * Pari nättiä väriä saarten piirtämiseen.
     * Indeksi menee siltojen määrän mukaan.
     */
    private static final Color[] VARIT = {
        new Color(0, 0, 0),
        
        new Color(70, 137, 102),
        new Color(255, 240, 165),
        new Color(255, 176, 59),
        new Color(182, 73, 38),
        
        new Color(255, 255, 255),
        new Color(255, 255, 255),
        new Color(255, 255, 255),
        new Color(255, 255, 255)
    };

    private static final int puolSaari = Util.saarenKoko / 2;

    private static final int isompiSaari =
            (int) ((4.0 * Util.saarenKoko) / 3.0);
    private static final int puolIsompi = isompiSaari / 2;

    /**
     * Piirtää kaikki saaret ruudulle. Joka saaren takana on isompi neliö, joka
     * on myös läpinäkyvämpi.
     * @param g2 
     * @param animaatioOffset 
     */
    public void piirra(Graphics2D g2, int animaatioOffset) {
        for (Saari saari : saaret.getSaaret()) {
            Point rsaari = Util.ruudulle(saari.x, saari.y);

            g2.setColor(Util.puolitaAlfa(VARIT[saari.getSillat()]));
            g2.fillRect(rsaari.x - puolIsompi + animaatioOffset,
                    rsaari.y - puolIsompi,
                    isompiSaari, isompiSaari);

            g2.setColor(VARIT[saari.getVaaditutSillat()]);
            g2.fillRect(rsaari.x - puolSaari + animaatioOffset,
                    rsaari.y - puolSaari,
                    Util.saarenKoko, Util.saarenKoko);
        }
    }
    
    
}
