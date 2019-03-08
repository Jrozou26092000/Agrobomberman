/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 * Clase encargada de gestionar la interfaz grafica que se muestra en pantalla y
 * controlar la funcionalidad del juego. 
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Nivel {
    private Scene escena;
    private Muro[][] muros;//Matriz de los muros tanto fijos como paredes de cada nivel.
    private ArrayList<Enemigo>enemigos;//Array de los enemigos.
    private Campesino campesino; //Personaje principal.
    private Contador contador;
    private GraphicsContext lapiz;
    private ArrayList<String> pulsacionTeclado = null;//Array para gestionar los eventos del teclado
    private int secuencia = 0;
    private int numero = 0;
    
    /**
     * Constructor de la clase encargado de inicializar los atributos. Además 
     * crea los muros fijos del nivel del juego, los enemigos y al campesino.
     * @param escena Se pasa por refeerencia la escena para poder controlar los
     * eventos del teclado con el array pulsacionTeclado.
     * @param posicionX Posición en X del campesino.
     * @param posicionY Posición en Y del campesino.
     * @param imgCampesino Ruta de la imagen del campsino.
     * @param imgEnemigos Ruta de la imagen de los enemigos.
     * @param lapiz 
     * @since AgroBomberman 1.0
     */
    public Nivel(Scene escena,int posicionX, int posicionY, String imgCampesino, String 
            imgEnemigos, GraphicsContext lapiz,String imgPared) {
        this.escena = escena;
        this.muros = new Muro[13][21];
        this.enemigos = new ArrayList<>();
        this.campesino = new Campesino(false, 1, 3, posicionX, posicionY, new Image(imgCampesino),null,
        lapiz,escena);
        this.contador = null;
        this.lapiz = lapiz;
        this.pulsacionTeclado = new ArrayList<>();
        
        //Se invoca el método para crear los muros tanto fijos como las paredes.
        crearMuros();
        //Se invoca el metodo para agragar las imagenes de las paredes del juego
        crearPared(imgPared);
        //Se crean los enemigos
        crearEnemigos(imgEnemigos);
        
        escena.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !pulsacionTeclado.contains(code) )
                        pulsacionTeclado.add( code );
                }
            });

        escena.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    pulsacionTeclado.remove( code );
                }
            });
    }
    public boolean Rectificar(Pared p){
        for (int i = 0; i < this.enemigos.size(); i++) {
            if((this.enemigos.get(i).getPosicionX()==p.getPosicionX())&&(
                    this.enemigos.get(i).getPosicionY()==p.getPosicionY())){
                return false;
            }
        }
        return true;
    }
    /**
     * Metodo encargado de crear el campesino para poder visuliozarlo en el escenario.
     */
    public void DibujarCampesino() {
        lapiz.drawImage(this.campesino.getImagen(), 0, 50 * this.secuencia, 50, 51,
                this.campesino.getPosicionX(), this.campesino.getPosicionY(),
                51, 50);
        //Se crea la forma del campesino para detectar colisiones
        Shape torsoCampesino = new Rectangle(this.campesino.getPosicionX(),this.campesino.getPosicionY(), 48, 48);
        this.campesino.setTorso(torsoCampesino);
    }
    /**
     * Metodo encargado de los eventos del teclado para poder mover al campesino.
     * @since AgroBomberman 1.0
     */
    public void moverCampesino(){
        //Eventos del teclado
        if (pulsacionTeclado.contains("LEFT"))
                   this.campesino.moverIzquierda();
                if (pulsacionTeclado.contains("RIGHT"))
                    this.campesino.moverDerecha();
                if (pulsacionTeclado.contains("UP"))
                    this.campesino.moverArriba();
                if (pulsacionTeclado.contains("DOWN"))
                    this.campesino.moverAbajo();
                
        //Se establecen las imagenes del personaje principal segun el sentido en el que se
        //mueva
        if(this.numero % 7 == 1){
                if(this.secuencia == 3){
                  this.secuencia = 0;
                }else{
                  this.secuencia++;
                }
        }
        if (pulsacionTeclado.contains("LEFT")){
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_izq.png"));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("RIGHT")) {
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_der.png"));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("UP")) {
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_arr.png"));
            DibujarCampesino();
        }else{
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_aba.png"));
            DibujarCampesino();
        }
                
         this.numero++;
    }
    /**
     * Metodo encargado de crear los enemigos buscando casillas vacias para 
     * establecer sus posiciones iniciales.
     * @param imgEnemigos Imagen de los enemigos.
     * @since AgroBomberman 1.0
     */
    public void crearEnemigos(String imgEnemigos){
        int c = 10;
        int x = 0;
        int y = 0; 
        boolean rectificarPos;
        while(c!=0){
            x = (int)Math.floor(Math.random()*(12-3+1)+3);
            y = (int)Math.floor(Math.random()*(20-3+1)+3);
            if(!this.muros[x][y].getClass().getName().equals("Funcional.MuroFijo")){
                Pared p = (Pared)this.muros[x][y];
                if(p.getImagen()==null){
                    rectificarPos = Rectificar(p);
                        if (rectificarPos) {
                            
                            Shape forma = new Rectangle(this.muros[x][y].getPosicionX()
                                    , this.muros[x][y].getPosicionY(), 50, 50);
                            Enemigo e = new Enemigo("Arriba", this.muros[x][y].getPosicionX(),
                            this.muros[x][y].getPosicionY(), new Image(imgEnemigos), forma,
                            this.lapiz);
                            this.enemigos.add(e);
                            c--;
                        }
                    }
            }
        }
    }

    
    /**
     * Metodo encargado de añadir enemigos.
     * @param e
     * @return Retorna un valor booleano que retctifica la añadición del enemigo.
     * @since AgroBomberman 1.0
     */
    public boolean addEnemigos(Enemigo e){
        return this.enemigos.add(e);
    }

    /**
     * Se crean los muros tanto fijos como las paredes.
     * @since AgroBomberman 1.0
     */
    public void crearMuros(){
        //Se crean los muros y paredes del juego.
        //Las paredes se inicializan con la imagen en null para despues cambiar el estado de 
        //null para que tengan imagen y así impedir el paso.
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if ((i==0)||(i==12)) {
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                }else if(i%2!=0){
                    if((j==0)||(j==20)){
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                    }else{
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new Pared(null, null, null, 50*j, 50*i, f);
                    }
                }else if(i%2==0){
                    if (j%2==0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                    }else if (j%2!=0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new Pared(null, null, null, 50*j, 50*i, f);
                    }
                }
            }
        }
    }
    public void DetectarColisionSuperiorCampesino(){
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionY();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                 this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                   if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                       if (this.pulsacionTeclado.contains("UP")) {
                           this.campesino.setPosicionY(detector + 1);
                       }else if (this.pulsacionTeclado.contains("DOWN")) {
                           this.campesino.setPosicionY(detector - 1);
                       }
                    }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if(p.getImagen()!=null){
                            if (this.pulsacionTeclado.contains("UP")) {
                                this.campesino.setPosicionY(detector + 1);
                            } else if (this.pulsacionTeclado.contains("DOWN")) {
                                this.campesino.setPosicionY(detector - 1);
                            }
                        }
                    } 
                }
            }
        
        }
    }
    public void DetectarColisionLadosCampesino(){
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionX();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                 this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                   if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                       if (this.pulsacionTeclado.contains("LEFT")) {
                           this.campesino.setPosicionX(detector + 1);
                       } else if (this.pulsacionTeclado.contains("RIGTH")) {
                           this.campesino.setPosicionX(detector - 1);
                       }
                    }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if(p.getImagen()!=null){
                             if (this.pulsacionTeclado.contains("LEFT")) {
                                this.campesino.setPosicionX(detector + 1);
                            } else if (this.pulsacionTeclado.contains("RIGTH")) {
                                this.campesino.setPosicionX(detector - 1);
                            }
                        }
                    } 
                }
           }
        }
    }
    /**
     * Metodo encargado de crear las paredes, asignarles una imagen de manera
     * aleatoria para que se visualice en el juego.
     * @param imgPared String de la ruta de la imagen de las paredes.
     * @since AgroBomberman 1.0
     */
    public void crearPared(String imgPared){
        Random aleatorio = new Random();
        int numero;
        Image img = new Image(imgPared);
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 21; j++) {
                if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                    if(!(((i==1)&&(j==1))||((i==1)&&(j==2))||((i==2)&&(j==1)))){
                        numero = aleatorio.nextInt(2);
                        if(numero % 2 == 0){
                            Pared p = (Pared) this.muros[i][j];
                            p.setImagen(img);
                            this.muros[i][j] = p;
                        }
                    }
                }
            }
        }
    }
    /**
     * Metodo encargado de dibujar las paredes en el juego.
     * @since AgroBomberman 1.0
     */
    public void dibujarParedes(){
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 21; j++) {
                if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                   Pared p = (Pared) this.muros[i][j];
                   lapiz.drawImage(p.getImagen(), p.getPosicionX(), p.getPosicionY());
                }
            }
        }
    }
    
    public void DibujarEnemigos(int num){
        Shape torsoEnemigo = new Rectangle(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50,50);
        this.enemigos.get(num).setTorso(torsoEnemigo);
        /*lapiz.strokeRect(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50, 50);*/
        lapiz.drawImage(this.enemigos.get(num).getImagen(),0, 50*this.secuencia, 50,51, 
                this.enemigos.get(num).getPosicionX(),this.enemigos.get(num).getPosicionY(),51,50);
        
    }
    
    public int RectificarMuros(int num){
    double pos_x1 = this.enemigos.get(num).getPosicionX()+50;
    double pos_y1 = this.enemigos.get(num).getPosicionY();
     if (pos_x1%10!=0) {
            pos_x1 = pos_x1/10;
            pos_x1 = Math.floor(pos_x1);
            pos_x1= pos_x1*10;
        }
    if (pos_y1 % 10 != 0) {
        pos_y1 = pos_y1 / 10;
        pos_y1 = Math.floor(pos_y1);
        pos_y1 = pos_y1 * 10;
    }
        
     double pos_x2 = this.enemigos.get(num).getPosicionX()-50;
     double pos_y2 = this.enemigos.get(num).getPosicionY();
     if (pos_x2%10!=0) {
            pos_x2 = pos_x2/10;
            pos_x2 = Math.floor(pos_x2);
            pos_x2= pos_x2*10;
        }
        if (pos_y2%10!=0) {
            pos_y2 = pos_y2/10;
            pos_y2 = Math.floor(pos_y2);
            pos_y2= pos_y2*10;
        }
        
     double pos_x3 = this.enemigos.get(num).getPosicionX();
     double pos_y3 = this.enemigos.get(num).getPosicionY()+50;
     if (pos_x3%10!=0) {
            pos_x3 = pos_x3/10;
            pos_x3 = Math.floor(pos_x3);
            pos_x3= pos_x3*10;
        }
        if (pos_y3%10!=0) {
            pos_y3 = pos_y3/10;
            pos_y3 = Math.floor(pos_y3);
            pos_y3= pos_y3*10;
        }
        
     double pos_x4 = this.enemigos.get(num).getPosicionX();
     double pos_y4 = this.enemigos.get(num).getPosicionY()-50;
     if (pos_x4%10!=0) {
            pos_x4 = pos_x4/10;
            pos_x4 = Math.floor(pos_x4);
            pos_x4= pos_x4*10;
        }
        if (pos_y4%10!=0) {
            pos_y4 = pos_y4/10;
            pos_y4 = Math.floor(pos_y4);
            pos_y4= pos_y4*10;
        }
        int rectificar=0;
        int X = 0;
        int Y = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                X = this.muros[i][j].getPosicionX();
                Y = this.muros[i][j].getPosicionY();
                if (this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")) {
                  if(((X==pos_x1)&&(Y==pos_y1))||((X==pos_x2)&&(Y==pos_y2))||((X==pos_x3)
                          &&(Y==pos_y3))||((X==pos_x4)&&(Y==pos_y4))){
                      rectificar++;
                  }
                }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                    Pared p = (Pared) this.muros[i][j];
                    if (p.getImagen()!=null) {
                        if(((X==pos_x1)&&(Y==pos_y1))||((X==pos_x2)&&(Y==pos_y2))||((X==pos_x3)
                          &&(Y==pos_y3))||((X==pos_x4)&&(Y==pos_y4))){
                            rectificar++;
                        }
                    }
                }
            }
        }
        System.out.println(rectificar);
        if (rectificar==4) {
            return 1;
        }
        return 2;
    }
    
    public boolean DireccionMover(int num){
        double x = this.enemigos.get(num).getPosicionX();
        double y = this.enemigos.get(num).getPosicionY();
        if (x%10!=0) {
            x = x/10;
            x = Math.floor(x);
            x= (int)x*10;
        }
        if (y%10!=0) {
            y = y/10;
            y = Math.floor(y);
            y= (int)y*10;
        }
        if(this.enemigos.get(num).getOrientacion().equals("Derecha")){
            x = x+50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Izquierda")) {
            x=x-50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Arriba")) {
            y= y-50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Abajo")) {
            y=y+50;
        }
        int verificar=0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
               if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                   if ((this.muros[i][j].getPosicionX()==x)&&
                        (this.muros[i][j].getPosicionY()==y)) {
                    return false;
                    }
               }else if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                   Pared p = (Pared)this.muros[i][j];
                   if (p.getImagen()!=null) {
                       if ((this.muros[i][j].getPosicionX()==x)&&
                        (this.muros[i][j].getPosicionY()==y)) {
                            return false;
                    }
                   }
               }
            }
        }
        return true;
    }
    public void moverEnemigos(int num){
        int verificar;
        boolean mover;
        if (this.enemigos.get(num).getOrientacion().equals("Derecha")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:          
                    mover = DireccionMover(num);
                    if (!mover) {
                        this.enemigos.get(num).setOrientacion("Arriba");
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                    }else{
                        this.enemigos.get(num).moverDerecha();
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Izquierda")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                        
                    mover = DireccionMover(num);
                    if (!mover) {
                        this.enemigos.get(num).setOrientacion("Abajo");
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                    }else{
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                        this.enemigos.get(num).moverIzquierda();
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Arriba")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                       
                    mover = DireccionMover(num);
                    if (!mover) {
                        this.enemigos.get(num).setOrientacion("Izquierda");
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                    }else{
                        this.enemigos.get(num).moverArriba();
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Abajo")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                        
                    mover = DireccionMover(num);
                    if (!mover) {
                        this.enemigos.get(num).setOrientacion("Derecha");
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                    }else{
                        this.enemigos.get(num).moverAbajo();
                        this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }
     
    }
}
