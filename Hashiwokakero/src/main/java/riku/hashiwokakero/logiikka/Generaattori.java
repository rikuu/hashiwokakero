package riku.hashiwokakero.logiikka;

import java.awt.Point;

import riku.hashiwokakero.domain.Saari;

/**
 * Generoi pelikenttiä.
 */
public class Generaattori {
    /**
     * SaariKartta, johon saaret lisätään.
     */
    private final SaariKartta saaret;
    
    /**
     * Pienin ja suurin hyväksyttävä koordinaatti. Toimivat myös osittain, ts.
     * uusi koordinaatti hylätään, jos x- tai y-koordinaatti arvotussa menee
     * kumpi vaan yli maksimiKoordinaatin tai alle minimiKoordinaatin.
     */
    private final Point maksimiKoordinaatti, minimiKoordinaatti;
    
    /**
     * Luo uuden Generaattorin
     * @param saaret SaariKartta, johon saaret lisätään.
     * @param maksimiKoordinaatti Suurin hyväksyttävä koordinaatti
     * @param minimiKoordinaatti Pienin hyväksyttävä koordinaatti
     */
    public Generaattori(SaariKartta saaret, Point maksimiKoordinaatti,
            Point minimiKoordinaatti) {
        this.saaret = saaret;
        
        this.maksimiKoordinaatti = maksimiKoordinaatti;
        this.minimiKoordinaatti = minimiKoordinaatti;
    }
    
    /**
     * Arpoo paikan, joka on joko x-, tai y-akselin suhteen kohtisuorassa
     * annettuun saareen.
     * @param pohja Saari josta liikutaan
     * @return Piste ruudukolla
     */
    private Point arvoPaikka(Saari pohja) {
        Point paikka = new Point(pohja.x, pohja.y);

        int p = (Math.random() <= 0.5) ? -2 : 2;
        if (Math.random() <= 0.5) {
            paikka.x += p;
        } else {
            paikka.y += p;
        }
        
        if ((paikka.x < minimiKoordinaatti.x) ||
                (paikka.x > maksimiKoordinaatti.x) ||
                (paikka.y < minimiKoordinaatti.y) ||
                (paikka.y > maksimiKoordinaatti.y)) {
            return arvoPaikka(pohja);
        }
        
        return paikka;
    }
    
    /**
     * Arpoo luvun 1-2, 70% luvuista on 1
     * @return arvottu luku
     */
    private int arvoSiltojenMaara() {
        return (Math.random() >= 0.7) ? 2 : 1;
    }
    
    /**
     * Arpoo olemassa olevista saarista jonkun, jolle voi lisätä annetun
     * määrän siltoja
     * 
     * @param siltoja kuinka monta siltaa tarvii lisätä
     * @return Olemassa oleva Saari
     */
    private Saari valitsePohja(int siltoja) {
        int i = (int)(Math.random() * (saaret.getSaaret().size() - 1));
        while ((saaret.getSaaret().get(i).getVaaditutSillat() + siltoja) > 4) {
            i++;
            
            if (i == saaret.getSaaret().size())
                i = 0;
        }
        
        return saaret.getSaaret().get(i);
    }
    
    /*private void lisaaSiltoja(Saari uusiSaari) {
        for (Saari s : saaret.getSaaret()) {
            if (uusiSaari.getVaaditutSillat() >= 4) {
                return;
            }
            
            if (saaret.saariaValissa(s, uusiSaari) ||
                    ((s.x != uusiSaari.x) && (s.y != uusiSaari.y))) {
                continue;
            }
            
            int siltoja = arvoSiltojenMaara();
            if (((s.getVaaditutSillat() + siltoja) <= 4)
                    && (Math.random() <= 0.4)) {
                s.vaadiLisaa(siltoja);
                uusiSaari.vaadiLisaa(siltoja);
            }
        }
    }*/
    
    /**
     * Generoi uuden saaren
     * @return saari
     */
    public Saari uusiSaari() {
        if (saaret.getSaaret().isEmpty()) {
            return new Saari(0, 0, 0);
        }
        
        int siltoja = arvoSiltojenMaara();
        Saari pohja = valitsePohja(siltoja);
        Point paikka = arvoPaikka(pohja);
        Saari uusiSaari = new Saari(paikka.x, paikka.y, siltoja);
        
        if (saaret.getSaaret().contains(uusiSaari) ||
                saaret.saariaValissa(pohja, uusiSaari)) {
            return uusiSaari();
        }
        
        pohja.vaadiLisaa(siltoja);
        
        // lisaaSiltoja(uusiSaari);
        
        return uusiSaari;
    }
}
