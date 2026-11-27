package com.mycompany.vista;

import java.awt.event.ActionListener;

public interface VistaInformacion {
    void addEjecutarListener(ActionListener l);
    void addAtrasListener(ActionListener l);
    void mostrarResultado(String texto);
}