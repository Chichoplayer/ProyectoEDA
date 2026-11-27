package com.mycompany;

import com.mycompany.controlador.LoginCont;
import com.mycompany.vista.InterfazPrincipal;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            InterfazPrincipal vista = new InterfazPrincipal();
            new LoginCont(vista);
            vista.setVisible(true);
        });
    }
}