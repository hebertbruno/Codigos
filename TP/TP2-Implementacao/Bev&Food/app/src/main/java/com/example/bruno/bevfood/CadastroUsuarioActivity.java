package com.example.bruno.bevfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private String APP_DIRECTORY ="myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;
    private ImageView imageUsuario;
    private String[] cargosUsuario = {" ", "Gerente","Garcom","Cozinheiro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        // Spinner contendo os cargos possiveis dos funcionarios
        Spinner spinnerCargos = (Spinner) findViewById(R.id.spinnerCargos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cargosUsuario);
        spinnerCargos.setAdapter(adapter);

        imageUsuario = (ImageView) findViewById(R.id.editFoto);
        //FloatingButton para adicionar foto
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Tirar Foto", "Escolher na Galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(CadastroUsuarioActivity.this);

                builder.setTitle("Escolha uma opção");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selecao) {
                        if (options[selecao] == "Tirar Foto")
                            openCamera();
                        else if (options[selecao] == "Escolher na Galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Selecionar App de Imagem"), SELECT_PICTURE);
                        } else if (options[selecao] == "Cancelar")
                            dialog.dismiss();
                    }
                });
                AlertDialog alerta = builder.create();
                alerta.show();
            }
        });




        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editNome = (EditText) findViewById(R.id.editNome);
                EditText editEmail = (EditText) findViewById(R.id.editEmail);
                EditText editSenha = (EditText) findViewById(R.id.editSenha);
                Spinner editCargo = (Spinner) findViewById(R.id.spinnerCargos);
                EditText editConfirme = (EditText) findViewById(R.id.editConfirme);
                ImageView editFoto = (ImageView) findViewById(R.id.editFoto);

                /* converter ImageView para Bitmap */
                editFoto.setDrawingCacheEnabled(true);
                editFoto.buildDrawingCache();
                Bitmap bitmap = editFoto.getDrawingCache();

                /* converter Bitmap para ByteArray */
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();



                Usuario usuario = new Usuario(editNome.getText().toString(), editEmail.getText().toString(),
                        editSenha.getText().toString(), editConfirme.getText().toString(), editCargo.getSelectedItem().toString(), byteArray);

                if (usuario.getNome() == null || usuario.getNome().trim().equals("") || usuario.getEmail() == null ||
                        usuario.getEmail().trim().equals("") || usuario.getSenha() == null || usuario.getSenha().trim().equals("") ||
                        usuario.getConfirmeSenha() == null || usuario.getConfirmeSenha().trim().equals("") || usuario.getCargo() == null || usuario.getCargo().trim().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Os campos NOME, SENHA, CARGO e CONFIRME A SENHA são OBRIGATORIOS", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (usuario.getSenha().equals(usuario.getConfirmeSenha()) == false) {
                    Toast toast = Toast.makeText(getApplicationContext(), "A senha confirmada é diferente da original", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    if (usuario.validarEmail(usuario.getEmail()).equals("invalido")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Informe um e-mail válido", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                        if (usuarioDAO.addUsuario(usuario))
                            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY +File.separator + TEMPORAL_PICTURE_NAME;
        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;
            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {

                    Uri path = data.getData();
                    try {
                        Bitmap  mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                        Bitmap imagemReduzida = mBitmap.createScaledBitmap(mBitmap, 200, 200, true);
                        imageUsuario.setImageBitmap(imagemReduzida);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        Bitmap imagemReduzida = bitmap.createScaledBitmap(bitmap, 200, 200, true);
        imageUsuario.setImageBitmap(imagemReduzida);
        girarFoto(imageUsuario);
    }

    public void girarFoto(View v){
        imageUsuario.setRotation(90);
    }


    public void onClick(View view) {
    }
}

