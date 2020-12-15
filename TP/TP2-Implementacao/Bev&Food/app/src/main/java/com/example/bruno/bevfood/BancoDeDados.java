package com.example.bruno.bevfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bruno on 03/06/2018.
 */

public class BancoDeDados extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "BevFood.db";


    private static final String CRIA_TABELA_USUARIOS = "CREATE TABLE Usuarios (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL, foto BLOB, cargo TEXT NOT NULL)";

    private static final String CRIA_TABELA_PRODUTOS = "CREATE TABLE Produtos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT NOT NULL, valor REAL NOT NULL, descricao TEXT, status TEXT NOT NULL)";

    private static final String CRIA_TABELA_PEDIDOS = "CREATE TABLE Pedidos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, mesa INTEGER NOT NULL, dataPedido TEXT NOT NULL, status INTEGER NOT NULL, total REAL NOT NULL)";

    public BancoDeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String DELETA_TABELA_USUARIOS = "DROP TABLE IF EXISTS Usuarios";
    private static final String DELETA_TABELA_PRODUTOS = "DROP TABLE IF EXISTS Produtos";
    private static final String DELETA_TABELA_PEDIDOS = "DROP TABLE IF EXISTS Pedidos";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CRIA_TABELA_USUARIOS);
        db.execSQL(CRIA_TABELA_PRODUTOS);
        db.execSQL(CRIA_TABELA_PEDIDOS);
       }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETA_TABELA_USUARIOS);
        db.execSQL(DELETA_TABELA_PRODUTOS);
        db.execSQL(DELETA_TABELA_PEDIDOS);
        onCreate(db);
    }
}
