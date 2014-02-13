package riku.hashiwokakero.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Pelkästään luo ikkunan pelille.
 */
public class Main implements Runnable, Peli.RatkaisuTapahtuma {
    private JFrame frame;
    private PeliLauta lauta;
    
    public Main() {
        lauta = null;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Hashiwokakero");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Util.resx, Util.resy);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
       
        // On tää jossain mielessä tässä vaiheessa ratkaistu
        peliRatkaistu();
    }
    
    @Override
    public void peliRatkaistu() {
        if (lauta != null)
            frame.getContentPane().remove(lauta);
        
        Peli peli = new Peli(10);
        peli.setTapahtuma(this);
        
        lauta = new PeliLauta(peli);
        frame.add(lauta);
        
        frame.getContentPane().invalidate();
        frame.getContentPane().validate();
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(main);
    }
}