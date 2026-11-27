package com.mycompany.controlador;

import com.mycompany.vista.InterfazPrincipal;
import com.mycompany.vista.PantallaEleccion;

public class LoginCont {
    public LoginCont(InterfazPrincipal vista) {
        vista.addIngresarListener(e -> {
            PantallaEleccion eleccion = new PantallaEleccion();
            new PantallaEleccionCont(eleccion);
            eleccion.setVisible(true);
            vista.dispose();
        });
        vista.addSalirListener(e -> vista.dispose());
    }
}