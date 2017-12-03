/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.event.KeyEvent;

/**
 *
 * @author djmbdv
 */
public class Teclado2 extends Controlador  {
    public Teclado2(Player p, Juego g){
        this.selected = p;
        this.juego = g;
        
    }
  

    @Override
    public void keyTyped(KeyEvent e) {
      //  System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
     //     System.out.println(e);
       switch(e.getKeyCode()){
            
            case KeyEvent.VK_UP:
                selected.setDireccion(Direccion.ARRIBA);
                break;
            case KeyEvent.VK_DOWN:
                selected.setDireccion(Direccion.ABAJO);
                break;
            case KeyEvent.VK_LEFT:
                selected.setDireccion(Direccion.IZQUIERDA);
                break;
            case KeyEvent.VK_RIGHT:
                selected.setDireccion(Direccion.DERECHA);
            
        }
       switch(e.getKeyCode()){
            
            case KeyEvent.VK_UP:
                
            case KeyEvent.VK_DOWN:
                
            case KeyEvent.VK_LEFT:
            
            
            case KeyEvent.VK_RIGHT:
            selected.setCanRun( true);
            break;
        }
       
     }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}

