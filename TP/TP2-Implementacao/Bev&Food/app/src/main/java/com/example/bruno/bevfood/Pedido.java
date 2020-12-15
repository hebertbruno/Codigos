package com.example.bruno.bevfood;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
   // private Integer codigo;
    private int mesa;
    private String dataPedido;
    private boolean status;
    private Double total;
    private List<Integer> id_produtos;

    public boolean isStatus() {
        return status;
    }



    public Pedido(int mesa, String dataPedido, boolean status, Double total, List<Integer> id_produtos) {
        this.mesa = mesa;
        this.dataPedido = dataPedido;
        this.status = status;
        this.total = total;
        this.id_produtos = id_produtos;
    }

    public Pedido(/*Integer codigo,*/ int mesa, String dataPedido, boolean status, Double total) {
     //   this.codigo = codigo;
        this.mesa = mesa;
        this.dataPedido = dataPedido;
        this.status = status;
        this.total = total;
    }

    public Pedido() {

    }

/*    public Pedido(Integer codigo) {
        this.codigo = codigo;
    }
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }*/
    public int getMesa() {
        return mesa;
    }
    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }


}
