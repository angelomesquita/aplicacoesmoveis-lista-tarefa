package br.edu.unis.listadetarefas.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.edu.unis.listadetarefas.room.entity.Tarefa;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;
import br.edu.unis.listadetarefas.room.entity.Usuario;

@Database(entities = {Tarefa.class, Usuario.class}, version = 2)
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
            //.fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .build()
            .getRoomTarefaDAO();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Usuario` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `usuario` TEXT, `senha` TEXT)"
            );
        }
    };
}
