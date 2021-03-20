package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.model.Tarefa;
import br.edu.unis.listadetarefas.model.TarefaDAO;

public class FormularioTarefaActivity extends AppCompatActivity {

    EditText editTituloTarefa, editDescricaoTarefa;
    Button btnAdicionar;
    Tarefa tarefa;
    final static TarefaDAO dao = new TarefaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);
        setTitle("Cadastrar Tarefa");

        carregarWidgets();
        configurarBotaoPersistencia();
        verificarTemExtra();
    }

    private void carregarWidgets() {
        editTituloTarefa = findViewById(R.id.edit_add_titulo_tarefa);
        editDescricaoTarefa = findViewById(R.id.edit_add_descricao_tarefa);
        btnAdicionar = findViewById(R.id.btn_add_tarefa);
    }

    private void configurarBotaoPersistencia() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularTarefa();
                if (ehEdicaoTarefa()) {
                    editarTarefa();
                } else {
                    salvarTarefa();
                }

                finish();
            }
        });
    }

    private void verificarTemExtra() {
        if (ehEdicaoTarefa()) {
            setTitle("Editar Tarefa");

            tarefa = (Tarefa) getIntent()
                    .getSerializableExtra("tarefaSelecionada");

            editTituloTarefa.setText(tarefa.getTitulo());
            editDescricaoTarefa.setText(tarefa.getDescricao());

            btnAdicionar.setText("Editar");
        }
    }

    private boolean ehEdicaoTarefa() {
        if (getIntent().hasExtra("tarefaSelecionada")) {
            return true;
        }
        return false;
    }

    private void popularTarefa() {
        if (ehEdicaoTarefa()) {
            tarefa.setTitulo(editTituloTarefa.getText().toString());
            tarefa.setDescricao(editDescricaoTarefa.getText().toString());
        } else {
            tarefa = new Tarefa(
                    editTituloTarefa.getText().toString(),
                    editDescricaoTarefa.getText().toString()
            );
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