package riku.hashiwokakero.ui;

import javax.swing.JFrame;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Pelkästään luo ikkunan pelille.
 */
public class Main extends JFrame {    
    public Main() {
        Peli p = new Peli(10);
                
        add(new PeliLauta(p));
        
        setTitle("Hashiwokakero");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Util.resx, Util.resy);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}