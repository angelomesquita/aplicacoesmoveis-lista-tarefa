package br.edu.unis.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unis.listadetarefas.R;
import br.edu.unis.listadetarefas.room.TarefaDatabase;
import br.edu.unis.listadetarefas.room.dao.RoomUsuarioDAO;

public class FormularioUsuarioActivity extends AppCompatActivity {

    EditText editUsuario, editSenha, editRepitaSenha;
    RoomUsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);
        setTitle("Cadastrar Usuário");
        carregarWidgets();
        instanciarRoom();
    }

    private void carregarWidgets() {
        editUsuario = findViewById(R.id.edit_add_usuario_usuario);
        editSenha = findViewById(R.id.edit_add_usuario_senha);
        editRepitaSenha = findViewById(R.id.edit_add_usuario_repita_senha);
    }

    private void instanciarRoom() {
        this.dao = TarefaDatabase.getUsuarioDAOInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.formulario_usuario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.formulario_usuario_menu_salvar:
                validarCamposPreenchidos();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void validarCamposPreenchidos() {
        if (camposPreenchidos() && senhasSaoIguais()) {
            Toast.makeText(
                    FormularioUsuarioActivity.this,
                    "Legal, vamos salvar o usuário entao",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(
            FormularioUsuarioActivity.this,
            "Os campos precisam estar preenchidos e as senhas serem iguais",
            Toast.LENGTH_SHORT).show();
        }
    }

    private boolean camposPreenchidos() {
        return !editUsuario.getText().toString().isEmpty()
                && !editSenha.getText().toString().isEmpty()
                && !editRepitaSenha.getText().toString().isEmpty();
    }

    private boolean senhasSaoIguais() {
        return editSenha.getText().toString().equals(
            editRepitaSenha.getText().toString()
        );
    }
}