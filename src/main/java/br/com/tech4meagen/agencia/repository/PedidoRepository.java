package br.com.tech4meagen.agencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4meagen.agencia.model.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido,String> {
    
}
