package com.example.bruno.bevfood;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import livroandroid.lib.fragment.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        setSupportActionBar(toolbar);

        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");


        byte[] array = usuario.getFoto();
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(array, 0, array.length);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navHeaderView = navigationView.getHeaderView(0);
      //  ImageView imageView = (ImageView) navHeaderView.findViewById(R.id.imageView);
        TextView txt_email = (TextView) navHeaderView.findViewById(R.id.textEmail);
        TextView txt_username = (TextView) navHeaderView.findViewById(R.id.textUsuario);
       // imageView.setImageBitmap(bitmapImage);
        txt_email.setText(usuario.getEmail());
        txt_username.setText(usuario.getNome());

      /*  ;
        TextView headerUserName = (TextView) navHeaderView.findViewById(R.id.textEmail);
        ImageView imageView = (ImageView) navHeaderView.findViewById(R.id.imageView);
        headerUserName.setText(usuario.getEmail());*/





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

     //   NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.novo_user:
                intent = new Intent(this, CadastroUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.sair:

                if(item.isChecked())item.setChecked(false);
                else item.setChecked(true);
                DeletaShared();
                return true;
            case R.id.novo_produto:
                 intent = new Intent(this, CadastroProdutoActivity.class);
                startActivity(intent);

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
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_funcionario) {
            setTitle("Funcionario");
            FuncionarioFragment funcionarioFragment = new FuncionarioFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, funcionarioFragment).commit();
        } else if (id == R.id.nav_produto) {
            setTitle("Produto");
            ProdutoFragment produtoFragment = new ProdutoFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, produtoFragment).commit();
        } else if (id == R.id.nav_cardapio) {
            setTitle("Cardapio");
            CardapioFragment cardapioFragment = new CardapioFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, cardapioFragment).commit();
        } else if (id == R.id.nav_pedido) {
            setTitle("Pedidos");
            PedidoFragment pedidoFragment = new PedidoFragment();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment, pedidoFragment).commit();
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
