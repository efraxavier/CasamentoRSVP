package com.example.casamentorsvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;
import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAOImpl;

public class AdicionarConvidadoActivity extends AppCompatActivity {

    private EditText nomeEditText, emailEditText, telefoneEditText, editTextPreferencias;
    private Button adicionarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_convidado);

        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        adicionarButton = findViewById(R.id.adicionarButton);
        editTextPreferencias = findViewById(R.id.editTextPreferencias);

        adicionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String telefone = telefoneEditText.getText().toString().trim();
                String preferencias = editTextPreferencias.getText().toString().trim();

                if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty()) {
                    Convidado convidado = new Convidado(0, nome, email, telefone, false, preferencias); // ID será gerenciado pelo banco de dados
                    ConvidadoDAOImpl convidadoDAO = new ConvidadoDAOImpl(AdicionarConvidadoActivity.this);
                    convidadoDAO.addConvidado(convidado);

                    finish(); // Fecha a atividade após adicionar o convidado
                }
            }
        });
    }
}
