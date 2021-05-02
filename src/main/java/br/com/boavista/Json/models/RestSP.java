package br.com.boavista.Json.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestSP {
    private String codigo;
    private String atualizacaoData;
    private int quantidade;
    private List<RestProtesto> protestos = new ArrayList<>();

}
