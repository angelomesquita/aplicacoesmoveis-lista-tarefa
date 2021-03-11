package br.edu.unis.listadetarefas.model;

import java.util.ArrayList;

public class TarefaDAO {

    final static ArrayList<Tarefa> tarefas = new ArrayList<>();

    public void salvar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public ArrayList<Tarefa> buscaTodos() {
        return new ArrayList<>(tarefas);
    }
}
