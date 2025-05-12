package view.components;

import java.awt.*;
import java.util.Random;

public class Apple {
    private final int WIDTH_PANEL_SAFE = 960;
    private final int HEIGHT_PANEL_SAFE = 540;
    private final int HEIGHT_SCORE = 40;
    public final int SIZEAPPLE = 20;
    public int x;
    public int y;
    Random random = new Random();

    public Apple(){
        setPosition();
    }

    public void setPosition(){
        x = random.nextInt(WIDTH_PANEL_SAFE/SIZEAPPLE)*SIZEAPPLE; // 1 al 48 * 20 / valor x maximo : 48*20=960
        y = random.nextInt(HEIGHT_PANEL_SAFE/SIZEAPPLE)*SIZEAPPLE + HEIGHT_SCORE; // 1 al 27 * 20 + 40 / valor y maximo : 27*20=540+40=580
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.fillOval(x, y, SIZEAPPLE, SIZEAPPLE);
    }
}
