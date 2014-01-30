package riku.hashiwokakero.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

import riku.hashiwokakero.logiikka.Peli;
import riku.hashiwokakero.logiikka.Saari;

public class PeliLauta extends JPanel {
    private Peli peli;
    
    private Sillat sillasto;
    private Rakentaja rakentaja;
    
    private static final Color[] VARIT = {
        new Color(0, 0, 0),
        
        new Color(70, 137, 102),
        new Color(255, 240, 165),
        new Color(255, 176, 59),
        new Color(182, 73, 38)
    };
    
    private Color puolitaAlfa(Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getGreen(),
                c.getAlpha() / 2);
    }
    
    public PeliLauta(Peli peli) {
        this.peli = peli;
        
        sillasto = new Sillat(peli.getSillat());
        
        rakentaja = new Rakentaja(peli);
        addMouseListener(rakentaja);
        addMouseMotionListener(rakentaja);
        
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        sillasto.piirra(g2);
        rakentaja.piirra(g2);
        
        for (Saari saari : peli.getSaaret()) {
            g2.setColor(puolitaAlfa(VARIT[saari.getSillat()]));
            g2.fillRect(saari.x - 24, saari.y - 24, 48, 48);
            
            g2.setColor(VARIT[saari.vaaditutSillat]);
            g2.fillRect(saari.x - 18, saari.y - 18, 36, 36);
        }
    }
}

