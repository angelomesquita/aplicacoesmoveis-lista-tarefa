package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
                startActivity(new Intent(
                        ListaTarefasActivity.this,
                        FormularioTarefaActivity.class
                ));
            }
        });
    }

    private void configurarListaTarefas() {
        final ArrayList<Tarefa> tarefas = dao.buscaTodos();

        configurarAdapterLista(tarefas);
        configurarAcaoClickLista(tarefas);
    }

    private void configurarAdapterLista(ArrayList<Tarefa> tarefas) {
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(
                ListaTarefasActivity.this,
                android.R.layout.simple_list_item_1,
                tarefas
        );

        listaTarefa.setAdapter(adapter);
    }

    private void configurarAcaoClickLista(ArrayList<Tarefa> tarefas) {
        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefaSelecionada = tarefas.get(position);

                Intent i = new Intent(
                        ListaTarefasActivity.this,
                        FormularioTarefaActivity.class
                );
                i.putExtra("tarefaSelecionada", tarefaSelecionada);

                startActivity(i);
            }
        });
    }
}




