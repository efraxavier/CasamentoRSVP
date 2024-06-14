package com.example.casamentorsvp.model;

import java.util.List;

public interface IConvidadoDAO {
    void addConvidado(Convidado convidado);
    Convidado buscarConvidadoPorId(int id) throws ConvidadoNaoEncontradoException;
    List<Convidado> getAllConvidados();
    int updateConvidado(Convidado convidado);
    void deleteConvidado(Convidado convidado);
}
