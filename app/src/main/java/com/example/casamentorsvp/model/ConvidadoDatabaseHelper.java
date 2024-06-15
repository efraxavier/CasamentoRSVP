package com.example.casamentorsvp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public abstract class ConvidadoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "casamentorsvp.db";
    private static final int DATABASE_VERSION = 2; // Atualizado para a versão 2

    public ConvidadoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONVIDADOS = "CREATE TABLE convidados (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT," +
                "telefone TEXT," +
                "rsvp INTEGER," +
                "preferenciasAlimentares TEXT" + // Adicionando a nova coluna
                ")";
        db.execSQL(CREATE_TABLE_CONVIDADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE convidados ADD COLUMN preferenciasAlimentares TEXT");
        }
    }

    public abstract void addConvidado(Convidado convidado);

    public abstract Convidado getConvidado(int id);

    public abstract List<Convidado> getAllConvidados();

    public abstract int updateConvidado(Convidado convidado);

    public abstract void deleteConvidado(Convidado convidado);
}
