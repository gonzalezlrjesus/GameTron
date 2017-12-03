/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import static juego.Direccion.ABAJO;
import static juego.Direccion.ARRIBA;
import static juego.Direccion.DERECHA;
import static juego.Direccion.IZQUIERDA;
/**
 *
 * @author Jesus
 */
public class AEstrella {
    NodoEstrella matriz[][];
    Point pos;
    List <Point> objetivos;
    public AEstrella(int matriz[][]){
        objetivos = new ArrayList();
        this.matriz =  new NodoEstrella[matriz.length][matriz[0].length];
        for(int j = 0; j < matriz.length; j++){
            for(int i = 0; i < matriz[j].length; i++){
               this.matriz[j][i] = new  NodoEstrella(i,j,matriz[j][i],null,objetivos);
            }
        }
    }
    public NodoEstrella getNodoByPoint(Point p){
        return matriz[p.y][p.x];
    }
    public int getMejor(Point p,int direccion){
        direccion+=4;
        float direccionesProbables[] = new float[3];
        float suma = 0, mayor;
        int dir = (direccion-1)%4;
        mayor = direccionesProbables[0] = scanDir(p,(direccion-1)%4);
         direccionesProbables[1] = scanDir(p,(direccion)%4);
          direccionesProbables[2] = scanDir(p,(direccion+1)%4);
          for(int i = 0; i <3; i++){
              System.out.print("[" + direccionesProbables[i] + "]");
              if(direccionesProbables[i] > mayor ){
                  mayor = direccionesProbables[i];
                  dir =(direccion+i-1 )%4 ;
                  
              }
          }
           System.out.print(dir+","+direccion);
          Direccion.println(dir);
        return dir;
    }
    public float scanDir(Point p,int direccion){
        float f;
        System.out.print("Buscar en direccion:");
        Direccion.println(direccion);
        try{
        if(direccion == Direccion.ARRIBA){
            NodoEstrella pe = matriz[p.y-1][p.x];
           if(pe.transitable) return matriz[p.y-1][p.x].coste;
        }
        if(direccion == Direccion.ABAJO){
            NodoEstrella pe = matriz[p.y+1][p.x];
           if(pe.transitable) return matriz[p.y+1][p.x].coste;
            
        }
        if(direccion == Direccion.IZQUIERDA){
            NodoEstrella pe = matriz[p.y][p.x-1];
            if(pe.transitable)    return matriz[p.y][p.x-1].coste;
        }
        if(direccion == Direccion.DERECHA){
            NodoEstrella pe = matriz[p.y][p.x+1];
            if(pe.transitable)return matriz[p.y][p.x+1].coste;
        }
            
        }catch(Exception e){}
        return -100000;
    }
    public NodoEstrella marcar(Point pos, int direccion){
        //System.out.println(pos);
        NodoEstrella start = matriz[pos.y][pos.x];
        
        start.marcaDir = direccion;
        Direccion.println(direccion);
        if(start.marcado || !start.transitable)return start;
        start.marcado = true;
        if(direccion == Direccion.IZQUIERDA){
               try{
                   start.hijo_a = matriz[pos.y ][pos.x-1];
                    if(!matriz[pos.y ][pos.x-1].marcado){
                        matriz[pos.y][pos.x - 1].padre = start;
                        marcar(matriz[pos.y][pos.x - 1].getPoint(),Direccion.IZQUIERDA);
                    }
               }catch(Exception e){};
               try{
                    
                   start.hijo_b = matriz[pos.y + 1 ][pos.x];
                    if(!matriz[pos.y + 1][pos.x].marcado){
                        matriz[pos.y + 1][pos.x ].padre = start;
                        marcar(matriz[pos.y+1][pos.x].getPoint(),Direccion.ABAJO);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_c = matriz[pos.y - 1 ][pos.x];
                   if(!matriz[pos.y - 1][pos.x].marcado){
                       matriz[pos.y - 1][pos.x].padre = start;
                       marcar(matriz[pos.y-1][pos.x].getPoint(),Direccion.ARRIBA);
                   }
               }catch(Exception e){};
         
       }
        if(direccion == Direccion.DERECHA){
               try{
                    if(!matriz[pos.y ][pos.x+1].marcado){
                        start.hijo_b = matriz[pos.y ][pos.x+1];
                        matriz[pos.y][pos.x + 1].padre = start;
                        marcar(matriz[pos.y][pos.x + 1].getPoint(),Direccion.DERECHA);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_a = matriz[pos.y + 1 ][pos.x];
                    if(!matriz[pos.y + 1][pos.x].marcado){
                        matriz[pos.y + 1][pos.x ].padre = start;
                        marcar(matriz[pos.y+1][pos.x ].getPoint(),Direccion.ABAJO);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_c = matriz[pos.y - 1 ][pos.x];
                   if(!matriz[pos.y - 1][pos.x].marcado){
                       matriz[pos.y - 1][pos.x].padre = start;
                       marcar(matriz[pos.y - 1][pos.x].getPoint(),Direccion.ARRIBA);
                   }
               }catch(Exception e){};
         
       }
         if(direccion == Direccion.ARRIBA){
               try{
                    if(!matriz[pos.y ][pos.x+1].marcado){
                        start.hijo_a = matriz[pos.y ][pos.x+1];
                        matriz[pos.y][pos.x + 1].padre = start;
                        marcar(matriz[pos.y][pos.x + 1].getPoint(),Direccion.DERECHA);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_b = matriz[pos.y][pos.x-1];
                    if(!matriz[pos.y][pos.x-1].marcado){
                        matriz[pos.y ][pos.x -1].padre = start;
                        marcar(matriz[pos.y][pos.x - 1].getPoint(),Direccion.IZQUIERDA);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_c = matriz[pos.y - 1 ][pos.x];
                   if(!matriz[pos.y - 1][pos.x].marcado){
                       matriz[pos.y - 1][pos.x].padre = start;
                       marcar(matriz[pos.y-1][pos.x].getPoint(),Direccion.ARRIBA);
                   }
               }catch(Exception e){};
         
       }
          if(direccion == Direccion.ABAJO){
               try{
                    if(!matriz[pos.y ][pos.x+1].marcado){
                        start.hijo_a = matriz[pos.y ][pos.x+1];
                        matriz[pos.y][pos.x + 1].padre = start;
                        marcar(matriz[pos.y][pos.x + 1].getPoint(),Direccion.DERECHA);
                    }
               }catch(Exception e){};
               try{
                   start.hijo_b = matriz[pos.y][pos.x-1];
                  if(!matriz[pos.y][pos.x-1].marcado){
                      matriz[pos.y ][pos.x -1].padre = start;
                      marcar(matriz[pos.y][pos.x - 1].getPoint(),Direccion.IZQUIERDA);
                  }
               }catch(Exception e){};
               try{
                   start.hijo_c = matriz[pos.y + 1 ][pos.x];
                   if(!matriz[pos.y + 1][pos.x].marcado){
                       matriz[pos.y + 1][pos.x].padre = start;
                       marcar(matriz[pos.y + 1][pos.x].getPoint(),Direccion.ABAJO);
                   }
               }catch(Exception e){};
              
         
       } return start;
    }

    void printMatriz() {
        int hijos = 0;
        System.out.println();
        for(NodoEstrella[] ner:matriz){
            for(NodoEstrella ne: ner){
               /* hijos = 0;
                hijos += (ne.hijo_a != null )?1:0;
                hijos += (ne.hijo_b != null )?1:0;
                hijos += (ne.hijo_c != null )?1:0;*/
                System.out.print("{"+ ne.coste+ "}");
            }
            System.out.println();
        }
    
    }
    void scanMatriz() {
        
        for(NodoEstrella[] ner:matriz){
            for(NodoEstrella ne: ner){
               /* hijos = 0;
                hijos += (ne.hijo_a != null )?1:0;
                hijos += (ne.hijo_b != null )?1:0;
                hijos += (ne.hijo_c != null )?1:0;*/
                System.out.print("{"+ ne.coste+ "}");
            }
            System.out.println();
        }
    
    }
    void clean() {
         for(NodoEstrella[] ner:matriz){
            for(NodoEstrella ne: ner){
               /* hijos = 0;
                hijos += (ne.hijo_a != null )?1:0;
                hijos += (ne.hijo_b != null )?1:0;
                hijos += (ne.hijo_c != null )?1:0;*/
                ne.coste=0;
            }
         
        }
    }
}
