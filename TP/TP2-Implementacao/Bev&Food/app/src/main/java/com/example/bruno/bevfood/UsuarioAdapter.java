package com.example.bruno.bevfood;

/*
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.List;

import livroandroid.lib.utils.MaterialUtils;

// Herda de RecyclerView.Adapter e declara o tipo genérico <CarroAdapterV2.CarrosViewHolder>
public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosViewHolder> {
    protected static final String TAG = "livroandroid";
    private final List<Usuario> usuarios;
    private final Context context;

    private UsuarioOnClickListener usuarioOnClickListener;

    public UsuarioAdapter(Context context, List<Usuario> usuarios, UsuarioOnClickListener usuarioOnClickListener) {
        this.context = context;
        this.usuarios = usuarios;
        this.usuarioOnClickListener = usuarioOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.usuarios != null ? this.usuarios.size() : 0;
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_usuario, viewGroup, false);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);

        // Cria o ViewHolder
        UsuariosViewHolder holder = new UsuariosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UsuariosViewHolder holder, final int position) {
        // Atualiza a view
        Usuario user = usuarios.get(position);

        holder.tNome.setText("ABCD");
       // holder.progress.setVisibility(View.VISIBLE);
/*
        Picasso.with(context).load(c.urlFoto).fit().into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });

        // Click
        if (usuarioOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioOnClickListener.onClickUsuario(holder.itemView, position); // A variável position é final
                }
            });

    }

    public interface UsuarioOnClickListener {
        public void onClickUsuario(View view, int idx);
    }

    // ViewHolder com as views
    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;

        public UsuariosViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.img);

        }
    }
}*/


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private ArrayList<Usuario> itens;

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagem;
        public TextView nome;
        public TextView cargo;
        public TextView email;
        public TextView id;


        public UsuarioViewHolder(View v) {
            super(v);
            imagem = (ImageView) v.findViewById(R.id.imageFuncionario);
            nome = (TextView) v.findViewById(R.id.textValor2);
            email = (TextView) v.findViewById(R.id.textEmail);
            id = (TextView) v.findViewById(R.id.textId);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ExibirPerfilUsuarioActivity.class);
                    intent.putExtra("id", Long.valueOf(id.getText().toString()));
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("usuario", nome.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public UsuarioAdapter(ArrayList<Usuario> itens) {
        this.itens = itens;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.funcionario_card, viewGroup, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder viewHolder, int i) {
        Bitmap bmp = BitmapFactory.decodeByteArray(itens.get(i).getFoto(), 0, itens.get(i).getFoto().length);
        viewHolder.imagem.setImageBitmap(bmp);
        viewHolder.nome.setText(itens.get(i).getNome());
        viewHolder.email.setText(itens.get(i).getEmail());
        viewHolder.id.setText(String.valueOf(itens.get(i).getId()));
    }
}
