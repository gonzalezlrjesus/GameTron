/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JPanel;

/**
 *
 * @author djmbdv
 */
public class Pantalla extends JPanel implements Runnable {

    Juego juego;

    public Pantalla(Juego juego) {
    //    this.setLayout(null);

        this.juego = juego;
    }

    @Override
    public void paint(Graphics g) {
       // this.getParent().repaint();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.red);
        if (juego.status == Juego.COMIENZO) {
            g.drawString("Seleccione un mapa para comenzar", this.getWidth() / 2 - 100, this.getHeight() / 2 - 10);
            return;
        }
        if (juego.status == juego.TERMINADO) {
            g.drawString("GAME OVER", this.getWidth() / 2 - 100, this.getHeight() / 2 - 10);
           if(juego.ganador == null){
                g.drawString("EMPATE!", this.getWidth() / 2 - 100, this.getHeight() / 2 +20);
           }else {
               g.setColor(Color.blue);
               g.drawString("Ganador: "+ juego.ganador.name, this.getWidth() / 2 - 100, this.getHeight() / 2 +20);
               g.drawImage(juego.ganador.player_U, this.getWidth() / 2 - 100, this.getHeight() / 2 +50, juego);
                
           }
           return;
        }
       g.setColor(Color.white);
        for (int j = 0; j < juego.mapa.dimy; j++) {
            for (int i = 0; i < juego.mapa.dimx; i++) {
                g.setColor(Color.black);
                if (juego.mapa.matriz[j][i] == 0) {
                   // g.fillRect(i * 32, j * 32, 32, 32);
                }else{
                    g.drawImage(juego.mapa.murosI[juego.mapa.matriz[j][i]-1], i*32, j*32, juego);
                }
                if (juego.mapa.aleatorios[j][i] != 0)g.drawImage(juego.mapa.aleatoriasI[juego.mapa.aleatorios[j][i]-1], i*32, j*32, juego);               
                if (juego.mapa.estelas[j][i] != 0){
                    g.setColor((juego.mapa.estelas[j][i] == juego.player1.id)?Color.red:Color.blue);
                    g.fillRect(i * 32, j * 32, 32, 32);
                } 
               // g.drawString(""+juego.mapa.matriz[j][i], i*32, j*32);
               
            }
        }
        g.setColor(Color.green);
        g.drawRect(1, 1, juego.mapa.dimx * 32 - 1, juego.mapa.dimy * 32 -1);
        
        juego.player1.drawPlayer((Graphics2D)g);
        juego.player2.drawPlayer((Graphics2D)g);
        
    }

    @Override
    public void run() {
        
        while(true){
            repaint();
     try {
            Thread.sleep(20);
            //Get database connection, delete unused data from DB
          
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }

}
