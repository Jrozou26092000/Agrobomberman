/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Juanes
 */
public class Enemigo extends Personaje{
    
    private String orientacion;

    public Enemigo(String orientacion, double posicionX, double posicionY, 
            Image imagen, Shape torso) {
        super(posicionX, posicionY, imagen, torso);
        this.orientacion = orientacion;
    }

    public String getOrientacion() {
        return orientacion;
    }
}
