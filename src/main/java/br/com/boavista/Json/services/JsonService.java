package br.com.boavista.Json.services;

import br.com.boavista.Json.models.RestJson;
import br.com.boavista.Json.ports.JsonRequestPort;
import br.com.boavista.Json.usecases.JsonUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonService implements JsonRequestPort {

    @Value("${service.data.local.in}")
    private String arqEntrada;
    @Value("${service.data.local.out}")
    private String arqSaida;

    private JsonUseCase jsonUseCase;
    private RestJson restJson;

    @Override
    public void iniciaJsonService(){

        this.restJson = new RestJson();
        this.jsonUseCase  = new JsonUseCase(this.arqEntrada, this.arqSaida);
    }

    @Override
    public void converter(){
        this.restJson = this.jsonUseCase.lerJson();
        this.jsonUseCase.geraSaida(this.restJson);

    }
}
