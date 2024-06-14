package com.example.casamentorsvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;

public class MainActivity extends AppCompatActivity {

    private Button adicionarConvidadoButton;
    private Button listarConvidadosButton;
    private Button buscarConvidadoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adicionarConvidadoButton = findViewById(R.id.adicionarConvidadoButton);
        listarConvidadosButton = findViewById(R.id.listarConvidadosButton);
        buscarConvidadoButton = findViewById(R.id.buscarConvidadoButton);

        adicionarConvidadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdicionarConvidadoActivity.class));
            }
        });

        listarConvidadosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListarConvidadosActivity.class));
            }
        });

        buscarConvidadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscarConvidadoActivity.class));
            }
        });
    }
}
