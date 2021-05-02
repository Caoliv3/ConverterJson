package br.com.boavista.Json.usecases;

import br.com.boavista.Json.adapters.outbound.WriteFile;
import br.com.boavista.Json.models.RestAS400;
import br.com.boavista.Json.models.RestJson;
import br.com.boavista.Json.models.RestProtesto;
import br.com.boavista.Json.models.RestSP;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JsonUseCase {

    private String arqEntrada;
    private String arqSaida;


    public JsonUseCase(String arqEntrada, String arqSaida) {

        this.arqEntrada =arqEntrada;
        this.arqSaida = arqSaida;
    }

    public RestJson lerJson()  {

        Gson gson = new Gson();
        JsonReader buffer = null;
        try {
            buffer = new JsonReader(new FileReader(this.arqEntrada));
        } catch (FileNotFoundException e) {
            System.out.printf("Erro na leitura do arquivo Json" + e.getMessage());
        }
        RestJson data = gson.fromJson(buffer, RestJson.class);
        try {
            buffer.close();
        } catch (IOException e) {
            System.out.printf("Erro na close do arquivo Json" + e.getMessage());
        }

        return data;
    }

    public void geraSaida(RestJson restJson) {
        List<RestSP> restSP = restJson.getData();
        List<RestProtesto> restProtestos = new ArrayList<>();
        WriteFile  writeFile = new WriteFile(this.arqSaida);
        String idCartorio;

        for (RestSP sp: restSP) {
            restProtestos = sp.getProtestos();
            idCartorio = sp.getCodigo();
            int i =0;
            for (RestProtesto protesto : restProtestos) {
                restProtestos = sp.getProtestos();
                RestAS400 as400 = RestAS400.builder()
                        .documento("CNPJ" + restProtestos.get(i).getCpfCnpj())
                        .codigoRetorno("200")
                        .dtProtesto(formataData(restProtestos.get(i).getDataProtesto()))
                        .valorProtesto(String.format("%016d",Long.valueOf(restProtestos.get(i).getValor().replace(",", ""))))
                        .dtVencimento(formataData(restProtestos.get(i).getDataVencimento()))
                        .DDD(String.format("%-2s", ""))
                        .telefone(String.format("%-11s",""))
                        .fonte("153")
                        .origem("00044")
                        .ufed("SP")
                        .codCartorio(idCartorio)
                        .especie(String.format("%-10s", restProtestos.get(i).getEspecie()))
                        .build();
                i++;
                writeFile.WriteLine(as400.toString() +  "\n");
            }
        }
        writeFile.close();
    }

    public static String formataData(String d) {

        SimpleDateFormat formatoOrigem = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = formatoOrigem.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatoDestino = new SimpleDateFormat("dd/MM/yyyy");
        String formatoAnoMesDia = formatoDestino.format(data);

        return formatoAnoMesDia;
    }

}
