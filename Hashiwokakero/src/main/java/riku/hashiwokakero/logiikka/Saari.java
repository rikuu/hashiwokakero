package riku.hashiwokakero.logiikka;

public class Saari {
    public final int x, y;
    
    private int vaaditutSillat;
    private int sillat;
    
    public Saari(int x, int y, int siltoja) {
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
    }
        
    public int getSillat() {
        return sillat;
    }
    
    public int getVaaditutSillat() {
        return vaaditutSillat;
    }
    
    public void vaadiLisaa(int x) {
        vaaditutSillat += x;
    }
    
    public boolean tarpeeksiSiltoja() {
        return (vaaditutSillat == sillat);
    }
    
    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (!(obj instanceof Saari)))
            return false;
        else if (obj == this)
            return true;
        
        Saari saari = (Saari) obj;
        return ((saari.x == this.x) && (saari.y == this.y));
    }
}
