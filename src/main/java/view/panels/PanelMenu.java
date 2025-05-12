package view.panels;

import persistence.RecordDAO;
import view.frame.FrameGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel {
    private final int WIDTH = 600;
    private final int HEIGHT = 350;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private final FrameGame frame;
    private JButton btnPlay;
    private JButton btnListRecords;
    private JButton btnExit;
    private JLabel mensajeLabel;

    public PanelMenu(FrameGame game) {
        this.frame = game;
        initComponents();
        asignActions();
        setAttibutes();
    }

    private void setAttibutes() {
        this.setLayout(null);
        this.add(mensajeLabel);
        this.add(btnPlay);
        this.add(btnListRecords);
        this.add(btnExit);
        this.setBackground(Color.black);
        this.setPreferredSize(SIZE);
        this.setFocusable(true);
    }

    private void initComponents() {
        this.mensajeLabel = new JLabel("SNAKE GAME");
        this.mensajeLabel.setBounds(416, 100, 200, 30);
        this.mensajeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        this.mensajeLabel.setForeground(Color.BLUE);

        this.btnPlay = new JButton("Jugar");
        this.btnPlay.setBounds(425, 175, 150, 40);

        this.btnListRecords = new JButton("Mostrar Registros");
        this.btnListRecords.setBounds(425, 275, 150, 40);

        this.btnExit = new JButton("Salir");
        this.btnExit.setBounds(425, 375, 150, 40);
    }

    private void asignActions() {
        this.btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToGame();
            }
        });

        this.btnListRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToRecords();
            }
        });

        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanelMenu.this);
                if (frame != null) {
                    RecordDAO.closeEntityManagerFactory();
                    frame.dispose();
                    System.exit(0);
                }
            }
        });
    }
}