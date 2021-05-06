package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unis.listadetarefas.R;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtSenha;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        carregarWidgets();
        configurarClickBotaoEntrar();
    }

    private void carregarWidgets() {
        txtUsuario = findViewById(R.id.login_usuario);
        txtSenha = findViewById(R.id.login_senha);
        btnEntrar = findViewById(R.id.login_botao_entrar);
    }

    private void configurarClickBotaoEntrar() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (credenciaisEstaoVazias()) {
                    Toast.makeText(
                            LoginActivity.this,
                            "Usuario e Senha devem estar preenchidos",
                            Toast.LENGTH_LONG
                    ).show();
                } else {
                    autenticar();
                }
            }
        });
    }

    private boolean credenciaisEstaoVazias() {
        if (txtUsuario.getText().length() == 0) {
            return true;
        }

        if (txtSenha.getText().length() == 0) {
            return true;
        }

        return false;
    }

    private void autenticar() {
        if (autorizaCredenciais()) {
            salvarPreferencias();
            abreListaDeTarefas();
            finish();
        } else {
            Toast.makeText(this, "Usuario ou Senha Incorretos", Toast.LENGTH_LONG).show();
        }
    }

    private boolean autorizaCredenciais() {
        // TODO: 05-May-21 Implementar a autenticação de forma correta e segura.
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        if (usuario.equals("unis") && senha.equals("unis")) {
            return true;
        }

        return false;
    }

    private void salvarPreferencias() {
        SharedPreferences credenciais = getSharedPreferences("credenciais", MODE_PRIVATE);
        SharedPreferences.Editor editor = credenciais.edit();

        editor.putString("credencial_usuario", txtUsuario.getText().toString());
        editor.commit();
    }

    private void abreListaDeTarefas() {
        startActivity(new Intent(
                this,
                ListaTarefasActivity.class
        ));
    }
}








