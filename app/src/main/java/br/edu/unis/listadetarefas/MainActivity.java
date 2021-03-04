package br.edu.unis.listadetarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ListView listaTarefa;
    private FloatingActionButton fabAddTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTarefa = findViewById(R.id.lista_tarefas);

        ArrayList<String> arrayDeTarefas = new ArrayList<>();

        arrayDeTarefas.add("Estudar Java");
        arrayDeTarefas.add("Estudar Android Studio");
        arrayDeTarefas.add("Estudar Programação");
        arrayDeTarefas.add("Estudar Empreendedorismo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayDeTarefas
        );

        listaTarefa.setAdapter(adapter);

        fabAddTarefa = findViewById(R.id.fab_add_tarefa);
        fabAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Tarefa Criada!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(
                        MainActivity.this,
                        FormularioTarefaActivity.class
                ));
            }
        });

    }
}