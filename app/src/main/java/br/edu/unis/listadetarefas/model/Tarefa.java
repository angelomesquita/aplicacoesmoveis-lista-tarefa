package br.edu.unis.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private final int id;
    private String titulo;
    private String descricao;
    private static int contadorId = 1;

    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.id = contadorId;
        contadorId++;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
