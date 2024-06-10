package com.example.casamentorsvp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casamentorsvp.R;
import com.example.casamentorsvp.model.Convidado;
import com.example.casamentorsvp.model.ConvidadoDAOImpl;
import java.util.List;

public class ListarConvidadosActivity extends AppCompatActivity {

    private static final String TAG = "ListarConvidadosActivity";
    private ListView listViewConvidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_convidados);

        listViewConvidados = findViewById(R.id.listViewConvidados);

        ConvidadoDAOImpl convidadoDAO = new ConvidadoDAOImpl(this);
        List<Convidado> convidados = convidadoDAO.getAllConvidados();

        if (convidados.isEmpty()) {
            Log.d(TAG, "Nenhum convidado encontrado.");
        } else {
            Log.d(TAG, "Convidados recuperados: " + convidados.size());
        }

        ArrayAdapter<Convidado> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, convidados);
        listViewConvidados.setAdapter(adapter);
    }
}
