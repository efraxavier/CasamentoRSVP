package com.example.casamentorsvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.casamentorsvp.R;
import com.example.casamentorsvp.controller.ConvidadoController;
import com.example.casamentorsvp.model.Convidado;

public class ConvidadoActivity extends AppCompatActivity {

    private ConvidadoController convidadoController;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextTelefone;
    private EditText editTextPreferencias;
    private CheckBox checkBoxRsvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convidado);

        convidadoController = new ConvidadoController(this);
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextPreferencias = findViewById(R.id.editTextPreferencias);
        checkBoxRsvp = findViewById(R.id.checkBoxRsvp);

        findViewById(R.id.buttonSalvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarConvidado();
            }
        });
    }

    private void salvarConvidado() {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String preferencias = editTextPreferencias.getText().toString();
        boolean rsvp = checkBoxRsvp.isChecked();

        Convidado convidado = new Convidado(0, nome, email, telefone, rsvp, preferencias);
        convidadoController.adicionarConvidado(convidado);

        finish(); // Fecha a Activity após salvar
    }
}
