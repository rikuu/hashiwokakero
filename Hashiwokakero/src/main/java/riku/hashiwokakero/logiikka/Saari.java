package riku.hashiwokakero.logiikka;

public class Saari {
    public final int vaaditutSillat;
    public final int x, y;
    
    private int sillat;
    
    public Saari(int siltoja, int x, int y) {
        vaaditutSillat = siltoja;
        
        this.x = x;
        this.y = y;
        
        sillat = 0;
    }
    
    public void lisaaSilta() {
        sillat++;
    }
    
    public void poistaSilta() {
        sillat = Math.max(sillat - 1, 0);
        // if (sillat < 0) return false
    }
        
    public int getSillat() {
        return sillat;
    }
    
    public boolean tarpeeksiSiltoja() {
        return (vaaditutSillat == sillat);
    }
}
