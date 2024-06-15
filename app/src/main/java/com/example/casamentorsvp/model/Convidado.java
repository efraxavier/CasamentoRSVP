package com.example.casamentorsvp.model;
import java.io.Serializable;

public class Convidado implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private boolean rsvp;
    private String preferenciasAlimentares;

    // Construtor completo
    public Convidado(int id, String nome, String email, String telefone, boolean rsvp, String preferenciasAlimentares) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.rsvp = rsvp;
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    // Getter e Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter para telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Getter e Setter para rsvp
    public boolean isRsvp() {
        return rsvp;
    }

    public void setRsvp(boolean rsvp) {
        this.rsvp = rsvp;
    }

    public String getPreferenciasAlimentares() {
        return preferenciasAlimentares;
    }

    public void setPreferenciasAlimentares(String preferenciasAlimentares) {
        this.preferenciasAlimentares = preferenciasAlimentares;
    }

    // Sobrescrita do método toString
    @Override
    public String toString() {
        return "Convidado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", rsvp=" + rsvp +
                ", preferenciasAlimentares='" + preferenciasAlimentares + '\'' +
                '}';
    }
}
