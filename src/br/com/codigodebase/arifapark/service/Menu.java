package br.com.codigodebase.arifapark.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import br.com.codigodebase.arifapark.model.RegistroDeEntrada;
import br.com.codigodebase.arifapark.model.Vaga;
import br.com.codigodebase.arifapark.model.Veiculo;

public interface Menu {
	public static void mostrarMenu(Vaga[] vagas, Scanner input, List<RegistroDeEntrada> registros) {

		while (true) {
			System.out.println("1 - Estacionar" + "\n2 - Retirar" + "\n3 - Mostrar vagas livres"
					+ "\n4 - Mostrar vagas ocupadas" + "\n5 - Buscar veiculo" + "\n6 - Mostrar registro"
					+ "\n7 - Mostrar valor do dia" + "\n8 - Sair" + "\n");

			System.out.print("Digite a opcao desejada >>> ");
			
			try {
			int	opcao = input.nextInt();
				System.out.println();
				if (opcao >= 8) {
					System.out.println("Obrigado por usar o sistema ArifaPark! Ate breve.");
					input.close();
					break;
				} else {
					escolherOpcaoMenu(opcao, vagas, input, registros);
				}
			} catch (InputMismatchException e) {
				System.err.println("\n Digite apenas n�meros inteiros! \n");
				input.next();
			}
			System.out.println("=======================================\n");
		}
	}

	public static void escolherOpcaoMenu(int valorMenu, Vaga[] vagas, Scanner input, List<RegistroDeEntrada> registros) {
		switch (valorMenu) {
		case 1 -> estacionar(vagas, input);
		case 2 -> retiraVeiculo(vagas, input, registros);
		case 3 -> System.out.println(ServicoVagas.mostrarVagasLivres(vagas));
		case 4 -> System.out.println(ServicoVagas.mostrarVagasOcupadas(vagas));
		case 5 -> buscaVeiculo(vagas, input);
		case 6 -> System.out.println(ServicoRegistroDeEntrada.retornaRegistroDeEntradas(registros));
		case 7 -> System.out.print(ServicoRegistroDeEntrada.retornaValorDoDia(registros) + "\n\n");
		default -> System.err.println("OPCAO INVALIDA TENTE NOVAMENTE\n");
		}
	}

	public static void buscaVeiculo(Vaga[] vagas, Scanner input) {
		System.out.print("Digite a placa >> ");
		String placa = input.next();
		Integer posicao = ServicoVagas.buscarVeiculo(placa, vagas);
		if (posicao != 51) {
			System.out.println("\nCarro da placa: " + placa + ", est� na vaga: " + (posicao + 1) + "\n");
		} else {
			System.err.println("\nEste carro nao foi encontrado.\n");
		}
	}

	public static void retiraVeiculo(Vaga[] vagas, Scanner input, List<RegistroDeEntrada> registros) {
		boolean horaValida = false;
		System.out.print("Digite a placa do veiculo >>> ");
		String placa = input.next();

		Integer posicao = ServicoVagas.buscarVeiculo(placa, vagas);
		if (posicao == 11) {
			System.err.println("\nCarro nao encontrado\n");
			return;

		} else {
			do {
				String hora = ServicoVagas.validarHora(input);

				String hr[] = hora.split(":");
				Integer hrs = Integer.parseInt(hr[0]);
				Integer mins = Integer.parseInt(hr[1]);

				String horaEntrada = vagas[posicao].getHoraEntrada();
				String hrEnt[] = horaEntrada.split(":");
				Integer hrsEnt = Integer.parseInt(hrEnt[0]);
				Integer minEnt = Integer.parseInt(hrEnt[1]);

				if (hrs < hrsEnt) {
					System.err.println("\nDigite um hor�rio maior que o horario de entrada!\n");
				} else {
					if (hrs == hrsEnt && mins < minEnt) {
						System.err.println("\nDigite um horario maior que o horario de entrada!\n");
					} else {
						horaValida = true;
						System.out.println(ServicoVagas.retirar(posicao, vagas, hora, registros));
					}
				}

			} while (!horaValida);
		}
	}

	public static void estacionar(Vaga[] vagas, Scanner input) {
		Veiculo veiculo = new Veiculo();
		ServicoVagas.cadastrarVeiculo(input, veiculo);
		String hora = ServicoVagas.validarHora(input);
		System.out.println(ServicoVagas.estacionar(veiculo, vagas, hora));
	}

}
