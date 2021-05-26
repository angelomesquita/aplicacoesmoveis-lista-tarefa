package br.edu.unis.listadetarefas.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.edu.unis.listadetarefas.model.Tarefa;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class TarefaDatabase extends RoomDatabase {

    public abstract RoomTarefaDAO getRoomTarefaDAO();

    private static final String BANCO_DE_DADOS = "listadetarefas.db";

    public static RoomTarefaDAO getInstance(Context contexto) {

        return Room.databaseBuilder(
                contexto,
                TarefaDatabase.class,
                TarefaDatabase.BANCO_DE_DADOS
        )
            .allowMainThreadQueries()
            .build()
            .getRoomTarefaDAO();
    }
}
