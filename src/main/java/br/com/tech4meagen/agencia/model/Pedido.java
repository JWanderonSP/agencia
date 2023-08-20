package br.com.tech4meagen.agencia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.tech4meagen.agencia.shared.PedidoCompletoDto;


@Document("pedido")
public class Pedido {
    @Id
    private String id;
    private String idVeiculo;
    private String modelo;
    private String categoria;
    private String cor;
    private double valor;

    public String getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(String idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    
    
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public Pedido() {};

    public Pedido(PedidoCompletoDto dto) {
        this.id = dto.id();
        this.idVeiculo = dto.idVeiculo();
        this.modelo = dto.modelo();
        this.cor=dto.cor();
        this.valor = dto.valor();
    }




    
}
