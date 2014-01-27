package riku.hashiwokakero.ui;

import javax.swing.JFrame;

import riku.hashiwokakero.logiikka.Peli;
import riku.hashiwokakero.logiikka.Saari;

public class Main extends JFrame {    
    public Main() {
        Peli p = new Peli();
        
        Saari s1 = p.uusiSaari(400, 300, 1);
        Saari s2 = p.uusiSaari(300, 300, 3);
        Saari s3 = p.uusiSaari(300, 200, 2);
        Saari s4 = p.uusiSaari(300, 400, 1);
                
        add(new PeliLauta(p));
        
        setTitle("Hashiwokakero");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}