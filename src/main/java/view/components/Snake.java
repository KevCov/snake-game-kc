package view.components;

import model.Record;
import persistence.RecordDAO;
import service.Movement;
import service.RecordService;
import view.panels.PanelGame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake {
    private final int WIDTH_PANEL_SAFE = 960;
    private final int HEIGHT_PANEL_SAFE = 540;
    private final int HEIGHT_SCORE = 40;
    private int xDirection, yDirection;
    private Movement mov;
    private PanelGame panelGame;
    private RecordService recordService;
    private Random random = new Random();
    public static int bodySnake = 1;
    public static final int SIZESNAKE = 20;
    public int[] xx = new int[(WIDTH_PANEL_SAFE / SIZESNAKE) * (HEIGHT_PANEL_SAFE / SIZESNAKE)];
    public int[] yy = new int[(WIDTH_PANEL_SAFE / SIZESNAKE) * (HEIGHT_PANEL_SAFE / SIZESNAKE)];

    public Snake(Movement mov, PanelGame panelGame) {
        this.mov = mov;
        this.panelGame = panelGame;
        this.recordService = new RecordService();
        setPosition();
    }

    public void setPosition() {
        xx[0] = random.nextInt(WIDTH_PANEL_SAFE/SIZESNAKE)*SIZESNAKE; // 1 al 48 * 20 / valor x maximo : 48*20=960
        yy[0] = random.nextInt(HEIGHT_PANEL_SAFE/SIZESNAKE)*SIZESNAKE + HEIGHT_SCORE; // 1 al 27 * 20 + 40 / valor y maximo : 27*20=540+40=580
    }

    private void setXDirection(int xdirection) {
        this.xDirection = xdirection;
    }

    private void setYDirection(int ydirection) {
        this.yDirection = ydirection;
    }

    public void draw(Graphics2D g2) {
        for (int i=0; i<bodySnake; i++){
            if (i == 0){
                g2.setColor(Color.BLUE);
                g2.fillRect(xx[i], yy[i], SIZESNAKE, SIZESNAKE);
            } else {
                g2.setColor(Color.yellow);
                g2.fillRect(xx[i], yy[i], SIZESNAKE, SIZESNAKE);
            }
        }
    }

    public void checkCollision() {
        if (xx[0] < 0 || xx[0] > WIDTH_PANEL_SAFE || yy[0] < HEIGHT_SCORE || yy[0] > HEIGHT_PANEL_SAFE + HEIGHT_SCORE) {
            resetGame();
        }

        for (int i=1; i<bodySnake; i++) {
            if (xx[0] == xx[i] && yy[0] == yy[i]) {
                resetGame();
            }
        }
    }

    private void resetGame() {
        if (Score.score == 0) {
            resetAtributes();
        } else {
            int choice = JOptionPane.showConfirmDialog(
                    panelGame,
                    "¿Desea guardar su puntaje?",
                    "Guardar puntaje",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                saveScoreAndReset();
            } else {
                resetAtributes();
            }
        }
    }

    private void resetAtributes() {
        setPosition();
        setXDirection(0);
        setYDirection(0);
        bodySnake = 1;
        Score.score = 0;
        PanelGame.amountOfTicks = 10;
    }

    private void saveScoreAndReset() {
        String nombre = JOptionPane.showInputDialog(
                panelGame,
                "Ingrese su nombre:",
                "Guardar Puntaje",
                JOptionPane.PLAIN_MESSAGE);

        if (nombre != null && !nombre.trim().isEmpty()) {
            recordService.createRecord(new Record(Score.score, nombre));
            resetAtributes();
        } else {
            resetAtributes();
        }
    }

    public void update() {
        for (int i=bodySnake; i>0; i--){
            xx[i] = xx[i-1];
            yy[i] = yy[i-1];
        }
        xx[0] += xDirection;
        yy[0] += yDirection;
    }

    public void move() {
        if (mov.leftPressed && xDirection != SIZESNAKE) {
            setYDirection(0);
            setXDirection(-SIZESNAKE);
        } else if (mov.rightPressed && xDirection != -SIZESNAKE) {
            setYDirection(0);
            setXDirection(SIZESNAKE);
        } else if (mov.upPressed && yDirection != SIZESNAKE) {
            setXDirection(0);
            setYDirection(-SIZESNAKE);
        } else if (mov.downPressed && yDirection != -SIZESNAKE) {
            setXDirection(0);
            setYDirection(SIZESNAKE);
        }
    }
}