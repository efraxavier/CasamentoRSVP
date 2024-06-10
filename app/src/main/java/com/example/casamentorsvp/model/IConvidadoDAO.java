package com.example.casamentorsvp.model;

import java.util.List;

public interface IConvidadoDAO {
    void addConvidado(Convidado convidado);
    Convidado getConvidado(int id);
    List<Convidado> getAllConvidados();
    void updateConvidado(Convidado convidado);
    void deleteConvidado(Convidado convidado);
}
