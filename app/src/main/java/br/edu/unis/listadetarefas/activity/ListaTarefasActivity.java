package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.model.Tarefa;
import br.edu.unis.listadetarefas.model.TarefaDAO;


public class ListaTarefasActivity extends AppCompatActivity {

    private ListView listaTarefa;
    private FloatingActionButton fabAddTarefa;
    final static TarefaDAO dao = new TarefaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);
        carregarWidgets();
        configurarFABAddTarefa();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarListaTarefas();
    }

    private void carregarWidgets() {
        fabAddTarefa = findViewById(R.id.fab_add_tarefa);
        listaTarefa = findViewById(R.id.lista_tarefas);
    }

    private void configurarFABAddTarefa() {
        fabAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Tarefa Criada!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(
                        ListaTarefasActivity.this,
                        FormularioTarefaActivity.class
                ));
            }
        });
    }

    private void configurarListaTarefas() {
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(
                ListaTarefasActivity.this,
                android.R.layout.simple_list_item_1,
                dao.buscaTodos()
        );

        listaTarefa.setAdapter(adapter);
    }
}