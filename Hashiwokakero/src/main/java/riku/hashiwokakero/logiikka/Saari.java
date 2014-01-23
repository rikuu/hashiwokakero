package riku.hashiwokakero.logiikka;

public class Saari {
    private int vaaditutSillat;
    private int x, y;
    
    Saari(int sillat, int x, int y) {
        vaaditutSillat = sillat;
        
        this.x = x;
        this.y = y;
    }
    
    int getVaaditut() {
        return vaaditutSillat;
    }

}
