package riku.hashiwokakero.logiikka;

import riku.hashiwokakero.domain.Silta;
import riku.hashiwokakero.domain.Saari;

/**
 * Toimii jonkinlaisena vetoketjuna muille
 * pelilogiikkaa hallitseville luokille.
 */
public class Peli {
    private SaariKartta saaret;
    private SiltaKartta sillat;
    
    private Generaattori gen;
    
    public interface RatkaisuTapahtuma {
        public void peliRatkaistu();
    }
    
    /**
     * Kutsutaan kun peli on ratkaistu
     */
    private RatkaisuTapahtuma tapahtuma;
    
    /**
     * Luo uuden pelin
     * 
     * @param maara Saarien määrä uudessa pelissä
     */
    public Peli(int maara) {
        sillat = new SiltaKartta();
        saaret = new SaariKartta();
        
        gen = new Generaattori(saaret);
        
        uusiPeli(maara);
    }

    /**
     * Generoi saaret Generaattorilla
     * @param maara saarten maara
     */
    private void uusiPeli(int maara) {
        for (int i = 0; i < maara; i++)
            gen.uusiSaari();
    }
    
    public SiltaKartta getSillat() {
        return sillat;
    }
    
    public SaariKartta getSaaret() {
        return saaret;
    }

    /**
     * Asettaa ratkaisun yhteydessä tapahtuvan callback-kutsun luokan
     * 
     * @param tapahtuma tapahtuma-luokka
     */
    public void setTapahtuma(RatkaisuTapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }
    
    /**
     * Ottaa selvää onko koordinaattien kohdalla saari.
     * 
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return 
     */
    public boolean onSaari(int x, int y) {
        return (saaret.getSaari(x, y) != null);
    }
    
    /**
     * Kutsuu callbackiä, jos ratkaistu.
     */
    private void tarkistaOnkoRatkaistu() {
        if ((tapahtuma != null) && saaret.ratkaistu()) {
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
        
        if (!saaret.saariaValissa(a, b))
            sillat.lisaa(a, b);
        
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
