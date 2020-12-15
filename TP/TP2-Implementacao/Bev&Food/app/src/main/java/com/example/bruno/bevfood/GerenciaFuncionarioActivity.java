package com.example.bruno.bevfood;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class GerenciaFuncionarioActivity extends ListActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private SQLiteDatabase database;
    private CursorAdapter dataSource;
    String user;
    ListView listView;
    UsuarioDAO usuarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerencia_funcionario);
//        setListAdapter(new InvitePresenter(this));
/*
        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
        Cursor user = (Cursor) usuarioDAO.getUsuario();

        String[] colunas = {"id", "nome", "email", "cargo"};
      //  String where ={"id = " + id};


      //  user = database.query("Usuarios", colunas, where, null, null, null, null, null);
//        Cursor user = database.query("Usuarios", colunas, null, null, null, null, null);
        if (user.getCount() > 0){
            //cria cursor que será exibido na tela, nele serã   o exibidos
            //todos os contatos cadastrados
            dataSource = new SimpleCursorAdapter(this, R.layout.row, user,
                    colunas, new int[] { R.id.tvNome, R.id.tvCargo , R.id.tvEmail });

            //relaciona o dataSource ao próprio listview
            listView.setAdapter(dataSource);
        }else{
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
        }
     /*   listView = (ListView) findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(this, "Item clcado", Toast.LENGTH_SHORT).show();
            //    Intent intent = new Intent(this, ExibirPerfilUsuarioActivity.class);
             //   startActivity(intent);
            }
        });


       Intent intent = getIntent();
        user = intent.getStringExtra("usuario");

        ArrayList<Usuario> itens = new ArrayList<>();

        UsuarioDAO userDAO = new UsuarioDAO(this);
        ArrayList<Usuario> usuarios = userDAO.listaUsuarios();

        itens = usuarios;

        recycler = (RecyclerView) findViewById(R.id.reciclador);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new UsuarioAdapter(itens);
        recycler.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_user:
                Intent intent = new Intent(this, CadastroUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.sair:
                DeletaShared();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void DeletaShared(){
        SharedPreferences.Editor Ed = getSharedPreferences("preferencias_1", Context.MODE_PRIVATE).edit();
        Ed.clear();
        Ed.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


*/}}
