package br.edu.unis.listadetarefas.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.edu.unis.listadetarefas.model.Tarefa;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class TarefaDatabase extends RoomDatabase {

    public abstract RoomTarefaDAO getRoomTarefaDAO();

}
