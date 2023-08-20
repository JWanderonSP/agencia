package br.com.tech4meagen.agencia.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tech4meagen.agencia.model.Veiculo;


    @FeignClient("agencia")
public interface AgenciaClient {
    @RequestMapping(method = RequestMethod.GET, value = "/veiculo/{id}")
    Veiculo obterVeiculo(@PathVariable String id);
}

    

