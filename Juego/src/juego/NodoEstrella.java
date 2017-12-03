/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Jesus
 */
public class NodoEstrella extends Thread {
    int x;
    int y;
    boolean transitable;
    float coste;
    NodoEstrella hijo_a,hijo_b,hijo_c;
    NodoEstrella padre;
    Semaphore  s;
    int marcaDir;
    boolean marcado;
    List <Point> objetivos;
    public NodoEstrella(int x, int y, int tipo, NodoEstrella padre, List <Point> objetivos){
        this.x = x;
        this.y = y;
        transitable  = (tipo == 0);
        coste = 1;
        marcado = false;
        this.padre = padre;
        s = new Semaphore(-3);
        this.objetivos  = objetivos;
        
        if(!transitable)coste -=1;
        
        objetivos.forEach((p) -> {
            coste +=(10/p.distance(x,y));
        });
    }
    public Point getPoint(){
        return new Point(x,y);
    }
    public int getMejorDireccion(){
     /*   return (hijo_a == null)?
                    (hijo_b == null)?
                        ((hijo_c == null)?
                            (marcaDir+1)%4:
                            hijo_c.marcaDir)
                        :((hijo_b.coste >= hijo_c.coste)?
                            hijo_b.marcaDir:
                             hijo_c.marcaDir)
                    :(hijo_b == null)?
                        ((hijo_c == null)?
                            hijo_a.marcaDir:
                            (hijo_a.coste >= hijo_c.coste)?hijo_a.marcaDir:hijo_c.marcaDir)
                        :(hijo_c == null)?
                            ((hijo_a.coste >= hijo_b.coste)?hijo_a.marcaDir:hijo_b.marcaDir)
                            :((hijo_a.coste >= hijo_b.coste)?
                                ((hijo_a.coste >= hijo_c.coste)?hijo_a.marcaDir:hijo_c.marcaDir)
                                :((hijo_b.coste >= hijo_c.coste)?hijo_b.marcaDir:hijo_c.marcaDir));*/
     
            if(hijo_a == null){
                if(hijo_b == null){
                    if(hijo_c == null)return marcaDir;
                    else return hijo_c.marcaDir;
                }else{
                    if(hijo_c == null)return hijo_b.marcaDir;
                    else return (hijo_b.coste >= hijo_c.coste)?hijo_b.marcaDir:hijo_c.marcaDir;
                }
            }else { 
                if(hijo_b == null){
                    if(hijo_c == null)return hijo_a.marcaDir;
                    else return (hijo_a.coste >= hijo_c.coste)?hijo_a.marcaDir:hijo_c.marcaDir;
                }else{
                    if(hijo_a.coste >= hijo_c.coste){
                        if(hijo_a.coste >= hijo_b.coste){
                            return hijo_a.marcaDir;
                        }else return hijo_b.marcaDir;
                    }else if(hijo_c.coste >= hijo_b.coste){
                        return hijo_c.marcaDir;
                    }else return hijo_b.marcaDir;
                }
            }
            
           
    }
    
    public NodoEstrella getMejorDireccion2(){

            if(hijo_a == null){
                if(hijo_b == null){
                    if(hijo_c == null)return null;
                    else return hijo_c;
                }else{
                    if(hijo_c == null)return hijo_b;
                    else return (hijo_b.coste >= hijo_c.coste)?hijo_b:hijo_c;
                }
            }else { 
                if(hijo_b == null){
                    if(hijo_c == null)return hijo_a;
                    else return (hijo_a.coste >= hijo_c.coste)?hijo_a:hijo_c;
                }else{
                    if(hijo_a.coste >= hijo_b.coste){
                        if(hijo_c == null || hijo_a.coste >= hijo_c.coste){
                            return hijo_a;
                        }else return hijo_c;
                    }else if( hijo_c == null && hijo_c.coste >= hijo_b.coste){
                        return hijo_c;
                    }else return hijo_b;
                }
            }
            
           
    }
    public int direccionRelativa(NodoEstrella ne){
        if(ne.x > x)return Direccion.DERECHA;
        if(ne.x < x)return Direccion.IZQUIERDA;
        if(ne.y > y )return Direccion.ABAJO;
        if(ne.y < y)return Direccion.ARRIBA;
        return 0;
    }
    @Override 
    public void run() {
        if(!transitable){
            coste -= 1;
        }
        float c2 = coste;
        if(padre != null){
            padre.coste +=coste;
            padre.s.tryAcquire();
        }
        try{if(hijo_a != null)hijo_a.start();else {s.release();coste--;}}catch(Exception e){}
        try{if(hijo_b != null)hijo_b.start();else {s.release();coste--;}}catch(Exception e){}
        try{if(hijo_c != null) hijo_c.start();else {s.release();coste--;} }catch(Exception e){}
        s.tryAcquire();
           
        if(padre!= null)padre.coste += (coste - c2);
    }
    
}
