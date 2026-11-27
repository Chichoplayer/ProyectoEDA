package com.mycompany.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {
    private final JButton btnIngresar = new JButton("INGRESAR");
    private final JButton btnSalir = new JButton("SALIR");

    public InterfazPrincipal() {
        setTitle("EDA II - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Header institucional =====
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(new Color(56, 78, 142));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Variables para los logos (inicializadas vacías por si falla la carga)
        JLabel logoUniv = new JLabel();
        JLabel logoFac = new JLabel();

                // Cargar logo EPN
        java.net.URL urlEPN = getClass().getResource("/logoEPN.jpeg");
        if (urlEPN != null) {
            ImageIcon iconEPN = new ImageIcon(urlEPN);
            Image imgEPN = iconEPN.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            logoUniv.setIcon(new ImageIcon(imgEPN));
            logoUniv.setOpaque(true);
            logoUniv.setBackground(Color.WHITE);
        }

        // Cargar logo Facultad
        java.net.URL urlFac = getClass().getResource("/LogoFacultad.jpg");
        if (urlFac != null) {
            ImageIcon iconFac = new ImageIcon(urlFac);
            Image imgFac = iconFac.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            logoFac.setIcon(new ImageIcon(imgFac));
            logoFac.setOpaque(true);
            logoFac.setBackground(Color.WHITE);
        }

        JLabel titulo = new JLabel("ESCUELA POLITÉCNICA NACIONAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);

        // Añadir al header
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 15, 0, 15);
        gbc.gridy = 0;
        
        gbc.gridx = 0;
        header.add(logoUniv, gbc);
        
        gbc.gridx = 1; 
        gbc.weightx = 1; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        header.add(titulo, gbc);
        
        gbc.gridx = 2; 
        gbc.weightx = 0; 
        gbc.fill = GridBagConstraints.NONE;
        header.add(logoFac, gbc);
        
        add(header, BorderLayout.NORTH);

        // ===== Centro =====
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel lblMateria = new JLabel("EDA II", SwingConstants.CENTER);
        lblMateria.setFont(new Font("Segoe UI", Font.BOLD, 44));
        lblMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblMateria);
        centro.add(Box.createVerticalStrut(15));
        JLabel lblEst = new JLabel("Robin Angulo", SwingConstants.CENTER);
        lblEst.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        lblEst.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblEst);
        add(centro, BorderLayout.CENTER);

        // ===== Botones =====
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setBackground(new Color(56, 78, 142));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setPreferredSize(new Dimension(160, 45));
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setForeground(new Color(56, 78, 142));
        btnSalir.setPreferredSize(new Dimension(160, 45));
        sur.add(btnIngresar);
        sur.add(btnSalir);
        add(sur, BorderLayout.SOUTH);

        setSize(800, 550);
        setLocationRelativeTo(null);
    }

    public void addIngresarListener(ActionListener l) { btnIngresar.addActionListener(l); }
    public void addSalirListener(ActionListener l)    { btnSalir.addActionListener(l); }
}