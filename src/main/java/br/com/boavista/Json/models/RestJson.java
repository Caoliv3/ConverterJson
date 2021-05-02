package br.com.boavista.Json.models;


import lombok.Data;

import java.util.List;

@Data
public class RestJson {

    private int code = 0;
    private String codeMessage = "";
    private String documento;
    private String tipoDocumento;
    private String qtdeProtestos;
    private List<RestSP> data;
}
