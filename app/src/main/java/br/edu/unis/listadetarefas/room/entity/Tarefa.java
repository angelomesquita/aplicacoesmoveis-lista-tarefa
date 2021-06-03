package br.edu.unis.listadetarefas.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String descricao;
    private String usuario;
    //private String prazo;

    public Tarefa(String titulo, String descricao/*, String prazo*/) {
        this.titulo = titulo;
        this.descricao = descricao;
        //this.prazo = prazo;
        //this.usuario = ;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    /*public String getPrazo() {
        return prazo;
    }*/

    public String getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*public void setPrazo(String prazo) {
        this.prazo = prazo;
    }*/

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
