package riku.hashiwokakero.logiikka;

public class Generaattori {
    private SaariKartta saaret;
    
    public Generaattori(SaariKartta saaret) {
        this.saaret = saaret;
    }
    
    private int arvoEtaisyys() {
        return (Math.random() < 0.5) ? -2 : 2;
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
    
    public void uusiSaari() {
        if (saaret.getSaaret().isEmpty()) {
            saaret.lisaa(new Saari(0, 0, 0));
            return;
        }
        
        int px = 0, py = 0;
        if (Math.random() > 0.5) {
            px = arvoEtaisyys();
        } else {
            py = arvoEtaisyys();
        }
        
        int siltoja = arvoSiltojenMaara();
        
        Saari pohja = valitsePohja(siltoja);
        px += pohja.x;
        py += pohja.y;
        
        Saari uusSaari = new Saari(px, py, siltoja);
        
        if (saaret.getSaaret().contains(uusSaari) ||
                saaret.saariaValissa(pohja, uusSaari)) {
            uusiSaari();
            return;
        }
        
        pohja.vaadiLisaa(siltoja);
        
        saaret.lisaa(uusSaari);
    }
}
