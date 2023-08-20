package br.com.tech4meagen.agencia.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4meagen.agencia.shared.PedidoCompletoDto;


public interface PedidoServico {
    PedidoCompletoDto CadastrarPedido(PedidoCompletoDto dto);
    List<PedidoCompletoDto> obterPedido();
    Optional<PedidoCompletoDto> obterPedidoPorId(String id);
    void excluirPedido(String id);
    Optional<PedidoCompletoDto> atualizarPedidoPorId(String id, PedidoCompletoDto dto);
    
    
}

