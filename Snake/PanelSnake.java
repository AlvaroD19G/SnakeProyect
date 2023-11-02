/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Álvaro Álvarez R
 */
public class PanelSnake extends JPanel{
    
    private Color colorSnake= Color.green;
    private Color colorComida= Color.red;
    private int tammax, tam, can;
    private ArrayList<int[]> snake;
    private int[] comida = new int[2];
    private String direccion, direccionProxima;
    private Thread hilo;
    private Caminante camino;

    public PanelSnake(int tammax, int can) {
        snake = new ArrayList();
        this.tammax = tammax;
        this.can = can;
        this.tam=tammax/can;
        int[] a={this.can/2-4,this.can/2-1};
        int[] b={this.can/2-3,this.can/2-1};
        int[] c={this.can/2-2,this.can/2-1};
        int[] d={this.can/2-1,this.can/2-1};
        int[] e={this.can/2,this.can/2-1};
        snake.add(a);
        snake.add(b);
        snake.add(c);
        snake.add(d);
        snake.add(e);
        generarComida();
        this.direccion="de";
        this.direccionProxima="de";
        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();
    }
    
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorSnake);
        for (int[] par:this.snake){
            pintor.fillRect(par[0]*tam, par[1]*tam, tam-1, tam-1);
        }
        pintor.setColor(colorComida);
        pintor.fillRect(comida[0]*tam, comida[1]*tam, tam-1, tam-1);
    }
    
    public void avanzar(){
        igualarDir();
        int[] ultimo = snake.get(snake.size()-1);
        int agregarx = 0;
        int agregary = 0;
        switch (this.direccion){
            case "de": agregarx=1;break;
            case "iz": agregarx=-1;break;
            case "ar": agregary=-1;break;
            case "ab": agregary=1;break;
        }
        int[] nuevo = {ultimo[0]+agregarx,ultimo[1]+agregary};
        boolean existe = false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0]==snake.get(i)[0]&&nuevo[1]==snake.get(i)[1]) {
                existe=true;
                break;
            }
        }
        if(nuevo[0]==-1||nuevo[0]==can||nuevo[1]==-1||nuevo[1]==can){
            existe=true;
        }
        if (existe) {
            JOptionPane.showMessageDialog(this, "GAME OVER");
            for (int i = 0; i < snake.size(); i++) {
                snake.remove(i);
            }
            this.direccion="de";
            this.direccionProxima="de";
            camino.setTiempo(500);
                int[] a={this.can/2-4,this.can/2-1};
                int[] b={this.can/2-3,this.can/2-1};
                int[] c={this.can/2-2,this.can/2-1};
                int[] d={this.can/2-1,this.can/2-1};
                int[] e={this.can/2,this.can/2-1};
                snake.add(a);
                snake.add(b);
                snake.add(c);
                snake.add(d);
                snake.add(e);
            generarComida();
            
        }else{
            if(nuevo[0]==this.comida[0]&&nuevo[1]==this.comida[1]){
                snake.add(nuevo);
                camino.velocidad();
                generarComida();
            }else{
                snake.add(nuevo);
                snake.remove(0);
            }
        }
        
    }
    
    public void generarComida(){
        boolean existe = false;
        var x = (int)(Math.random()*can);
        var y = (int)(Math.random()*can);
        for (int[] par:this.snake){
            if (par[0]==x&&par[1]==y) {
                existe=true;
                generarComida();
                break;
            }
        }
        if(!existe){
            this.comida[0]=x;
            this.comida[1]=y;
        }
    }
    
    public void cambiarDireccion(String dir){
        if((this.direccion.equals("iz")||this.direccion.equals("de"))&&(dir.equals("ar")||dir.equals("ab"))){
            this.direccionProxima=dir;            
        }
        if((this.direccion.equals("ab")||this.direccion.equals("ar"))&&(dir.equals("iz")||dir.equals("de"))){
            this.direccionProxima=dir;            
        }

    }
    public void igualarDir(){
        this.direccion=this.direccionProxima;
    }
}
