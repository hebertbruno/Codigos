package com.example.bruno.bevfood;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProdutoAdapter  extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>  {

    private ArrayList<Produto> itens;

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        public TextView tipoProduto;
        public TextView valor;
        public TextView id;


        public ProdutoViewHolder(View v) {
            super(v);
            tipoProduto = (TextView) v.findViewById(R.id.textNomeProduto2);
            valor = (TextView) v.findViewById(R.id.textValor2);
            id = (TextView) v.findViewById(R.id.textId);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ExibirProdutoActivity.class);
                    intent.putExtra("id", Long.valueOf(id.getText().toString()));
                    intent.putExtra("tipoProduto", tipoProduto.getText().toString());
//                    intent.putExtra("valor", Long.valueOf(valor.getText().toString()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public ProdutoAdapter(ArrayList<Produto> itens) {
        this.itens = itens;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    @Override
    public ProdutoAdapter.ProdutoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.produto_card, viewGroup, false);
        return new ProdutoAdapter.ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProdutoAdapter.ProdutoViewHolder viewHolder, int i) {
        viewHolder.tipoProduto.setText(itens.get(i).getTipoProduto());
        viewHolder.valor.setText(String.valueOf(itens.get(i).getValor()));
        viewHolder.id.setText(String.valueOf(itens.get(i).getId()));
    }
}


