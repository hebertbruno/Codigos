package com.example.bruno.bevfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by bruno on 03/06/2018.
 */
public class UsuarioDAO {
    private SQLiteDatabase bancoDeDados;


    public UsuarioDAO(Context context) {
        this.bancoDeDados = (new BancoDeDados(context)).getWritableDatabase();
    }
    public boolean addUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("cargo", usuario.getCargo());
        valores.put("foto", usuario.getFoto());

        resultado = bancoDeDados.insert("Usuarios", null, valores);

        if (resultado == -1)
            return false;
        else
            return true;
    }

    public Usuario getUsuario(String email, String senha) {
        Usuario usuario = null;

        String where = "email = ? AND senha = ?";
        String[] colunas = {"nome", "email", "senha", "cargo", "foto", "id"};
        String[] argumentos = {email, senha};

        Cursor cursor = bancoDeDados.query("Usuarios", colunas, where, argumentos, null, null, null);
        if (cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setNome(cursor.getString(0));
            usuario.setEmail(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setCargo(cursor.getString(3));
            usuario.setFoto(cursor.getBlob(4));
            usuario.setId(cursor.getInt(5));
        }

        cursor.close();
        return usuario;
    }

    public Usuario getUsuario() {
        Usuario usuario = null;

      //  String where = "email = ? AND senha = ?";
        String[] colunas = {"nome", "email", "cargo"};

        Cursor cursor = bancoDeDados.query("Usuarios", colunas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setNome(cursor.getString(0));
            usuario.setEmail(cursor.getString(1));
//            usuario.setSenha(cursor.getString(2));
            usuario.setCargo(cursor.getString(3));
            usuario.setFoto(cursor.getBlob(4));
            usuario.setId(cursor.getInt(5));
        }

        cursor.close();
        return usuario;
    }

    public ArrayList<Usuario> listaUsuarios() {
        ArrayList<Usuario> userEncontrados = new ArrayList<Usuario>();

        String sql = "SELECT nome, cargo, foto FROM Usuarios ";
        Cursor cursor = this.bancoDeDados.rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Usuario user = new Usuario();
                user.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                user.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));
                user.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));
                userEncontrados.add(user);
            }
        } catch (SQLiteException e) {
            Log.e("BevFood.db", e.getMessage());
        }
        return userEncontrados;
    }



    public Usuario getUsuario(long id) {
        Usuario user = null;
        Cursor cursor;
        String[] campos = { "nome", "cargo", "email", "foto"};
        String where = "id = " + id;

        cursor = bancoDeDados.query("Usuarios", campos, where, null, null, null, null, null);

        if (cursor.moveToNext()) {
            user = new Usuario();
            user.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            user.setCargo(cursor.getString(cursor.getColumnIndex("cargo")));
            user.setCargo(cursor.getString(cursor.getColumnIndex("email")));
            user.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));

        }
        cursor.close();
        return user;
    }

    public boolean deletaUsuario(long id){
        String where = "id = " + id;
        int qtLinhas;

        qtLinhas = bancoDeDados.delete("Usuarios", where, null);
        bancoDeDados.close();

        if (qtLinhas == 1)
            return true;
        else
            return false;
    }

}

