package Snake;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Caminante implements Runnable{

    private PanelSnake panel;
    private boolean estado;
    private int tiempo;
    
    public Caminante(PanelSnake panel){
        this.tiempo=500;
        this.panel=panel;
        this.estado= true;
    }
            
    @Override
    public void run() {
        while(estado){
            panel.avanzar();
            panel.repaint();
            try {
                Thread.sleep(this.tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void stop(){
        this.estado=false;
    }
    public void velocidad(){
        if(this.tiempo<300){
            this.tiempo-=40;
        }
        if(this.tiempo<200){
            this.tiempo-=30;
        }
        if(this.tiempo<100){
            this.tiempo-=10;
        }
        if(this.tiempo>50){
            this.tiempo-=5;
        }
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
