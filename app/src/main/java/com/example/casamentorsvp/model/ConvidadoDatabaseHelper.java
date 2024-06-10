package com.example.casamentorsvp.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ConvidadoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "casamento.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONVIDADOS = "convidados";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TELEFONE = "telefone";
    private static final String COLUMN_RSVP = "rsvp";
    private static final String COLUMN_PREFERENCIAS_ALIMENTARES = "preferencias_alimentares";

    public ConvidadoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONVIDADOS_TABLE = "CREATE TABLE " + TABLE_CONVIDADOS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_TELEFONE + " TEXT, "
                + COLUMN_RSVP + " INTEGER, "
                + COLUMN_PREFERENCIAS_ALIMENTARES + " TEXT)";
        db.execSQL(CREATE_CONVIDADOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONVIDADOS);
        onCreate(db);
    }

    public void addConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, convidado.getNome());
        values.put(COLUMN_EMAIL, convidado.getEmail());
        values.put(COLUMN_TELEFONE, convidado.getTelefone());
        values.put(COLUMN_RSVP, convidado.isRsvp() ? 1 : 0);
        values.put(COLUMN_PREFERENCIAS_ALIMENTARES, convidado.getPreferenciasAlimentares());

        db.insert(TABLE_CONVIDADOS, null, values);
        db.close();
    }

    public Convidado getConvidado(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONVIDADOS, new String[]{COLUMN_ID,
                        COLUMN_NOME, COLUMN_EMAIL, COLUMN_TELEFONE, COLUMN_RSVP, COLUMN_PREFERENCIAS_ALIMENTARES}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        @SuppressLint("Range") Convidado convidado = new Convidado(
                Integer.parseInt(cursor.getString(cursor.getInt(0))),
                cursor.getString(cursor.getColumnIndex(String.valueOf(cursor.getInt(1)))),
                cursor.getString(cursor.getColumnIndex(String.valueOf(cursor.getInt(2)))),
                cursor.getString(cursor.getColumnIndex(String.valueOf(cursor.getInt(3)))),
                cursor.getInt(cursor.getColumnIndex(String.valueOf(cursor.getInt(4) == 1))) > 0,
                cursor.getString(cursor.getColumnIndex(String.valueOf(cursor.getInt(5)))));
        cursor.close();
        return convidado;
    }

    public List<Convidado> getAllConvidados() {
        List<Convidado> convidadoList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONVIDADOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String nome = cursor.getString(cursor.getColumnIndex(COLUMN_NOME));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String telefone = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONE));
                boolean rsvp = cursor.getInt(cursor.getColumnIndex(COLUMN_RSVP)) > 0;
                String preferenciasAlimentares = cursor.getString(cursor.getColumnIndex(COLUMN_PREFERENCIAS_ALIMENTARES));

                // Cria um novo objeto Convidado com os dados do cursor
                Convidado convidado = new Convidado(id, nome, email, telefone, rsvp, preferenciasAlimentares);
                convidado.setId(id);

                // Adiciona o objeto Convidado à lista
                convidadoList.add(convidado);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return convidadoList;
    }


    public int updateConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, convidado.getNome());
        values.put(COLUMN_EMAIL, convidado.getEmail());
        values.put(COLUMN_TELEFONE, convidado.getTelefone());
        values.put(COLUMN_RSVP, convidado.isRsvp() ? 1 : 0);
        values.put(COLUMN_PREFERENCIAS_ALIMENTARES, convidado.getPreferenciasAlimentares());

        return db.update(TABLE_CONVIDADOS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(convidado.getId())});
    }

    public void deleteConvidado(Convidado convidado) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONVIDADOS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(convidado.getId())});
        db.close();
    }
}
