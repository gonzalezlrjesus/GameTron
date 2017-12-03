/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author djmbdv
 */
public class Mapa {

    BufferedImage murosI[];
    BufferedImage aleatoriasI[];
    int dimx;
    int dimy;
    int matriz[][];
    int estelas[][];
    int aleatorios[][];
    int speed;
    
    public void borraEstela(int id){
        for (int j = 0; j < dimy; j++) {
            for (int i = 0; i < dimx; i++) {
                if(estelas[j][i] == id){
                    estelas[j][i] = 0;
                }
            }
        }
    }
    public void clearEstela(){
        for (int j = 0; j < dimy; j++) {
            for (int i = 0; i < dimx; i++) {
                estelas[j][i] = 0;
            }
        }
    }
    public Mapa() {
        murosI = new BufferedImage[16];
        aleatoriasI = new BufferedImage[5];
        try {
            for (int i = 0; i < 16; i++) {
                murosI[i] = ImageIO.read(getClass().getResource("/img/" + (i + 1) + ".png"));
            }

            for (int i = 1; i <= 5; i++) {
                aleatoriasI[i - 1] = ImageIO.read(getClass().getResource("/img/-" + i + ".png"));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
