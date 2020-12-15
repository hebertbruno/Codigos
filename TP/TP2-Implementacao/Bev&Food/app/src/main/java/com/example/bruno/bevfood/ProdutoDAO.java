package com.example.bruno.bevfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;

public class ProdutoDAO {
    private SQLiteDatabase bancoDeDados;

    public ProdutoDAO(Context context){
        this.bancoDeDados = (new BancoDeDados(context).getWritableDatabase());
    }

    public boolean addProduto(Produto produto) {
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("tipo", produto.getTipoProduto());
        valores.put("valor", produto.getValor());
        valores.put("descricao", produto.getDescricao());
        valores.put("status", produto.getStatus());

        resultado = bancoDeDados.insert("Produtos", null, valores);
        bancoDeDados.close();

        if (resultado == -1)
            return false;
        else {
            produto.setId(resultado);
            return true;
        }

    }

    public boolean alteraProduto(Produto produto) {
        ContentValues valores = new ContentValues();
        String where;
        int linha;

        where = "id = " + produto.getId();

        valores.put("tipo", produto.getTipoProduto());
        valores.put("valor", produto.getValor());
        valores.put("descricao", produto.getDescricao());
        valores.put("status", produto.getStatus());

        linha = bancoDeDados.update("Produtos", valores, where, null);
        bancoDeDados.close();

        if (linha == 1)
            return true;
        else
            return false;
    }

    public ArrayList<Produto> listaProdutos() {
        ArrayList<Produto> produtosEncontrados = new ArrayList<Produto>();

        String sql = "SELECT tipo, valor,  id FROM Produtos ";
        Cursor cursor = this.bancoDeDados.rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Produto p = new Produto();
                p.setTipoProduto(cursor.getString(cursor.getColumnIndex("tipo")));
                p.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
 //               p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
//                p.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                produtosEncontrados.add(p);
            }
        } catch (SQLiteException e) {
            Log.e("BevFood.db", e.getMessage());
        }
        return produtosEncontrados;
    }

    public ArrayList<Produto> listaProdutosDisponiveis() {
        ArrayList<Produto> produtosEncontrados = new ArrayList<Produto>();

        String sql = "SELECT tipo, valor,  id FROM Produtos WHERE status = 'Disponivel'";
        Cursor cursor = this.bancoDeDados.rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Produto p = new Produto();
                p.setTipoProduto(cursor.getString(cursor.getColumnIndex("tipo")));
                p.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                //               p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
//                p.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                produtosEncontrados.add(p);
            }
        } catch (SQLiteException e) {
            Log.e("BevFood.db", e.getMessage());
        }
        return produtosEncontrados;
    }

    public Produto getProduto(long id) {
        Produto p = null;
        Cursor cursor;
        String[] campos = {"tipo", "valor", "descricao", "status", "id"};
        String where = "id = " + id;

        cursor = bancoDeDados.query("Produtos", campos, where, null, null, null, null, null);

        if (cursor.moveToNext()) {
            p = new Produto();
            p.setTipoProduto(cursor.getString(cursor.getColumnIndex("tipo")));
            p.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
            p.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            p.setStatus(cursor.getString(cursor.getColumnIndex("status")));
        }
        cursor.close();
        return p;
    }

    public boolean deletaProduto(long id){
        String where = "id = " + id;
        int qtLinhas;

        qtLinhas = bancoDeDados.delete("Produtos", where, null);
        bancoDeDados.close();

        if (qtLinhas == 1)
            return true;
        else
            return false;
    }
}
