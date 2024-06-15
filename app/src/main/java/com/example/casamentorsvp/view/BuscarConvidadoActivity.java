package com.example.casamentorsvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;
import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAOImpl;
import com.example.casamentorsvp.model.ConvidadoNaoEncontradoException;

public class BuscarConvidadoActivity extends AppCompatActivity {

    private EditText idEditText;
    private TextView resultTextView;
    private Button buscarButton, atualizarButton, deletarButton;
    private ConvidadoDAOImpl convidadoDAO;
    private Convidado convidadoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_convidado);

        idEditText = findViewById(R.id.idEditText);
        resultTextView = findViewById(R.id.resultTextView);
        buscarButton = findViewById(R.id.buscarButton);
        atualizarButton = findViewById(R.id.atualizarButton);
        deletarButton = findViewById(R.id.deletarButton);

        convidadoDAO = new ConvidadoDAOImpl(this);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int convidadoId = Integer.parseInt(idEditText.getText().toString().trim());
                convidadoAtual = convidadoDAO.getConvidado(convidadoId);
                if (convidadoAtual != null) {
                    resultTextView.setText(convidadoAtual.toString());
                } else {
                    resultTextView.setText("Convidado não encontrado");
                }
            }
        });

        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (convidadoAtual != null) {
                    Intent intent = new Intent(BuscarConvidadoActivity.this, AtualizarConvidadoActivity.class);
                    intent.putExtra("convidadoId", convidadoAtual.getId());
                    startActivityForResult(intent, 1);
                }
            }
        });

        deletarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (convidadoAtual != null) {
                    convidadoDAO.deleteConvidado(convidadoAtual);
                    resultTextView.setText("Convidado deletado com sucesso");
                    convidadoAtual = null;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                convidadoAtual = (Convidado) data.getSerializableExtra("convidadoAtualizado");
                resultTextView.setText(convidadoAtual.toString());
            }
        }
    }
}
