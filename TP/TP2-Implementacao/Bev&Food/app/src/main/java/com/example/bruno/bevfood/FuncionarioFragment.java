package com.example.bruno.bevfood;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.fragment.BaseFragment;

import static java.security.AccessController.getContext;


public class FuncionarioFragment extends BaseFragment {
    protected RecyclerView recyclerView;

    private List<Usuario> usuarios;
    private LinearLayoutManager mLayoutManager;
    private String tipo;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    String usuario;

    private UsuarioDAO usuarioDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funcionario, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        ArrayList<Usuario> itens = new ArrayList<>();


        usuarioDAO = new UsuarioDAO(getContext());
        ArrayList<Usuario> usuarios = usuarioDAO.listaUsuarios();

        itens = usuarios;



        recycler = (RecyclerView) view.findViewById(R.id.recyclerView);

        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

        adapter = new UsuarioAdapter(itens);
        recycler.setAdapter(adapter);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskUsuarios();
    }

    private void taskUsuarios() {
        // Busca os carros
     //   this.usuarios = CarroService.getCarros(getContext(),tipo);
        // Atualiza a lista
      //  recyclerView.setAdapter(new UsuarioAdapter((getContext(), usuarios, onClickUsuario()));
    }
    /*
    private UsuarioAdapter.UsuarioOnClickListener onClickUsuario() {
        return new UsuarioAdapter.UsuarioOnClickListener() {
            @Override

            public void onClickUsuario(View view, int idx) {
                Usuario c = usuarios.get(idx);
                Toast.makeText(getContext(), "Carro: " + c.getNome(), Toast.LENGTH_SHORT).show();
             //   Intent intent = new Intent(getContext(), CarroActivity.class);
             //   intent.putExtra("carro", c);
             //   startActivity(intent);
            }
        };*/
    }




/*
public class FuncionarioFragment extends Fragment {

    public FuncionarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_funcionario, container, false);
    }


}
*/