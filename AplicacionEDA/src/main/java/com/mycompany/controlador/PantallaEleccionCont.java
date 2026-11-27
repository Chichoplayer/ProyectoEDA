package com.mycompany.controlador;

import com.mycompany.modelo.AlgoritmosGrafos;
import com.mycompany.modelo.EjemplosComplejidad;
import com.mycompany.vista.*;

import javax.swing.JFrame;

public class PantallaEleccionCont {
    private final PantallaEleccion vista;

    public PantallaEleccionCont(PantallaEleccion vista) {
        this.vista = vista;
        vista.addAceptar1Listener(e -> manejarPrincipal());
        vista.addAceptar2Listener(e -> manejarGrafo());
        vista.addAceptar3Listener(e -> manejarComplejidad());
    }

    private void manejarPrincipal() {
        String op = vista.getSeleccionPrincipal();
        if ("Eficiencia algoritmica".equals(op)) {
            PantallaEficienciaAlgoritmica p = new PantallaEficienciaAlgoritmica();
            new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarEficiencia);
            abrir(p);
        } else if ("Analisis de Complejidad".equals(op)) {
            PantallaAnalisisComplejidad p = new PantallaAnalisisComplejidad();
            new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarAnalisis);
            abrir(p);
        } else {
            vista.mostrarError("Seleccione una opción válida.");
        }
    }

    private void manejarGrafo() {
        AlgoritmosGrafos.Tipo tipo = AlgoritmosGrafos.Tipo.desde(vista.getSeleccionGrafo());
        if (tipo == null) { vista.mostrarError("Algoritmo no válido."); return; }
        PantallaGrafos p = new PantallaGrafos(tipo);
        new PantallaGrafosCont(p);
        abrir(p);
    }

    private void manejarComplejidad() {
        String op = vista.getSeleccionComplejidad();
        if (op == null) { vista.mostrarError("Opción no válida."); return; }
        switch (op) {
            case "P": {
                PantallaP p = new PantallaP();
                new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarP);
                abrir(p); break;
            }
            case "NP": {
                PantallaNP p = new PantallaNP();
                new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarNP);
                abrir(p); break;
            }
            case "NP-HARD": {
                PantallaNPHARD p = new PantallaNPHARD();
                new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarNPHard);
                abrir(p); break;
            }
            case "NP-COMPLETE": {
                PantallaNPCOMPLETE p = new PantallaNPCOMPLETE();
                new PantallaInfoCont(p, p, EjemplosComplejidad::ejecutarNPComplete);
                abrir(p); break;
            }
            default: vista.mostrarError("Opción no válida.");
        }
    }

    private void abrir(JFrame siguiente) {
        siguiente.setVisible(true);
        vista.dispose();
    }
}