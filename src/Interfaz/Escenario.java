/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Funcional.LoopJuego;
import com.sun.glass.ui.Size;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clase encargada de crear el escenario y el Canvas del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Escenario extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane layout  = new Pane();  
        Canvas canvas = new Canvas(1052,650);
        layout.getChildren().add(canvas);
        Scene escena = new Scene(layout,1052,650, Color.WHITESMOKE);
        
        
        GraphicsContext lapiz = canvas.getGraphicsContext2D();
        LoopJuego juego = new LoopJuego(escena, lapiz);
        juego.start();
        
        primaryStage.setScene(escena);
        primaryStage.setTitle("Ejemplo Escenario Interactivo");
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        try{
            Application.launch(args);
        }catch(IllegalArgumentException i){
            System.out.println("Error en la direccion de una imagen...");
        }catch (NullPointerException n){
            System.out.println("Error, se está apuntando a un objeto nulo...");
        }
    }
}
