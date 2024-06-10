package com.example.casamentorsvp.controller;

import android.content.Context;

import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAO;
import com.example.casamentorsvp.model.ConvidadoNaoEncontradoException;

public class ConvidadoController {
    private ConvidadoDAO convidadoDAO;

    public ConvidadoController(Context context) {
        this.convidadoDAO = new ConvidadoDAO(context);
    }

    public void adicionarConvidado(Convidado convidado) {
        try {
            convidadoDAO.inserirConvidado(convidado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Convidado buscarConvidado(int id) {
        try {
            return convidadoDAO.buscarConvidadoPorId(id);
        } catch (ConvidadoNaoEncontradoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
