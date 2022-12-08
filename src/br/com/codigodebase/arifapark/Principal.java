package br.com.codigodebase.arifapark;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.codigodebase.arifapark.model.RegistroDeEntrada;
import br.com.codigodebase.arifapark.model.Vaga;
import br.com.codigodebase.arifapark.service.Menu;
import br.com.codigodebase.arifapark.service.ServicoVagas;



public class Principal {

	public static void main(String[] args) {
		// estanciando a classe Vagas para criar todas as vagas do estacionamento
		Vaga[] vagas = ServicoVagas.criarVagas();
		
		// criando uma lista que armazenara todos os registros de entrada de veiculos do estacionamento
		List<RegistroDeEntrada> registros = new ArrayList<RegistroDeEntrada>();
		
		// recebe informacao digitada pelo usuario
		Scanner input = new Scanner(System.in);
		
		System.out.println("Seja bem vindo ao ArifaPark!\n");
		
		Menu.mostrarMenu(vagas, input, registros);
	}

}
