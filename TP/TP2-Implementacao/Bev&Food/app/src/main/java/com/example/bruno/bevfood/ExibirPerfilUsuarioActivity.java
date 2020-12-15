package com.example.bruno.bevfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExibirPerfilUsuarioActivity extends AppCompatActivity {

    String usuario;
    long id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_perfil_usuario);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String cargo = intent.getStringExtra("cargo");
        String email = intent.getStringExtra("email");
      //  byte[] image = intent.getByteArrayExtra("foto");
        usuario = intent.getStringExtra("usuario");



        id = intent.getLongExtra("id", 0);
        usuario = intent.getStringExtra("usuario");

        final UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Usuario usuario1 = usuarioDAO.getUsuario(id);




    TextView UserNome = (TextView)findViewById(R.id.textView7);

    TextView UserCargo = (TextView)findViewById(R.id.textView8);

    TextView UserEmail= (TextView)findViewById(R.id.textView9);

    final TextView textNome = (TextView) findViewById(R.id.textValor2);
        textNome.setText(nome);

    final TextView textCargo = (TextView) findViewById(R.id.textCargo);
        textCargo.setText(cargo);

    final TextView textEmail = (TextView) findViewById(R.id.textEmail);
        textEmail.setText(email);

/*    final byte[] byteArray = usuario1.getFoto();

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

        ImageView imageUser = (ImageView) findViewById(R.id.imageFuncionario);
        imageUser.setImageBitmap(bmp);
    */

        Button btEditar = (Button) findViewById(R.id.btEditar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
                intent.putExtra("nome", textNome.getText().toString());
                intent.putExtra("cargo", textCargo.getText().toString());
                intent.putExtra("email", textEmail.getText().toString());
            //    intent.putExtra("foto", byteArray);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        Button btRemover = (Button) findViewById(R.id.btRemover);
        btRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder buildOpc = new AlertDialog.Builder(v.getContext());
                buildOpc.setTitle("Remover");
                buildOpc.setMessage("Deseja realmente remover?");
                buildOpc.setIcon(android.R.drawable.ic_dialog_alert);
                buildOpc.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //               Toast.makeText(getBaseContext(), "Teste", Toast.LENGTH_LONG).show();
                        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
                        if (usuarioDAO.deletaUsuario(id)) {
                            Toast.makeText(getBaseContext(), "Perfil removido com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            intent.putExtra("usuario", usuario);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Erro ao remover!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                buildOpc.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //    dialog.dismiss();
                    }
                });
                AlertDialog alerta = buildOpc.create();
                alerta.show();
            }
        });


    // imagePet.setImageResource(image);
}


}
