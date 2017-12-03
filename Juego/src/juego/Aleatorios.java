/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author djmbdv
 */
public class Aleatorios extends Thread{
    Mapa mapa;
    Juego juego;
    Queue<Point> cola;
    
    public Aleatorios(Juego juego){
        mapa = juego.mapa;
        this.juego = juego;
        cola = new LinkedList();
        for(int j = 0; j < mapa.dimy; j++)
            for(int i = 0;i < mapa.dimx; i++){
                if(mapa.aleatorios[j][i]!= 0){
                    cola.add(new Point(i,j));
                }
            }
    }
    
    @Override
    public void run(){
        while(true){
        Point p = new Point(((int) (Math.random() * (mapa.dimx -1))),((int) (Math.random() * (mapa.dimy-1))));
        if(mapa.matriz[p.y][p.x] == 0
                && mapa.aleatorios[p.y][p.x] == 0 && mapa.estelas[p.y][p.x] == 0
                && (int)(Math.random() * 2)  == 1
                && (juego.player1.x != p.x || juego.player1.y != p.y)
                 && (juego.player2.x != p.x || juego.player2.y != p.y)){
            cola.add(p);
            mapa.aleatorios[p.y][p.x] = (int)(Math.random() * 5 + 1);
        }
        if((int)(Math.random() * 2) == 1){
           Point p1 =  cola.poll();
           try{borraAleatorio(p1);}catch(Exception e){}
        }
        try {
            Thread.sleep(800);
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }
    public void borraAleatorio(Point p){
        mapa.aleatorios[p.y][p.x] = 0;
    }
}
