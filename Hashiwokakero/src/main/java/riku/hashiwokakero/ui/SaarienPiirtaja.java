package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import riku.hashiwokakero.domain.Saari;

import riku.hashiwokakero.logiikka.SaariKartta;

/**
 * Piirtää saaret ruudulle.
 */
public class SaarienPiirtaja {
    /**
     * Tarvitsee että tietää mitä piirtää
     */
    private final SaariKartta saaret;
    
    /**
     * Luo uuden SaarienPiirtajan
     * @param saaret Pelin käytössä oleva SaariKartta
     */
    public SaarienPiirtaja(SaariKartta saaret) {
        this.saaret = saaret;
    }
    
    /**
     * Puolet saaren koosta. 
     */
    private static final int puolSaari = Util.saarenKoko / 2;

    /**
     * Saaren takana olevan isomman saaren koko.
     */
    private static final int isompiSaari =
            (int) ((4.0 * Util.saarenKoko) / 3.0);
    
    /**
     * Puolet isomman saaren koosta.
     */
    private static final int puolikasIsompi = isompiSaari / 2;

    /**
     * Fontti numerojen piirtämiseen.
     */
    private static final Font fontti =
            new Font(Font.DIALOG, Font.PLAIN, puolSaari);
    
    /**
     * Piirtää kaikki saaret ruudulle. Joka saaren takana on isompi neliö,
     * joka on myös läpinäkyvämpi.
     * @param g2 Javan Graphics2D piirtämiseen
     * @param animaatioSiirtyma Kuinka paljon kuuluu kuvaa siirtää x-akselilla.
     */
    public void piirra(Graphics2D g2, int animaatioSiirtyma) {
        g2.setFont(fontti);
        
        for (Saari saari : saaret.getSaaret()) {
            Point ruutu = Util.ruudulle(saari.x, saari.y);
            ruutu.x += animaatioSiirtyma;

            g2.setColor(Util.puolitaAlfa(Util.getVari(saari.getSillat())));
            g2.fillRect(ruutu.x - puolikasIsompi, ruutu.y - puolikasIsompi,
                    isompiSaari, isompiSaari);

            g2.setColor(Util.getVari(saari.getVaaditutSillat()));
            g2.fillRect(ruutu.x - puolSaari, ruutu.y - puolSaari,
                    Util.saarenKoko, Util.saarenKoko);
            
            int siltojaLisaa = saari.getVaaditutSillat() - saari.getSillat();
            if (siltojaLisaa > 0) {
                g2.setColor(Util.puolitaAlfa(Color.BLACK));
                
                String numero = Integer.toString(siltojaLisaa);
                g2.drawChars(numero.toCharArray(), 0, numero.length(),
                        ruutu.x - (puolSaari / 4),
                        ruutu.y + (puolSaari / 4));
            }
        }
    }
}
