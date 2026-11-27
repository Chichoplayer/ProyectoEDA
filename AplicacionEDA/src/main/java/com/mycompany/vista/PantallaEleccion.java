package com.mycompany.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PantallaEleccion extends JFrame {
    private final JComboBox<String> boxPrincipal = new JComboBox<>(
            new String[]{"Eficiencia algoritmica", "Analisis de Complejidad"});
    private final JComboBox<String> boxGrafos = new JComboBox<>(
            new String[]{"Árbol topológico", "TSP", "Prim", "Kahn", "Kruskal", "Dijkstra"});
    private final JComboBox<String> boxComplejidad = new JComboBox<>(
            new String[]{"P", "NP", "NP-HARD", "NP-COMPLETE"});
    private final JButton btnAceptar1 = new JButton("ACEPTAR");
    private final JButton btnAceptar2 = new JButton("ACEPTAR");
    private final JButton btnAceptar3 = new JButton("ACEPTAR");

    public PantallaEleccion() {
        setTitle("Menú de opciones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.weightx = 1; c.gridwidth = 2;

        c.gridy = 0; main.add(new JLabel("Opción principal:"), c);
        c.gridy = 1; main.add(boxPrincipal, c);
        c.gridy = 2; main.add(btnAceptar1, c);

        c.gridy = 3; main.add(new JLabel("Algoritmo de grafos:"), c);
        c.gridy = 4; main.add(boxGrafos, c);
        c.gridy = 5; main.add(btnAceptar2, c);

        c.gridy = 6; main.add(new JLabel("Clase de complejidad:"), c);
        c.gridy = 7; main.add(boxComplejidad, c);
        c.gridy = 8; main.add(btnAceptar3, c);

        Font f = new Font("Segoe UI", Font.BOLD, 16);
        boxPrincipal.setFont(f);
        boxGrafos.setFont(f);
        boxComplejidad.setFont(f);
        for (JButton b : new JButton[]{btnAceptar1, btnAceptar2, btnAceptar3}) {
            b.setBackground(new Color(56, 78, 142));
            b.setForeground(Color.WHITE);
            b.setFont(f);
            b.setBorderPainted(false);
        }

        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(main);
        setSize(520, 600);
        setLocationRelativeTo(null);
    }

    public String getSeleccionPrincipal() {
        Object o = boxPrincipal.getSelectedItem();
        return o == null ? null : o.toString();
    }
    public String getSeleccionGrafo() {
        Object o = boxGrafos.getSelectedItem();
        return o == null ? null : o.toString();
    }
    public String getSeleccionComplejidad() {
        Object o = boxComplejidad.getSelectedItem();
        return o == null ? null : o.toString();
    }

    public void addAceptar1Listener(ActionListener l) { btnAceptar1.addActionListener(l); }
    public void addAceptar2Listener(ActionListener l) { btnAceptar2.addActionListener(l); }
    public void addAceptar3Listener(ActionListener l) { btnAceptar3.addActionListener(l); }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}