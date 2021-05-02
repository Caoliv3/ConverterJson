package br.com.boavista.Json.adapters.outbound;

import br.com.boavista.Json.models.RestAS400;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	
	private String destino;
	private FileWriter gravaArquivo = null;
		
	public WriteFile(String d) {
		destino = d;
		try {
			gravaArquivo = new FileWriter(destino);
		} catch (IOException e) {
			System.out.println("Arquivo de destino nao encontrada!\n" + e.getMessage());
		}
	}
	
	public void WriteLine (String linha) {
		
		try {
			gravaArquivo.write(linha);
			
		} catch (IOException e) {
			System.out.println("Erro na gravacao do arquivo!\n" + e.getMessage());
		}				
	}

	public void close() {
		try {
			if(gravaArquivo !=null) {
			gravaArquivo.close();
			}			
		} catch (IOException e) {
			System.out.println("Erro no close do arquivo!\n" + e.getMessage());
		}
		
	}
}
