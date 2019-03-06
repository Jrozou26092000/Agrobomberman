/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase que define las características de las paredes del juego. Las paredes
 * podrán impedir el paso de los elementos del juego pero también podrán ser 
 * destruidas.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Arias
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Pared extends Muro{
    private Image imagen;
    private ArrayList<PowerUp> power;
    private Puerta puerta;

    /**
     * Constructor de la clase encargado de inicializar los atributos.
     * @param imagen Aspecto de la pared dentro del juego.
     * @param power La pared podrá esconder un power up.
     * @param puerta La pared podrá esconder la puerta al siguiente nivel.
     * @param posicionX Posición en X de la pared.
     * @param psocionY Posición en Y de la  pared.
     * @param forma Silueta de la pared, usada con el fin de detectar choques.
     * @since AgroBomberman 1.0
     */
    public Pared(Image imagen, ArrayList<PowerUp> power, Puerta puerta, 
            int posicionX, int psocionY, Shape forma) {
        super(posicionX, psocionY, forma);
        this.imagen = imagen;
        this.power = power;
        this.puerta = puerta;
    }
    
    /**
     * Método encargado de retornar la imagen de la pared.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
}
