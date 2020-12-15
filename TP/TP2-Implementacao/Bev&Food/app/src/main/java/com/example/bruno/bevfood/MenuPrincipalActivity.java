package com.example.bruno.bevfood;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button buttonFuncionario = (Button) findViewById(R.id.buttonFuncionarios);


        buttonFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GerenciaFuncionarioActivity.class);
                startActivity(intent);

             }});
        }


        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_user:
                Intent intent = new Intent(this, CadastroUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.sair:

                AlertDialog.Builder alert =
                        new
                                AlertDialog.Builder(
                                this
                        );
                alert.setMessage(
                        "Hello World App v1.0"
                )
                        .setNeutralButton(
                                "Ok"
                                ,
                                null
                        ).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

