/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;
import static juego.Direccion.ABAJO;
import static juego.Direccion.ARRIBA;
import static juego.Direccion.DERECHA;
import static juego.Direccion.IZQUIERDA;

public class Player {

    String name;
    String path;
    int id;
    int direccion;
    int xStart;
    int yStart;
    int x;
    int y;
    boolean canRun; //DIferente
    public int lives; //DIferente
    Juego juego;
    Image player_U, player_D, player_R, player_L;
    int dirStart;
    public Point getPoint(){
        return new Point(x,y);
    }
    public Player(String ruta, String nombre, int d, int id, int xini, int yini, Juego j) {
        path = ruta;
        xStart = x = xini;
        yStart = y = yini;
        dirStart = d;
        this.id = id;
        direccion = d;
        name = nombre;
        lives = 2;
        canRun = false;
        juego = j;
        try {
            player_U = ImageIO.read(getClass().getClassLoader().getResource((path + "/U.png")));
            player_D = ImageIO.read(getClass().getClassLoader().getResource((path + "/D.png")));
            player_L = ImageIO.read(getClass().getClassLoader().getResource(path + "/L.png"));
            player_R = ImageIO.read(getClass().getClassLoader().getResource(path + "/R.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setDireccion(int d) {
        if (this.direccion == Direccion.ABAJO && d == Direccion.ARRIBA
                || this.direccion == Direccion.ARRIBA && d == Direccion.ABAJO
                || direccion == Direccion.IZQUIERDA && d == Direccion.DERECHA
                || direccion == Direccion.DERECHA && d == Direccion.IZQUIERDA) {
            return;
        }
        direccion = d;
    }

    public void move() {
        if (!canRun) {
            return;
        }
        juego.mapa.estelas[y][x] = id;
        if (direccion == DERECHA && canRun) {

            x++;

        } else if (direccion == IZQUIERDA && canRun) {

            x--;

        } else if (direccion == ARRIBA && canRun) {

            y--;

        } else if (direccion == ABAJO && canRun) {

            y++;
        }
        verificar();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getCanRun() {
        return canRun;
    }

    public void setCanRun(boolean cr){
        canRun = cr;
         Player enemy = (juego.player1.equals(this)) ? juego.player2 : juego.player1 ;
        Controlador c = (juego.human.selected == this)?juego.computer:juego.human;
         if(c.getClass().equals(Inteligencia.class)){
             System.out.println("Activar inteligencia enemiga");
             enemy.canRun = true;
         }
    }
    public void verificar() {
        Player enemy = (juego.player1.equals(this)) ? juego.player2 : juego.player1 ;
        if (x < 0 || x >= juego.mapa.dimx
                || y < 0 || y >= juego.mapa.dimy
                || juego.mapa.matriz[y][x] != 0
                || juego.mapa.estelas[y][x] != 0
                || juego.mapa.aleatorios[y][x] == 5) {
            if (lives >= 1) {
                lives--;
                juego.mapa.borraEstela(id);
                resetPos();
            } else {

                juego.player1.canRun = false;
                juego.player2.canRun = false;
                juego.ganador = enemy;
                juego.status = Juego.TERMINADO;
            }
            return;
        }
        if(x == enemy.x && y == enemy.y){
            if (lives >= 1) {
                lives--;
                juego.mapa.clearEstela();
                enemy.lives--;
                enemy.resetPos();
                resetPos();
            } else {
                
                juego.player1.canRun = false;
                juego.player2.canRun = false;
                juego.ganador = (enemy.lives > 0)?enemy:null;
                juego.status = Juego.TERMINADO;
            }
            
        }
        
        switch(juego.mapa.aleatorios[y][x]){
            case 1:
                juego.mapa.borraEstela(enemy.id);
                break;
            case 2:
                juego.mapa.borraEstela(id);
                break;
            case 3:
                juego.speed-=2;
                break;
            case 4:
                juego.speed+=4;
                break;
        }
        juego.mapa.aleatorios[y][x] = 0;
    }

    public void resetPos() {
        canRun = false;
        direccion = dirStart;
        x = xStart;
        y = yStart;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getDirection() {
        return direccion;
    }

    public int getVidas() {
        return lives;
    }

    public void drawPlayer(Graphics2D gh) {

        if (direccion == ARRIBA) {
            gh.drawImage(player_U, getX() * 32, getY() * 32, null);
        } else if (direccion == ABAJO) {
            gh.drawImage(player_D, getX() * 32, getY() * 32, null);
        } else if (direccion == IZQUIERDA) {
            gh.drawImage(player_L, getX() * 32, getY() * 32, null);
        } else if (direccion == DERECHA) {
            gh.drawImage(player_R, getX() * 32, getY() * 32, null);
        }
        //  System.out.println(name + "->" + direccion);
    }
    //DIferente

}
