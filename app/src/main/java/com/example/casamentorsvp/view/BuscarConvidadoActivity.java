package com.example.casamentorsvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;
import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAOImpl;
import com.example.casamentorsvp.model.ConvidadoNaoEncontradoException;

public class BuscarConvidadoActivity extends AppCompatActivity {

    private EditText idEditText;
    private Button buscarButton;
    private TextView resultadoTextView;
    private Button atualizarButton;
    private Button deletarButton;

    private ConvidadoDAOImpl convidadoDAO;
    private Convidado convidadoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_convidado);

        idEditText = findViewById(R.id.idEditText);
        buscarButton = findViewById(R.id.buscarButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);
        atualizarButton = findViewById(R.id.atualizarButton);
        deletarButton = findViewById(R.id.deletarButton);

        convidadoDAO = new ConvidadoDAOImpl(this);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idEditText.getText().toString().trim());
                try {
                    convidadoAtual = convidadoDAO.buscarConvidadoPorId(id);
                    resultadoTextView.setText(convidadoAtual.toString());
                    atualizarButton.setVisibility(View.VISIBLE);
                    deletarButton.setVisibility(View.VISIBLE);
                } catch (ConvidadoNaoEncontradoException e) {
                    resultadoTextView.setText(e.getMessage());
                    atualizarButton.setVisibility(View.GONE);
                    deletarButton.setVisibility(View.GONE);
                }
            }
        });

        atualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscarConvidadoActivity.this, AtualizarConvidadoActivity.class);
                intent.putExtra("convidado_id", convidadoAtual.getId());
                startActivity(intent);
            }
        });

        deletarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convidadoDAO.deleteConvidado(convidadoAtual);
                resultadoTextView.setText("Convidado deletado com sucesso!");
                atualizarButton.setVisibility(View.GONE);
                deletarButton.setVisibility(View.GONE);
            }
        });
    }
}
