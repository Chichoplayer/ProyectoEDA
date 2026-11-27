package com.mycompany.controlador;

import com.mycompany.modelo.AlgoritmosGrafos;
import com.mycompany.vista.PantallaEleccion;
import com.mycompany.vista.PantallaGrafos;

public class PantallaGrafosCont {
    public PantallaGrafosCont(PantallaGrafos vista) {
        vista.addEjecutarListener(e ->
            vista.mostrarResultado(AlgoritmosGrafos.ejecutar(vista.getTipo()))
        );
        vista.addAtrasListener(e -> {
            PantallaEleccion eleccion = new PantallaEleccion();
            new PantallaEleccionCont(eleccion);
            eleccion.setVisible(true);
            vista.dispose();
        });
    }
}