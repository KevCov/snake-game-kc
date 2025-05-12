package view.panels;

import service.Movement;
import view.components.Apple;
import view.components.Map;
import view.components.Score;
import view.components.Snake;
import view.frame.FrameGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGame extends JPanel implements Runnable {
    private final int WIDTH = 1000;
    private final int HEIGHT = 640;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private JButton btnMenu;
    private Movement mov = new Movement(this);
    private FrameGame frame;
    private Thread gameThread;
    private Snake snake;
    private Apple apple;
    private Score score;
    private Map map;
    public static volatile boolean running = false;
    public static double amountOfTicks = 10.0;

    public PanelGame(FrameGame game) {
        this.frame = game;
        initComponents();
        setAttributes();
    }

    public void startThread() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("inicio el juego");

    }

    public void stopGame() {
        try {
            running = false;
            gameThread.join();
            System.out.println("detenido el juego");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        this.snake = new Snake(mov, this);
        this.score = new Score();
        this.map = new Map();
        newApple();
        createButton();
    }

    private void setAttributes() {
        this.setLayout(null);
        this.add(btnMenu);
        this.setBackground(Color.black);
        this.addKeyListener(mov);
        this.setPreferredSize(SIZE);
        this.setFocusable(true);
    }

    private void newApple() {
        apple = new Apple();
    }

    private void createButton() {
        this.btnMenu = new JButton("Menu Principal");
        this.btnMenu.setBounds(5, 5, 150, 25);
        this.btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToMenu();
                stopGame();
            }
        });
    }

    private void checkCollision() {
        snake.checkCollision();
        handleAppleCollision();
    }

    private void handleAppleCollision() {
        if (new Rectangle(snake.xx[0], snake.yy[0], snake.SIZESNAKE, snake.SIZESNAKE)
                .intersects(new Rectangle(apple.x, apple.y, apple.SIZEAPPLE, apple.SIZEAPPLE))) {
            Snake.bodySnake++;
            newApple();
            increaseSpeed();
            score.score++;
        }
    }

    private void update() {
        snake.update();
        snake.move();
    }

    private void increaseSpeed() {
        if (amountOfTicks < 15) {
            amountOfTicks += 0.25;
        }
    }

    @Override
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                update();
                checkCollision();
                repaint();
                delta--;
                ns = 1000000000 / amountOfTicks;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        apple.draw(g2);
        snake.draw(g2);
        map.draw(g2);
        score.draw(g2);
    }
}