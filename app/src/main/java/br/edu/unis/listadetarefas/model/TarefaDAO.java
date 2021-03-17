package br.edu.unis.listadetarefas.model;

import android.util.Log;

import java.util.ArrayList;

public class TarefaDAO {

    final static ArrayList<Tarefa> tarefas = new ArrayList<>();

    public void salvar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public ArrayList<Tarefa> buscaTodos() {
        return new ArrayList<>(tarefas);
    }

    public Tarefa buscarTarefaPeloId(Tarefa tarefa) {
        for (Tarefa t : tarefas) {
            if (t.getId() == tarefa.getId()) {
                return t;
            }
        }
        return null;
    }

    public void editar(Tarefa tarefa) {
        Tarefa tarefaEditada = buscarTarefaPeloId(tarefa);

        if (tarefaEditada != null) {
            int posicaoTarefa = tarefas.indexOf(tarefaEditada);
            tarefas.set(posicaoTarefa, tarefa);
        }
    }
}
