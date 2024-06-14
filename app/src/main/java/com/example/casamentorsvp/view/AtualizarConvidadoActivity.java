package com.example.casamentorsvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;
import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAOImpl;
import com.example.casamentorsvp.model.ConvidadoNaoEncontradoException;

public class AtualizarConvidadoActivity extends AppCompatActivity {

    private EditText nomeEditText, emailEditText, telefoneEditText, editTextPreferencias;
    private Button atualizarButton;
    private ConvidadoDAOImpl convidadoDAO;
    private Convidado convidadoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_convidado);

        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        editTextPreferencias = findViewById(R.id.editTextPreferencias);
        atualizarButton = findViewById(R.id.atualizarButton);

        convidadoDAO = new ConvidadoDAOImpl(this);

        int convidadoId = getIntent().getIntExtra("convidado_id", -1);
        if (convidadoId != -1) {
            try {
                convidadoAtual = convidadoDAO.buscarConvidadoPorId(convidadoId);
                nomeEditText.setText(convidadoAtual.getNome());
                emailEditText.setText(convidadoAtual.getEmail());
                telefoneEditText.setText(convidadoAtual.getTelefone());
                editTextPreferencias.setText(convidadoAtual.getPreferenciasAlimentares());
            } catch (ConvidadoNaoEncontradoException e) {
                // Handle exception
            }
        }

        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String telefone = telefoneEditText.getText().toString().trim();
                String preferencias = editTextPreferencias.getText().toString().trim();

                convidadoAtual.setNome(nome);
                convidadoAtual.setEmail(email);
                convidadoAtual.setTelefone(telefone);
                convidadoAtual.setPreferenciasAlimentares(preferencias);

                convidadoDAO.updateConvidado(convidadoAtual);
                finish();
            }
        });
    }
}
