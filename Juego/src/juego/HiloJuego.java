/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djmbdv
 */
public class HiloJuego implements Runnable {
    Juego juego;
    public HiloJuego(Juego j){
        juego = j;
    }

    @Override
    public void run() {
         while(juego.status != Juego.TERMINADO){
            try {
                juego.tick();
                Thread.sleep((80-juego.speed)*15);
            } catch (InterruptedException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            } }
    }
}
