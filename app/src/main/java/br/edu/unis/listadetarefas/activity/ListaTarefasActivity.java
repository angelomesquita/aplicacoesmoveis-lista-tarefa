package br.edu.unis.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.model.Tarefa;
import br.edu.unis.listadetarefas.model.TarefaDAO;


public class ListaTarefasActivity extends AppCompatActivity {

    private ListView listaTarefa;
    private FloatingActionButton fabAddTarefa;
    private ArrayAdapter<Tarefa> adapter;
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.lista_tarefas_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        selecionarAcaoMenuContexto(item);
        return super.onContextItemSelected(item);
    }

    private void selecionarAcaoMenuContexto(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lista_tarefas_menu_remover:
                removerTarefa(item);
                break;
            case R.id.lista_tarefas_menu_ajuda:
                Toast.makeText(this, "Hoje não!!!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void removerTarefa(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Tarefa tarefaSelecionada = adapter.getItem(menuInfo.position);

        dao.remover(tarefaSelecionada);
        adapter.remove(tarefaSelecionada);
        adapter.notifyDataSetChanged();

        Toast.makeText(
                ListaTarefasActivity.this,
                "Tarefa Apagada",
                Toast.LENGTH_SHORT
        ).show();
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
        configurarAdapterLista();
        recarregarAdapter();
        configurarAcaoClickLista();
    }

    private void recarregarAdapter() {
        adapter.clear();
        adapter.addAll(dao.buscarTodos());
    }

    private void configurarAdapterLista() {
        adapter = new ArrayAdapter<>(
                ListaTarefasActivity.this,
                android.R.layout.simple_list_item_1
        );

        listaTarefa.setAdapter(adapter);
    }

    private void configurarAcaoClickLista() {
        configurarClickLista();
        configurarClickLongoLista();
    }

    private void configurarClickLista() {
        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefaSelecionada = (Tarefa) parent.getItemAtPosition(position);

                Intent i = new Intent(
                        ListaTarefasActivity.this,
                        FormularioTarefaActivity.class
                );
                i.putExtra("tarefaSelecionada", tarefaSelecionada);

                startActivity(i);
            }
        });
    }

    private void configurarClickLongoLista() {
        registerForContextMenu(listaTarefa);

        /*listaTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefaSelecionada = (Tarefa) parent.getItemAtPosition(position);

                dao.remover(tarefaSelecionada);
                adapter.remove(tarefaSelecionada);
                adapter.notifyDataSetChanged();

                Toast.makeText(
                        ListaTarefasActivity.this,
                        "Tarefa Apagada",
                        Toast.LENGTH_SHORT
                ).show();

                return false;
            }
        });*/
    }
}




