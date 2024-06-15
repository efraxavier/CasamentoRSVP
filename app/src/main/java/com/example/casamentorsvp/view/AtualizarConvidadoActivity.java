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
        atualizarButton = findViewById(R.id.atualizarButton);
        editTextPreferencias = findViewById(R.id.editTextPreferencias);

        convidadoDAO = new ConvidadoDAOImpl(this);

        // Recebe o ID do convidado passado pela BuscarConvidadoActivity
        int convidadoId = getIntent().getIntExtra("convidadoId", -1);
        if (convidadoId != -1) {
            convidadoAtual = convidadoDAO.getConvidado(convidadoId);
            if (convidadoAtual != null) {
                // Preenche os campos com os dados do convidado atual
                nomeEditText.setText(convidadoAtual.getNome());
                emailEditText.setText(convidadoAtual.getEmail());
                telefoneEditText.setText(convidadoAtual.getTelefone());
                editTextPreferencias.setText(convidadoAtual.getPreferenciasAlimentares());
            }
        }

        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (convidadoAtual != null) {
                    String nome = nomeEditText.getText().toString().trim();
                    String email = emailEditText.getText().toString().trim();
                    String telefone = telefoneEditText.getText().toString().trim();
                    String preferencias = editTextPreferencias.getText().toString().trim();

                    convidadoAtual.setNome(nome);
                    convidadoAtual.setEmail(email);
                    convidadoAtual.setTelefone(telefone);
                    convidadoAtual.setPreferenciasAlimentares(preferencias);

                    convidadoDAO.updateConvidado(convidadoAtual);

                    // Envia o convidado atualizado de volta para a BuscarConvidadoActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("convidadoAtualizado", convidadoAtual);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
