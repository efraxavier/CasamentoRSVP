package com.example.casamentorsvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConvidadoDAOImpl extends ConvidadoDatabaseHelper implements IConvidadoDAO {

    private static final String TAG = "ConvidadoDAOImpl";
    private static final String COLUMN_RSVP = "rsvp";

    public ConvidadoDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void addConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", convidado.getNome());
        values.put("email", convidado.getEmail());
        values.put("telefone", convidado.getTelefone());
        values.put(COLUMN_RSVP, convidado.isRsvp() ? 1 : 0);
        values.put("preferencias_alimentares", convidado.getPreferenciasAlimentares()); // Adicionando preferencias alimentares

        long id = db.insert("convidados", null, values);
        if (id != -1) {
            Log.d(TAG, "Convidado inserido com sucesso: " + convidado.toString());
        } else {
            Log.e(TAG, "Erro ao inserir convidado: " + convidado.toString());
        }
        db.close();
    }

    @Override
    public Convidado buscarConvidadoPorId(int id) throws ConvidadoNaoEncontradoException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("convidados", new String[]{"id", "nome", "email", "telefone", COLUMN_RSVP, "preferencias_alimentares"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Convidado convidado = new Convidado(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1,
                    cursor.getString(5)
            );
            cursor.close();
            db.close();
            Log.d(TAG, "Convidado recuperado com sucesso: " + convidado.toString());
            return convidado;
        } else {
            db.close();
            Log.e(TAG, "Erro ao recuperar convidado com ID: " + id);
            throw new ConvidadoNaoEncontradoException("Convidado não encontrado com o ID: " + id);
        }
    }

    @Override
    public List<Convidado> getAllConvidados() {
        List<Convidado> convidados = new ArrayList<>();
        String selectQuery = "SELECT * FROM convidados";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Convidado convidado = new Convidado(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4) == 1,
                        cursor.getString(5)
                );
                convidados.add(convidado);
                Log.d(TAG, "Convidado recuperado: " + convidado.toString());
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "Nenhum convidado encontrado no banco de dados.");
        }
        cursor.close();
        db.close();
        return convidados;
    }

    @Override
    public int updateConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", convidado.getNome());
        values.put("email", convidado.getEmail());
        values.put("telefone", convidado.getTelefone());
        values.put(COLUMN_RSVP, convidado.isRsvp() ? 1 : 0);
        values.put("preferencias_alimentares", convidado.getPreferenciasAlimentares()); // Atualizando preferencias alimentares

        int rows = db.update("convidados", values, "id = ?", new String[]{String.valueOf(convidado.getId())});
        if (rows > 0) {
            Log.d(TAG, "Convidado atualizado com sucesso: " + convidado.toString());
        } else {
            Log.e(TAG, "Erro ao atualizar convidado: " + convidado.toString());
        }
        db.close();
        return rows;
    }

    @Override
    public void deleteConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("convidados", "id = ?", new String[]{String.valueOf(convidado.getId())});
        if (rows > 0) {
            Log.d(TAG, "Convidado deletado com sucesso: " + convidado.toString());
        } else {
            Log.e(TAG, "Erro ao deletar convidado: " + convidado.toString());
        }
        db.close();
    }
}
