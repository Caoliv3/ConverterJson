package br.com.boavista.Json.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestAS400 {

    private String documento;
    private String codigoRetorno = "200";
    private String dtProtesto = "";
    private String valorProtesto = "";
    private String dtVencimento = "";
    private String DDD = "";
    private String telefone = "";
    private String fonte = "";
    private String origem = "";
    private String ufed = "";
    private String codCartorio = "";
    private String especie = "";


    @Override
    public String toString() {
        return    documento    + codigoRetorno
                + dtProtesto   + valorProtesto
                + dtVencimento + DDD
                + telefone     + fonte
                + origem       + ufed
                + codCartorio  + especie;
    }
}
