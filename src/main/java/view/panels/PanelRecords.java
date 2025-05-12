package view.panels;

import service.RecordService;
import view.frame.FrameGame;
import model.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelRecords extends JPanel {
    private final int WIDTH = 600;
    private final int HEIGHT = 350;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private final FrameGame frame;
    private RecordService recordService;
    private JTable table;
    private JButton btnMenu;
    private JButton btnEliminar;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    String[] columnNames = {"N° Registro", "Jugador", "Puntaje", "Fecha"};

    public PanelRecords(FrameGame game) {
        this.frame = game;
        this.recordService = new RecordService();
        createButton();
        createTable();
        createScrollPanel();
        setAttributes();
    }

    public void loadRecords() {
        tableModel.setRowCount(0);
        var records = recordService.getAllRecords();
        for (Record record : records) {
            tableModel.addRow(new Object[]{
                    record.getId(),
                    record.getPlayerName(),
                    record.getScore(),
                    record.getGameDate(),
            });
        }
    }

    private void createTable() {
        this.tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(tableModel);
        this.table.setBackground(Color.WHITE);
        this.table.setFont(new Font("Arial", Font.PLAIN, 14));
        this.table.setRowHeight(24);
    }

    private void createScrollPanel() {
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(50, 30, 900, 450);
    }

    private void createButton() {
        this.btnMenu = new JButton("Menu Principal");
        this.btnMenu.setBounds(345, 500, 150, 25);
        this.btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToMenu();
            }
        });

        this.btnEliminar = new JButton("Eliminar registro");
        this.btnEliminar.setBounds(500, 500, 150, 25);
        this.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Long> ids = recordService.getAllIds();
                if (ids.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No hay registros para eliminar.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JComboBox<Long> idComboBox = new JComboBox<>(ids.toArray(new Long[0]));

                int option = JOptionPane.showConfirmDialog(
                        frame,
                        new Object[]{"Selecciona el N° del registro a eliminar:", idComboBox},
                        "Eliminar Registro",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (option == JOptionPane.OK_OPTION) {
                    Long selectedId = (Long) idComboBox.getSelectedItem();
                    if (selectedId != null) {
                        String msg = recordService.deleteRecord(selectedId);
                        loadRecords();
                        JOptionPane.showMessageDialog(frame, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No se seleccionó ningún ID.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    private void setAttributes() {
        this.setLayout(null);
        this.add(btnMenu);
        this.add(btnEliminar);
        this.add(scrollPane);
        this.setBackground(Color.black);
        this.setPreferredSize(SIZE);
        this.setFocusable(true);
    }
}
