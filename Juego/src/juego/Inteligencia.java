/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djmbdv
 */
public class Inteligencia extends Controlador implements Runnable {
    boolean activo;
    Semaphore s;
    AEstrella algoritmo;
    public Inteligencia(Player p,Juego juego){
        activo = true;
        s = new Semaphore(0);
        algoritmo = new AEstrella(juego.mapa.matriz);
        this.juego = juego;
        this.selected = p;
        actual = p.getPoint();
        
    }
    public void activarInteligencia(){
        new Thread(this).start();
    }
    public void updateObjetivos(){
        algoritmo.objetivos.clear();
        for(int j = 0; j < juego.mapa.aleatorios.length;j++){
            for(int i = 0; i < juego.mapa.aleatorios[j].length;i++){
                if(juego.mapa.aleatorios[j][i] != 0 &&  juego.mapa.aleatorios[j][i] < 4)
                    algoritmo.objetivos.add(new Point(i,j));
            }
        }
    }
    public void updateMatriz(){
        for(NodoEstrella ner[]: algoritmo.matriz){
            for(NodoEstrella ne:ner){
                if(juego.mapa.estelas[ne.y][ne.x]!= 0 || juego.mapa.aleatorios[ne.y][ne.x]== 5 )ne.transitable=false;else ne.transitable=true;
                try{
                    ne.coste+=(0.02 * algoritmo.matriz[ne.y+1][ne.x].coste);
                    ne.coste+=(-0.04*((juego.mapa.estelas[ne.y+1][ne.x] != 0)?1:0));
                    ne.coste+=(0.03*((juego.mapa.aleatorios[ne.y+1][ne.x] != 0 && juego.mapa.aleatorios[ne.y+1][ne.x] != 5)?1:0));
                }catch(Exception e){}
                try{
                    ne.coste+=(0.02 * algoritmo.matriz[ne.y-1][ne.x].coste);
                    ne.coste+=(-0.04*((juego.mapa.estelas[ne.y-1][ne.x] != 0)?1:0));
                    ne.coste+=(0.03*((juego.mapa.aleatorios[ne.y-1][ne.x] != 0 && juego.mapa.aleatorios[ne.y-1][ne.x] != 5)?1:0));
                
                }catch(Exception e){}
                try{
                    ne.coste+=(0.02 * algoritmo.matriz[ne.y][ne.x+1].coste);
                    ne.coste+=(-0.04*((juego.mapa.estelas[ne.y][ne.x+1] != 0)?1:0));
                    ne.coste+=(0.03*((juego.mapa.aleatorios[ne.y][ne.x+1] != 0 && juego.mapa.aleatorios[ne.y-1][ne.x] != 5)?1:0));
                
                }catch(Exception e){}
                try{
                    ne.coste+=(0.02 * algoritmo.matriz[ne.y][ne.x-1].coste);
                    ne.coste+=(-0.04*((juego.mapa.estelas[ne.y][ne.x-1] != 0)?1:0));
                    ne.coste+=(0.03*((juego.mapa.aleatorios[ne.y][ne.x-1] != 0 && juego.mapa.aleatorios[ne.y-1][ne.x] != 5)?1:0));
                
                }catch(Exception e){}
            }
        }
    }
   
    public void decision(int dir){
        int k = dir;
        selected.setDireccion(dir);
        Direccion.println(dir);
    }
  /*  public void decision(NodoEstrella ne){
        if(ne == null){
            System.out.println("null");
            return;
        }
      //   System.out.println("[" + ne.x + "," +ne.y + "]");
        ;
        selected.setDireccion(algoritmo.getNodoByPoint(selected.getPoint()).direccionRelativa(ne));
    }*/
    Point actual;
     @Override
    public void run() {
        while(true){
            if(!activo)s.tryAcquire();
            if(selected.getPoint().distance(actual) == 0)continue;
       //   algoritmo.clean();
          //  NodoEstrella ne = algoritmo.marcar(selected.getPoint(),selected.direccion);
           // algoritmo.printMatriz();
            
          //  ne.run();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
            updateObjetivos();
            updateMatriz();
            if(selected.getPoint().x !=  actual.x ||  selected.getPoint().y !=  actual.y){
                decision(algoritmo.getMejor(selected.getPoint(), selected.direccion));
                actual = selected.getPoint();
            }
        //    selected.canRun = true;

        }
    }
    
    public void activar(){
        if(activo)return;
        activo = true;
        s.release();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
