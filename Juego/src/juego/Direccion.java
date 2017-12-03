/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

/**
 *
 * @author djmbdv
 */
public  class Direccion {
    static int  IZQUIERDA = 0;
    static int ARRIBA = 1;
    static int DERECHA = 2;
    static int ABAJO = 3;
    public static void println(int i ){
        switch(i){
            case 0: System.out.println("IZQ");break;
            case 1: System.out.println("ARRI");break;
            case 2: System.out.println("DER");break;
            case 3: System.out.println("ABAJO");break;
        }
    }
}
