package com.mycompany.vista;

import com.mycompany.modelo.AlgoritmosGrafos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PantallaGrafos extends JFrame {
    private final AlgoritmosGrafos.Tipo tipo;
    private final JTextArea area = new JTextArea(20, 60);
    private final JButton btnEjecutar = new JButton("Ejecutar ejemplo");
    private final JButton btnAtras = new JButton("ATRAS");

    public PantallaGrafos(AlgoritmosGrafos.Tipo tipo) {
        this.tipo = tipo;
        setTitle("Grafos - " + tipo.getNombre());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Algoritmo: " + tipo.getNombre(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(titulo, BorderLayout.NORTH);

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnEjecutar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAtras.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sur.add(btnEjecutar);
        sur.add(btnAtras);
        add(sur, BorderLayout.SOUTH);

        setSize(780, 550);
        setLocationRelativeTo(null);
    }

    public AlgoritmosGrafos.Tipo getTipo() { return tipo; }
    public void addEjecutarListener(ActionListener l) { btnEjecutar.addActionListener(l); }
    public void addAtrasListener(ActionListener l)    { btnAtras.addActionListener(l); }
    public void mostrarResultado(String s)            { area.setText(s); }
}