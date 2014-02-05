package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Point;

/**
 * Jotain apumetodeja ui-luokille
 */
public class Util {
    /**
     * Puolittaa annetun värin alfan, i.e. tekee siitä läpinäkyvämmän
     * 
     * @param vari Muutettava väri
     * @return Läpinäkymäpi väri
     */
    public static Color puolitaAlfa(Color vari) {
        return new Color(vari.getRed(), vari.getGreen(), vari.getBlue(),
                vari.getAlpha() / 2);
    }
    
    /**
     * Muuttaa koordinaatit ruudukosta pikselimuotoon
     * 
     * @param x
     * @param y
     * @return 
     */
    public static Point ruudulle(int x, int y) {
        // Ikkuna on 800x600
        // Saaret on 36x36
        
        int rx = (800 / 2) + (x * 36);
        int ry = (600 / 2) + (y * 36);
        
        return new Point(rx, ry);
    }
    
    /**
     * Muuttaa koordinaatit pikseleistä ruudukkomuotoon
     * 
     * @param p
     * @return 
     */
    public static Point ruudukkoon(Point p) {
        double dx = (double) (p.x - (800 / 2)) / 36.0;
        double dy = (double) (p.y - (600 / 2)) / 36.0;

        int x = (int) Math.round(dx);
        int y = (int) Math.round(dy);
        
        return new Point(x, y);
    }
}
