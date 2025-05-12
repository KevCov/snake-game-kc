package view.components;

import java.awt.*;

public class Score {
    public static int score = 0;
    private final int WIDTH_PANEL = 1000;

    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.setFont(new Font("consolas", Font.PLAIN, 30));

        g.drawString(String.valueOf(score), WIDTH_PANEL/2, 30);
    }
}
