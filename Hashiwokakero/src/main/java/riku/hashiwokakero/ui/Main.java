package riku.hashiwokakero.ui;

import javax.swing.JFrame;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Pelkästään luo ikkunan pelille.
 */
public class Main extends JFrame implements Peli.RatkaisuTapahtuma {
    private PeliLauta lauta;
    
    public Main() {
        lauta = null;
        
        // On tää jossain mielessä tässä vaiheessa ratkaistu
        peliRatkaistu();
        
        setTitle("Hashiwokakero");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Util.resx, Util.resy);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    @Override
    public void peliRatkaistu() {
        if (lauta != null)
            getContentPane().remove(lauta);
        
        Peli peli = new Peli(10);
        peli.setTapahtuma(this);
        
        lauta = new PeliLauta(peli);
        add(lauta);
        
        getContentPane().invalidate();
        getContentPane().validate();
    }
    
    public static void main(String[] args) {
        new Main();
    }
}