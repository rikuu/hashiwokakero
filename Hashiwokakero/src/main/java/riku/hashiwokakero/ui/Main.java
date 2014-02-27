package riku.hashiwokakero.ui;

import java.awt.Point;
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
    /**
     * Swing käyttää JFramea hoitamaan asioita. Ei ole mitä haluan
     * henkilökohtaisesti tehdä, mutta en voi asialle mitään.
     */
    private JFrame frame;
    
    /**
     * Nykyinen (tai seuraava jos animaatio tapahtumassa) PeliLauta.
     */
    private PeliLauta lauta;
    
    /**
     * Saarien määrä seuraavassa pelissä on.
     */
    private int saaria = 3;
    
    /**
     * Maksimikoordinaatit pelin Generaattorille.
     */
    private final static Point max = Util.ruudukkoon(
            new Point(Util.resoluutioX, Util.resoluutioY));
    
    /**
     * Minimikoordinaatit pelin Generaattorille.
     */
    private final static Point min = Util.ruudukkoon(new Point(0, 0));
    
    @Override
    public void run() {
        frame = new JFrame("Hashiwokakero");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Util.resoluutioX, Util.resoluutioY);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        
        lisaaSulkuKuuntelija();
        
        uusiPeli();
    }
    
    /**
     * Luo uuden Pelin, PeliLaudan ja lisää sen ruudulle.
     */
    private void uusiPeli() {
        Peli peli = new Peli(saaria, max, min);
        peli.setTapahtuma(this);
        
        lauta = new PeliLauta(peli);
        frame.add(lauta);
        
        // Kuuluu kasvattaa saarien määrää mielyttävästi.
        // Tää on melko mielivaltanen päätös.
        // Silti mielyttävä.
        saaria = Math.min((int) Math.round(saaria * 1.3), 14);
    }
    
    /**
     * Lisaa ActionListenerin, joka sulkee ikkunan kun joku painaa Esciä
     */
    private void lisaaSulkuKuuntelija() {
        ActionListener sulkuKuuntelija = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(
                        frame, WindowEvent.WINDOW_CLOSING)); 
            }
        };

        frame.getRootPane().registerKeyboardAction(sulkuKuuntelija,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    /**
     * Pitää huolta että PeliLaudan animointi vie 100 freimiä molempiin
     * suuntiin ja sen jälkeen poistaa vanhan.
     */
    private class Animoija implements ActionListener {
        /**
         * Kertoo missä freimissä mennään
         */
        private int i;
        
        /**
         * Vanha PeliLauta
         */
        private final PeliLauta vanhaLauta;
       
        /**
         * Luo uuden Animoijan.
         * @param vanhaLauta edellinen PeliLauta
         */
        public Animoija(PeliLauta vanhaLauta) {
            i = 0;
            this.vanhaLauta = vanhaLauta;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (i < 200) {
                if (vanhaLauta != null) {
                    if (i < 100) {
                       vanhaLauta.repaint();
                    } else if (i == 100) {
                       frame.remove(vanhaLauta);

                       frame.invalidate();
                       frame.validate();
                   }
                }
                
                if (i > 100) {
                    lauta.repaint();
                }
                
                i += 1;
            } else {
                ((Timer) e.getSource()).stop();
            }
        }
    }
    
    @Override
    public void peliRatkaistu() {
        PeliLauta vanha = lauta;
        
        if (lauta != null) {
            lauta.animoiUlos();
        }
        
        uusiPeli();
        lauta.animoiSisaan();
        
        Timer timer = new Timer(10, new Animoija(vanha));
        timer.start();
    }
    
    /**
     * main-metodi mihin Java haluu tarttuu
     * @param args nää on mitä Java myös haluu
     */
    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(main);
    }
}