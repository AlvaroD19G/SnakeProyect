/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Álvaro Álvarez R
 */
public class PanelFondo extends JPanel{
    
    private Color colorFondo= Color.gray;
    private int tammax;
    private int tam;
    private int can;

    public PanelFondo(int tammax, int can) {
        this.tammax = tammax;
        this.can = can;
        this.tam=tammax/can;
    }
    
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorFondo);
        for (int i = 0; i < this.can; i++) {
            for (int j = 0; j < this.can; j++) {
                pintor.drawRect(i*this.tam, j*this.tam, this.tam-1, this.tam-1);
            }
        }
    }
}
