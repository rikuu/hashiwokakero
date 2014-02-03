package riku.hashiwokakero.ui;

import javax.swing.JFrame;

import riku.hashiwokakero.logiikka.Peli;
import riku.hashiwokakero.logiikka.Saari;

public class Main extends JFrame {    
    public Main() {
        Peli p = new Peli();
                
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