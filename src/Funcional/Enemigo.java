/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Juanes
 */
public class Enemigo extends Personaje{
    
    private String orientacion;
    private int secuencia =0;
    private int numero =0;
    
    public Enemigo(String orientacion, int posicionX, int posicionY, 
            Image imagen, Shape torso,GraphicsContext lapiz) {
        super(posicionX, posicionY, imagen, torso);
        this.orientacion = orientacion;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }
}
