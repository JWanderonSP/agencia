package br.com.tech4meagen.agencia.shared;

import br.com.tech4meagen.agencia.model.Veiculo;

public record PedidoCompletoDto(String id, String idVeiculo,Veiculo veiculo, String modelo, String cor, String categoria, Double valor) {


}