package riku.hashiwokakero.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import riku.hashiwokakero.logiikka.Peli;

/**
 * Luo ikkunan pelille ja tekee uuden pelin kun edellinen loppuu.
 */
public class Main implements Runnable, Peli.RatkaisuTapahtuma {
    private JFrame frame;
    private PeliLauta lauta;
    
    @Override
    public void run() {
        frame = new JFrame("Hashiwokakero");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Util.resx, Util.resy);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        
        lisaaSulkuKuuntelija();
        
        // On tää jossain mielessä tälleen ratkaistu
        lauta = null;
        peliRatkaistu();
    }
    
    /**
     * Lisaa ActionListenerin, joka sulkee ikkunan kun joku painaa Esciä
     */
    private void lisaaSulkuKuuntelija() {
        ActionListener sulkuKuuntelija = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                peliRatkaistu();
                //frame.dispatchEvent(new WindowEvent(
                //        frame, WindowEvent.WINDOW_CLOSING)); 
            }
        };

        frame.getRootPane().registerKeyboardAction(sulkuKuuntelija,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    private class Animoija implements ActionListener {
        private int i;
        private final PeliLauta vanhaLauta;
        
        public Animoija(PeliLauta uusLauta) {
            i = 0;
            this.vanhaLauta = uusLauta;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (i < 100) {
                vanhaLauta.repaint();                
            } else if (vanhaLauta != null) {
                lauta.repaint();
            }
            
            if (i >= 200) {
                if (vanhaLauta != null) {
                    frame.getContentPane().remove(vanhaLauta);
                }
                
                frame.getContentPane().invalidate();
                frame.getContentPane().validate();
            } else {
                i += 1;
                
                ((Timer)e.getSource()).restart();
            }
        }
    }
    
    @Override
    public void peliRatkaistu() {
        if (lauta != null) {
            lauta.animoiUlos();

            Timer timer = new Timer(1, new Animoija(lauta));
            timer.setRepeats(true);
            timer.start();
        }
        
        Peli peli = new Peli(10);
        peli.setTapahtuma(this);
        
        lauta = new PeliLauta(peli);
        lauta.animoiSisaan();
        
        frame.add(lauta);
        
        frame.getContentPane().invalidate();
        frame.getContentPane().validate();
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(main);
    }
}