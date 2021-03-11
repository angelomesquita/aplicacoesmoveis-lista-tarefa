package br.edu.unis.listadetarefas.model;

public class Tarefa {

    private String titulo;
    private String descricao;

    public Tarefa(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @todo Voltar ao normal após modificar o adapter
     * @return String titulo da tarefa
     */
    public String toString() {
       return this.titulo;
    }
}
