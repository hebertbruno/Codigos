package com.example.bruno.bevfood;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CadastroProdutoActivity extends AppCompatActivity {
    boolean switchStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);





        Button buttonSalvarProd = (Button) findViewById(R.id.buttonSalvarProd);
        buttonSalvarProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editTipo = (EditText) findViewById(R.id.editNomeProd);
                EditText editValor = (EditText) findViewById(R.id.editValor);
                EditText editDescricao = (EditText) findViewById(R.id.editDescricao);
                Switch swicht = findViewById(R.id.switch1);
                final TextView switchText = (TextView) findViewById(R.id.textView);

//                swicht.setChecked(true);
                swicht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked){
                            switchText.setText("Disponivel");
                        }
                        else {
                            switchText.setText("Indisponivel");
                        }
                    }
                });



                Produto produto = new Produto(editTipo.getText().toString(), Double.parseDouble(editValor.getText().toString()),
                        editDescricao.getText().toString(), switchText.getText().toString());

                if(produto.getTipoProduto() == null || produto.getTipoProduto().trim().equals("")||
                        produto.getValor() == null){
                    Toast toast = Toast.makeText(getApplicationContext(), "Os campos TIPO DE PRODUTO E VALOR são OBRIGATORIOS", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    ProdutoDAO produtoDAO = new ProdutoDAO(getApplicationContext());
                        if (produtoDAO.addProduto(produto))
                            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

        });
    }

    public void onClick(View view) {
    }
}
