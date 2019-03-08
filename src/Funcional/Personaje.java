/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase encargada de fijar las características generales de los personajes del 
 * juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Personaje {
    protected int posicionX;//posicion x del pesonaje
    protected int posicionY;//posicion y del pesonaje
    protected Image imagen;
    protected Shape torso;

    /**
     * Constructor de la clase encargado de inicializar los atributos
     * @param posicionX Posicion del personaje en el eje x.
     * @param posicionY Posicion del personaje en el eje y.
     * @param imagen Aspecto de cada personaje.
     * @param torso Forma de cada personaje para detectar colisiones.
     * @since AgroBomberman 1.0 
     */
    public Personaje(int posicionX, int posicionY, Image imagen,Shape torso) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.imagen = imagen;
        this.torso = torso;
    }

    public void moverDerecha() {
        this.posicionX= this.posicionX+1;
    }
    
    /**
     * Metodo encargado de cambiar la posicion en X (negativo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverIzquierda() {
        this.posicionX= this.posicionX-1;
    }

    /**
     * Metodo encargado de cambiar la posicion en Y (negativo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverArriba() {
        this.posicionY= this.posicionY-1;
    }
    
    /**
     * Metodo encargado de cambiar la posicion en Y (positivo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverAbajo() {
        this.posicionY= this.posicionY+1;
    }
    
    /**
     * Metodo que retorna la posicion X del personaje en el plano coordenado.
     * @return 
     * @since AgroBomberman 1.0
     */
    public int getPosicionX() {
        return posicionX;
    }
    
    /**
     * Metodo que cambia la posicion X del personaje en el plano coordenado.
     * @param posicionX 
     * @since AgroBomberman 1.0
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }
    
    /**
     * Metodo que retorna la posicion Y del personaje en el plano coordenado.
     * @return 
     * @since AgroBomberman 1.0
     */
    public int getPosicionY() {
        return posicionY;
    }
    
    /**
     * Metodo que cambia la posicion Y del personaje en el plano coordenado.
     * @param posicionY 
     * @since AgroBomberman 1.0
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
    
    /**
     * Metodo que retorna la imaen que tiene asignada cada personaje.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Image getImagen() {
        return imagen;
    }
    
    /**
     * Metodo que cambia la imagen que tiene asignada el personaje.
     * @param imagen 
     * @since AgroBomberman 1.0
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Shape getTorso() {
        return torso;
    }

    public void setTorso(Shape torso) {
        this.torso = torso;
    }
    
    
    
    
}
