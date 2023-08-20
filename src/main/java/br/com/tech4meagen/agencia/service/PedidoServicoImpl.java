package br.com.tech4meagen.agencia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4meagen.agencia.httpClient.AgenciaClient;
import br.com.tech4meagen.agencia.model.Pedido;
import br.com.tech4meagen.agencia.model.Veiculo;
import br.com.tech4meagen.agencia.repository.PedidoRepository;
import br.com.tech4meagen.agencia.shared.PedidoCompletoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PedidoServicoImpl implements PedidoServico {
    @Autowired
    private PedidoRepository repositorio;
    
    //client aqui
    @Autowired
    private AgenciaClient agencia;

   @Override
    public PedidoCompletoDto CadastrarPedido(PedidoCompletoDto dto) {
        Pedido novopedido = new Pedido(dto);
        repositorio.save(novopedido);
        return new PedidoCompletoDto(novopedido.getId(),novopedido.getIdVeiculo(), null, novopedido.getModelo(),novopedido.getCor(), novopedido.getCategoria(), novopedido.getValor());
    }


   @Override
    public List<PedidoCompletoDto> obterPedido() {
        return repositorio.findAll()
            .stream()
            .map(p -> new PedidoCompletoDto(p.getId(),p.getIdVeiculo(), null, p.getModelo(), p.getCor(),p.getCategoria(), p.getValor()))
            .toList();
    }
    @Override
    public void excluirPedido(String id) {
        repositorio.deleteById(id);
    }
    
    @Override
    public Optional<PedidoCompletoDto> atualizarPedidoPorId (String id, PedidoCompletoDto dto){
        Optional<Pedido> pedidoatualizar = repositorio.findById(id);
        
        if (pedidoatualizar.isPresent()) {
            Pedido pedidoAtualizar = new Pedido();
            pedidoAtualizar.setId(id);
            repositorio.save(pedidoAtualizar);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }
    
    @CircuitBreaker(name = "obterVeiculo", fallbackMethod = "fallbackPedidosPorId")
    @Override
    public Optional<PedidoCompletoDto> obterPedidoPorId(String id) {
        Optional<Pedido> pedido = repositorio.findById(id);

        if (pedido.isPresent()) {
            Veiculo veiculoatual = agencia.obterVeiculo (pedido.get().getIdVeiculo());
            PedidoCompletoDto pedidoComVeiculo = 
            new PedidoCompletoDto(pedido.get().getId(),
             pedido.get().getIdVeiculo(),veiculoatual, pedido.get().getModelo(),pedido.get().getCor(),pedido.get().getCategoria(),pedido.get().getValor());
            return Optional.of(pedidoComVeiculo);
        } else {
            return Optional.empty();
  
}
    }
public Optional<PedidoCompletoDto> fallbackPedidosPorId(String id, Exception e) {
        Optional<Pedido> pedido = repositorio.findById(id);

        if (pedido.isPresent()) {
            PedidoCompletoDto pedidoComVeiculo = 
            new PedidoCompletoDto(pedido.get().getId(),
             pedido.get().getIdVeiculo(),null, pedido.get().getModelo(),pedido.get().getCor(),pedido.get().getCategoria(),pedido.get().getValor());
            return Optional.of(pedidoComVeiculo);
        } else {
            return Optional.empty();
  
}

}
}
