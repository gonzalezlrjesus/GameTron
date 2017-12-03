/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author djmbdv
 */
public class Teclado extends Controlador {
    public Teclado(Player p, Juego g){
        this.selected = p;
        this.juego = g;    
    }
  

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
       System.out.println(e);
       switch(e.getKeyCode()){
            
            case KeyEvent.VK_W:
                selected.setDireccion(Direccion.ARRIBA);
                break;
            case KeyEvent.VK_S:
                selected.setDireccion(Direccion.ABAJO);
                break;
            case KeyEvent.VK_A:
                selected.setDireccion(Direccion.IZQUIERDA);
                break;
            case KeyEvent.VK_D:
                selected.setDireccion(Direccion.DERECHA);
                
        }
       switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                
            case KeyEvent.VK_S:
                
            case KeyEvent.VK_A:
                
            case KeyEvent.VK_D:
            selected.setCanRun( true);
            
        }
     }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
