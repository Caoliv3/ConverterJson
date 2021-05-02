package br.com.boavista.Json.models;

import lombok.Data;

@Data
public class RestProtesto {
    private String cpfCnpj;
    private String dataProtesto;
    private String dataVencimento;
    private String valor;
    private String uf_cartorio;
    private String especie;
}

