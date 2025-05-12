package view.frame;

import persistence.RecordDAO;
import view.panels.PanelGame;
import view.panels.PanelMenu;
import view.panels.PanelRecords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameGame extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 640;
    private static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    private PanelGame panelGame;
    private PanelRecords panelRecords;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private PanelMenu panelMenu;

    public FrameGame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        panelMenu = new PanelMenu(this);
        panelGame = new PanelGame(this);
        panelRecords = new PanelRecords(this);

        mainPanel.add(panelMenu, "MENU");
        mainPanel.add(panelGame, "GAME");
        mainPanel.add(panelRecords, "RECORDS");

        this.add(mainPanel);

        cardLayout.show(mainPanel, "MENU");
        panelGame.requestFocusInWindow();
        setAttributes();
    }

    public void switchToGame() {
        cardLayout.show(mainPanel, "GAME");
        panelGame.startThread();
        panelGame.requestFocusInWindow();
    }

    public void switchToMenu() {
        cardLayout.show(mainPanel, "MENU");
        panelMenu.requestFocusInWindow();
    }

    public void switchToRecords() {
        panelRecords.loadRecords();
        cardLayout.show(mainPanel, "RECORDS");
        panelMenu.requestFocusInWindow();
    }

    private void setAttributes() {
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                RecordDAO.closeEntityManagerFactory();
                System.exit(0);
            }
        });
        this.setSize(SCREEN_SIZE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
