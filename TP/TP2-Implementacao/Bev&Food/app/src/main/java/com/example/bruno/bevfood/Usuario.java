package com.example.bruno.bevfood;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bruno on 03/06/2018.
 */
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private byte[] foto;
    private String confirmeSenha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public Usuario(String nome, String email, String senha, String confirmeSenha, String cargo, byte[] foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.foto = foto;
        this.confirmeSenha = confirmeSenha;
    }

    public Usuario(String email, String senha, String cargo) {
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getConfirmeSenha() {
        return confirmeSenha;
    }

    public void setConfirmeSenha(String confirmeSenha) {
        this.confirmeSenha = confirmeSenha;
    }

    public String validarEmail(String email) {
        String retorno = "";
        // Expressão Regular para validar E-mail
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (!m.find())
            retorno = "invalido";
        else
            retorno = "valido";
        return retorno;
    }
}
