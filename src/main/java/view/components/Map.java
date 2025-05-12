package view.components;

import java.awt.*;

public class Map {
    private final int SIZEBLOCK = 20;

    public void draw(Graphics2D g2){
        g2.setColor(Color.gray);
        for(int i=0; i<=960; i+=SIZEBLOCK){ // 48 lineas verticales + linea lado derecho + linea lado izquierdo = 50 lineas
            g2.drawLine(i, 40, i, 600); // pares ordenados = (x:0;y=40) hasta (x:0;y=600)
        }
        for(int j=40; j<=600; j+=SIZEBLOCK){ // 29 lineas horizontales + linea lado inferior = 30 lineas
            g2.drawLine(0, j, 980, j); // pares ordenados = (x:0;y=40) hasta (x:980;y=40)
        }
    }
}
