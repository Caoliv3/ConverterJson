package br.com.boavista.Json.adapters.inbound;


import br.com.boavista.Json.ports.JsonRequestPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JsonConstroller {

    @Autowired
    private JsonRequestPort jsonService;

    @Scheduled(cron = "${service.cron.expression}")
    public void InitJson(){
        jsonService.iniciaJsonService();
        jsonService.converter();
    }
}
