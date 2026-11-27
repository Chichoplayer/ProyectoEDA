package com.mycompany.controlador;

import com.mycompany.vista.PantallaEleccion;
import com.mycompany.vista.VistaInformacion;

import javax.swing.JFrame;
import java.util.function.Supplier;

public class PantallaInfoCont {
    public PantallaInfoCont(JFrame ventana, VistaInformacion vista, Supplier<String> ejecutable) {
        vista.addEjecutarListener(e -> vista.mostrarResultado(ejecutable.get()));
        vista.addAtrasListener(e -> {
            PantallaEleccion eleccion = new PantallaEleccion();
            new PantallaEleccionCont(eleccion);
            eleccion.setVisible(true);
            ventana.dispose();
        });
    }
}