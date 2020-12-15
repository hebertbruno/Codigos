package com.example.bruno.bevfood;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardapioFragment extends Fragment {

    protected RecyclerView recyclerView;

    private List<Produto> produtos;
    private LinearLayoutManager mLayoutManager;
    private String tipo;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private ProdutoDAO produtoDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        ArrayList<Produto> itens = new ArrayList<>();


        produtoDAO = new ProdutoDAO(getContext());
        ArrayList<Produto> produtos = produtoDAO.listaProdutosDisponiveis();

        itens = produtos;



        recycler = (RecyclerView) view.findViewById(R.id.recyclerView);

        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

        adapter = new ProdutoAdapter(itens);
        recycler.setAdapter(adapter);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskUsuarios();
    }

    private void taskUsuarios() {}


}
