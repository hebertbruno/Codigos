package com.example.bruno.bevfood;

public class Produto {
    private String tipoProduto;
    private Double valor;
    private String descricao;
    private String status;
    private long id;

    public Produto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto(String tipoProduto, Double valor,
                   String descricao, String status) {
        this.tipoProduto = tipoProduto;
        this.valor = valor;
        this.descricao = descricao;
        this.status = status;
    }

    public Produto(String tipoProduto, Double valor, String status) {
        this.tipoProduto = tipoProduto;
        this.valor = valor;
        this.status = status;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}