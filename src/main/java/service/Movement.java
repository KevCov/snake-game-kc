package service;

import view.panels.PanelGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
    public boolean leftPressed, rightPressed, upPressed, downPressed;
    private PanelGame panelGame;

    public Movement(PanelGame panelGame) {
        this.panelGame = panelGame;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_A: leftPressed = true; break;
            case KeyEvent.VK_D: rightPressed = true; break;
            case KeyEvent.VK_W: upPressed = true; break;
            case KeyEvent.VK_S: downPressed = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_A: leftPressed = false; break;
            case KeyEvent.VK_D: rightPressed = false; break;
            case KeyEvent.VK_W: upPressed = false; break;
            case KeyEvent.VK_S: downPressed = false; break;
        }
    }
}
