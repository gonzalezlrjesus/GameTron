package juego;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author djmbdv
 */
public class CargadorDeMapa{
    public static Mapa load( File f) {
        Mapa m = new Mapa();
        int k;
        try {
            Scanner s = new Scanner(f);
            m.dimx = s.nextInt();
            m.dimy = s.nextInt();
            m.speed = s.nextInt();
            m.estelas = new int[m.dimy][m.dimx];
            m.matriz = new int[m.dimy][m.dimx];
            m.aleatorios = new int[m.dimy][m.dimx];
            for(int j = 0; j < m.dimy; j++)
            for(int i = 0; i < m.dimx; i++){
                m.estelas[j][i] = 0;
                k = s.nextInt();
                if(k < 0){
                    m.matriz[j][i] = 0;
                    m.aleatorios[j][i] = -k;
                }else m.matriz[j][i] = k;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargadorDeMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
}
