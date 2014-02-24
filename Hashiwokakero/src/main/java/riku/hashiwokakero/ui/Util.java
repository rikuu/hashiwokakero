package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Point;

/**
 * Jotain apumetodeja ui-luokille
 */
public class Util {
    /**
     * Ruudun X- ja Y-resoluutio
     */
    public static final int resoluutioX = 800, resoluutioY = 600;
    
    /**
     * Saaren sivun pituus
     */
    public static final int saarenKoko = 36;
    
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
        int rx = (resoluutioX / 2) + (x * saarenKoko);
        int ry = (resoluutioY / 2) + (y * saarenKoko);
        
        return new Point(rx, ry);
    }
    
    /**
     * Muuttaa koordinaatit pikseleistä ruudukkomuotoon
     * 
     * @param p
     * @return 
     */
    public static Point ruudukkoon(Point p) {
        double dx = (double) (p.x - (resoluutioX / 2)) / saarenKoko;
        double dy = (double) (p.y - (resoluutioY / 2)) / saarenKoko;

        int x = (int) Math.round(dx);
        int y = (int) Math.round(dy);
        
        return new Point(x, y);
    }
}
