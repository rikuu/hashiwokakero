package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

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
     * Piirtää kaikki saaret ruudulle. Joka saaren takana on isompi neliö, joka
     * on myös läpinäkyvämpi.
     * @param g2 Javan Graphics2D piirtämiseen
     * @param animaatioSiirtyma Kuinka paljon kuuluu kuvaa siirtää x-akselilla.
     */
    public void piirra(Graphics2D g2, int animaatioSiirtyma) {
        for (Saari saari : saaret.getSaaret()) {
            Point ruutu = Util.ruudulle(saari.x, saari.y);
            ruutu.x += animaatioSiirtyma;

            g2.setColor(Util.puolitaAlfa(Util.vari(saari.getSillat())));
            g2.fillRect(ruutu.x - puolikasIsompi, ruutu.y - puolikasIsompi,
                    isompiSaari, isompiSaari);

            g2.setColor(Util.vari(saari.getVaaditutSillat()));
            g2.fillRect(ruutu.x - puolSaari, ruutu.y - puolSaari,
                    Util.saarenKoko, Util.saarenKoko);
            
            int siltojaLisaa = saari.getVaaditutSillat() - saari.getSillat();
            if (siltojaLisaa > 0) {
                g2.setColor(Util.puolitaAlfa(Color.BLACK));
                
                Font fontti = new Font(Font.MONOSPACED, Font.BOLD, puolSaari);
                
                GlyphVector glyph =
                        fontti.createGlyphVector(g2.getFontRenderContext(),
                                Integer.toString(siltojaLisaa));
                
                Rectangle2D asd = glyph.getVisualBounds();                
                
                g2.drawGlyphVector(glyph,
                        (float) (ruutu.x - (asd.getMaxX() - asd.getMinX()) / 2),
                        (float) (ruutu.y + (asd.getMaxY() - asd.getMinY()) / 2));
            }
        }
    }
}
