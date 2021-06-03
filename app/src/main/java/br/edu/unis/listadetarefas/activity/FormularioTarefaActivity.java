package br.edu.unis.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.model.MinhasPreferencias;
import br.edu.unis.listadetarefas.room.entity.Tarefa;
import br.edu.unis.listadetarefas.room.TarefaDatabase;
import br.edu.unis.listadetarefas.room.dao.RoomTarefaDAO;

public class FormularioTarefaActivity extends AppCompatActivity {

    EditText editTituloTarefa, editDescricaoTarefa, editPrazoTarefa;
    Tarefa tarefa;
    private RoomTarefaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);
        setTitle("Cadastrar Tarefa");
        carregarWidgets();
        verificarTemExtra();
        instanciarRoom();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.formulario_tarefa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.formulario_tarefa_menu_salvar:
                validarCamposPreenchidos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void validarCamposPreenchidos() {
        if (camposPreenchidos()) {
            persistirTarefa();
        } else {
            Toast.makeText(
            FormularioTarefaActivity.this,
            "Os campos precisam estar preenchidos",
            Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarWidgets() {
        editTituloTarefa = findViewById(R.id.edit_add_titulo_tarefa);
        editDescricaoTarefa = findViewById(R.id.edit_add_descricao_tarefa);
        //editPrazoTarefa = findViewById(R.id.edit_add_prazo_tarefa);
    }

    private boolean camposPreenchidos() {
        return !editTituloTarefa.getText().toString().isEmpty()
            && !editDescricaoTarefa.getText().toString().isEmpty();
    }

    private void persistirTarefa() {
        popularTarefa();
        if (ehEdicaoTarefa()) {
            editarTarefa();
        } else {
            salvarTarefa();
        }

        finish();
    }

    private void verificarTemExtra() {
        if (ehEdicaoTarefa()) {
            setTitle("Editar Tarefa");

            tarefa = (Tarefa) getIntent()
                    .getSerializableExtra("tarefaSelecionada");

            editTituloTarefa.setText(tarefa.getTitulo());
            editDescricaoTarefa.setText(tarefa.getDescricao());
            //editPrazoTarefa.setText(tarefa.getPrazo());
        }
    }

    private void instanciarRoom() {
        this.dao = TarefaDatabase.getTarefaDAOInstance(this);
    }

    private boolean ehEdicaoTarefa() {
        return getIntent().hasExtra("tarefaSelecionada");
    }

    private void popularTarefa() {
        if (ehEdicaoTarefa()) {
            tarefa.setTitulo(editTituloTarefa.getText().toString());
            tarefa.setDescricao(editDescricaoTarefa.getText().toString());
            //tarefa.setPrazo(editPrazoTarefa.getText().toString());
            tarefa.setUsuario(MinhasPreferencias.getUsuarioLogado(this));

        } else {
            tarefa = new Tarefa(
                editTituloTarefa.getText().toString(),
                editDescricaoTarefa.getText().toString()//,
                //editPrazoTarefa.getText().toString()
            );
            tarefa.setUsuario(MinhasPreferencias.getUsuarioLogado(this));
        }
    }

    private void salvarTarefa() {
        dao.salvar(tarefa);

        Toast.makeText(
                FormularioTarefaActivity.this,
                "Tarefa Adicionada",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void editarTarefa() {
        dao.editar(tarefa);

        Toast.makeText(
                FormularioTarefaActivity.this,
                "Tarefa Editada",
                Toast.LENGTH_SHORT
        ).show();
    }
}