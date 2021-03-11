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

    EditText editTituloTarefa;
    Button btnAdicionar;
    final static TarefaDAO dao = new TarefaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);
        setTitle("Cadastrar Tarefa");

        carregarWidgets();
        configurarBotaoAdd();
    }

    private void configurarBotaoAdd() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTarefa();
                finish();
            }
        });
    }

    private void carregarWidgets() {
        editTituloTarefa = findViewById(R.id.edit_add_titulo_tarefa);
        btnAdicionar = findViewById(R.id.btn_add_tarefa);
    }

    private void salvarTarefa() {
        dao.salvar(new Tarefa(
                editTituloTarefa.getText().toString()
        ));

        Toast.makeText(
                FormularioTarefaActivity.this,
                "Tarefa Adicionada",
                Toast.LENGTH_SHORT
        ).show();
    }
}