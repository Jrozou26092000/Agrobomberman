/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.shape.Shape;

/**
 * Clase que define los muros que pueden ser o muros fijos o paredes.
 * @@author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Muro {
    private int posicionX;
    private int posicionY;
    private Shape forma;

    public Muro(int posicionX, int posicionY, Shape forma) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.forma = forma;
    }

    public Shape getForma() {
        return forma;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
    
    
    
}
