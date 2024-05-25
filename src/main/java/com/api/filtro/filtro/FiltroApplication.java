package com.api.filtro.filtro;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FiltroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiltroApplication.class, args);


		String diretorio = "/home/meiryanne/Área de Trabalho/filtro";

		try {
			RemoveComments.removerComentarios(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
