package br.edu.unis.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.edu.unis.listadetarefas.R;

public class SplashActivity extends AppCompatActivity {

    public static final int TEMPO_DE_ATRASO = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        carregando();
    }

    private void carregando() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                abreListaTarefas();
            }
        }, TEMPO_DE_ATRASO);
    }


    private void abreListaTarefas() {
        Intent intent = new Intent(
            this,
            ListaTarefasActivity.class
        );
        startActivity(intent);
    }
}