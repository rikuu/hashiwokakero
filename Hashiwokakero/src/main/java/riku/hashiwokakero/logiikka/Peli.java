package riku.hashiwokakero.logiikka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import riku.hashiwokakero.domain.Silta;
import riku.hashiwokakero.domain.Saari;

/**
 * Toimii jonkinlaisena vetoketjuna muille pelilogiikkaa hallitseville luokille.
 * Sisältää kokonaisena yhden täyden pelin.
 */
public class Peli {
    /**
     * SaariKartta kartoittamaan saaret
     */
    private final SaariKartta saaret;
    
    /**
     * SiltaKartta kartoittamaan sillat
     */
    private final SiltaKartta sillat;
    
    /**
     * Generaattori generoimaan saaria
     */
    private final Generaattori gen;
    
    /**
     * Interface, joka täytyy toteuttaa jos tahtoo että saa kutsun takaisin
     * kun peli on ratkaistu.
     */
    public interface RatkaisuTapahtuma {
        /**
        * Kutsutaan kun peli on ratkaistu
        */
        public void peliRatkaistu();
    }
    
    /**
     * Kutsutaan pelin ratkaisun yhteydessä, jos ei ole null
     */
    private RatkaisuTapahtuma tapahtuma;
    
    /**
     * Luo uuden pelin
     * 
     * @param maara Saarien määrä uudessa pelissä
     * @param max Maksimikoordinaatit Generaattorille
     * @param min Minimikoordinaatit Generaattorille
     */
    public Peli(int maara, Point max, Point min) {
        sillat = new SiltaKartta();
        saaret = new SaariKartta();
        
        gen = new Generaattori(saaret, max, min);
        
        uusiPeli(maara);
    }

    /**
     * Generoi saaret Generaattorilla
     * 
     * @param maara saarten maara
     */
    private void uusiPeli(int maara) {
        for (int i = 0; i < maara; i++)
            saaret.lisaa(gen.uusiSaari());
        
        saaret.keskitaSaaret();
    }
    
    public SiltaKartta getSillat() {
        return sillat;
    }
    
    public SaariKartta getSaaret() {
        return saaret;
    }

    /**
     * Asettaa ratkaisun yhteydessä tapahtuvan tapahtuma-kutsun "suunnan".
     * 
     * @param tapahtuma luokka, joka toteuttaa RatkaisuTapahtuma interfacen
     */
    public void setTapahtuma(RatkaisuTapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }
    
    /**
     * Ottaa selvää onko koordinaattien kohdalla saari.
     * 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return True, jos koordinaattien kohdalla on saari
     */
    public boolean onSaari(int x, int y) {
        return (saaret.getSaari(x, y) != null);
    }
    
    /**
     * Kutsuu RatkaisuTapahtuman jos ratkaistu.
     */
    private void tarkistaOnkoRatkaistu() {
        if ((tapahtuma != null) && saaret.ratkaistu() &&
                (sillat.getRyhmat().size() == 1)) {
            tapahtuma.peliRatkaistu();
        }
    }

    /**
     * Luo uuden sillan kahden saaren välille
     * 
     * @param ax Saaren A x-koordinaatti
     * @param ay Saaren A y-koordinaatti
     * @param bx Saaren B x-koordinaatti
     * @param by Saaren B y-koordinaatti
     */
    public void uusiSilta(int ax, int ay, int bx, int by) {
        Saari a = saaret.getSaari(ax, ay);
        Saari b = saaret.getSaari(bx, by);
        
        if ((a != null) && (b != null) && !saaret.saariaValissa(a, b)) {
            sillat.lisaa(a, b);
        }
        
        tarkistaOnkoRatkaistu();
    }
    
    /**
     * Poistaa koordinaattien alla olevan sillan
     * 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     */
    public void poistaSilta(int x, int y) {
        Silta silta = sillat.getSilta(x, y);
        
        if (silta != null) {
            sillat.poista(silta.lahto, silta.loppu);
        }
        
        tarkistaOnkoRatkaistu();
    }
}
