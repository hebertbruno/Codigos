package com.example.bruno.bevfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String email;
    String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        acessaSharedPreferences();
        super.onCreate(savedInstanceState);
        if (email == "Vazio") {
            setContentView(R.layout.activity_login);
            Button buttonCadastre = (Button) findViewById(R.id.buttonCadastre);

            Button Entrar = (Button) findViewById(R.id.buttonEntrar);

            buttonCadastre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarCadastro();
                }
            });
            Entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    EditText editEmail = (EditText) findViewById(R.id.editEmail);
                    EditText editSenha = (EditText) findViewById(R.id.editSenha);
                    UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                    Usuario usuario = usuarioDAO.getUsuario(editEmail.getText().toString(), editSenha.getText().toString());

                    String SharedEmail = editEmail.getText().toString();
                    String SharedSenha = editSenha.getText().toString();

                    if (usuario != null) {
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Usuário e/ou senha inválidos", Toast.LENGTH_SHORT).show();

                    salvarSharedPreferences(SharedEmail, SharedSenha);
                }
            });
        }
    }

    public void mostrarCadastro() {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }



    public void salvarSharedPreferences(String SharedEmail, String SharedSenha) {
        SharedPreferences prefs = getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);

        SharedPreferences.Editor ed = prefs.edit();

        ed.putString("EMAIL", SharedEmail);
        ed.putString("SENHA", SharedSenha);
        ed.commit();
    }
    private String acessaSharedPreferences() {

        SharedPreferences prefs = getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);

        email = prefs.getString("EMAIL", "Vazio");
        senha = prefs.getString("SENHA", "Vazio");

        return email;
    }
}
