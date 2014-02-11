package riku.hashiwokakero.logiikka;

import java.awt.Point;
import riku.hashiwokakero.domain.Saari;

/**
 * Generoi pelikenttiä.
 */
public class Generaattori {
    private SaariKartta saaret;
    
    private SiltaKartta ratkaistu;
    
    // Pitää siirtää pois täältä, koskee pelkästään 800x600 ikkunaa
    private final static int reunax0 = -10, reunay0 = -8;
    private final static int reunax1 = 10, reunay1 = 8;
    
    public Generaattori(SaariKartta saaret) {
        this.saaret = saaret;
        
        ratkaistu = new SiltaKartta();
    }
    
    private Point arvoPaikka(Saari pohja) {
        Point paikka = new Point(pohja.x, pohja.y);

        int p = (Math.random() <= 0.5) ? -2 : 2;
        if (Math.random() <= 0.5) {
            paikka.x += p;
        } else {
            paikka.y += p;
        }
        
        // Huono, mahoton pelata, arvo uus
        if ((paikka.x <= reunax0) || (paikka.x >= reunax1) ||
            (paikka.y <= reunay0) || (paikka.y >= reunay1)) {
                return arvoPaikka(pohja);
        }
        
        return paikka;
    }
    
    private int arvoSiltojenMaara() {
        return (Math.random() > 0.7) ? 2 : 1;
    }
    
    private Saari valitsePohja(int siltoja) {
        int i = (int)(Math.random() * (saaret.getSaaret().size() - 1));
        while ((saaret.getSaaret().get(i).getVaaditutSillat() + siltoja) > 4) {
            i++;
            
            if (i == saaret.getSaaret().size())
                i = 0;
        }
        
        return saaret.getSaaret().get(i);
    }
    
    private void lisaaSiltoja(Saari uusiSaari) {
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
                    /*&& (Math.random() <= 0.4)*/) {
                s.vaadiLisaa(siltoja);
                uusiSaari.vaadiLisaa(siltoja);
            }
        }
    }
    
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
        
        //lisaaSiltoja(uusiSaari);
        
        return uusiSaari;
    }
}
