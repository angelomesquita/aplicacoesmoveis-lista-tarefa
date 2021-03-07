package br.edu.unis.listadetarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioTarefaActivity extends AppCompatActivity {

    EditText editTituloTarefa;
    Button btnAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tarefa);
        setTitle("Cadastrar Tarefa");

        editTituloTarefa = findViewById(R.id.edit_add_titulo_tarefa);
        btnAdicionar = findViewById(R.id.btn_add_tarefa);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adicionar o titulo da tarefa na lista de tarefas
                Toast.makeText(
                        FormularioTarefaActivity.this,
                        "Tarefa Adicionada",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });


    }
}