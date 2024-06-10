package com.example.casamentorsvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConvidadoDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meuCasamento.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Convidados";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    private static final String RSVP = "rsvp";
    private static final String PREFERENCIAS = "preferenciasAlimentares";

    public ConvidadoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT, " +
                EMAIL + " TEXT, " +
                TELEFONE + " TEXT, " +
                RSVP + " INTEGER, " +
                PREFERENCIAS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void inserirConvidado(Convidado convidado) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, convidado.getId());
        values.put(NOME, convidado.getNome());
        values.put(EMAIL, convidado.getEmail());
        values.put(TELEFONE, convidado.getTelefone());
        values.put(RSVP, convidado.isRsvp() ? 1 : 0);
        values.put(PREFERENCIAS, convidado.getPreferenciasAlimentares());
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    public Convidado buscarConvidadoPorId(int id) throws ConvidadoNaoEncontradoException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        // Verifica se o cursor não está vazio e move para a primeira posição
        if (cursor != null && cursor.moveToFirst()) {
            try {
                // Obtém os dados do cursor e cria um novo objeto Convidado
                Convidado convidado = new Convidado(
                        cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(NOME)),
                        cursor.getString(cursor.getColumnIndex(EMAIL)),
                        cursor.getString(cursor.getColumnIndex(TELEFONE)),
                        cursor.getInt(cursor.getColumnIndex(RSVP)) > 0,
                        cursor.getString(cursor.getColumnIndex(PREFERENCIAS))
                );
                cursor.close();
                return convidado;
            } catch (Exception e) {
                cursor.close();
                throw new ConvidadoNaoEncontradoException("Erro ao ler os dados do cursor para o ID: " + id);
            }
        } else {
            // Se o cursor estiver vazio ou não se mover para a primeira posição, lança a exceção
            throw new ConvidadoNaoEncontradoException("Convidado não encontrado com o ID: " + id);
        }
    }
}
