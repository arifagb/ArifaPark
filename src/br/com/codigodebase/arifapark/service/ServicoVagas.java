package br.com.codigodebase.arifapark.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.com.codigodebase.arifapark.model.RegistroDeEntrada;
import br.com.codigodebase.arifapark.model.Vaga;
import br.com.codigodebase.arifapark.model.Veiculo;



public interface ServicoVagas {

	public static Vaga[] criarVagas() {
		Vaga[] vagas = new Vaga[50];
		for (int x = 1; x <= vagas.length; x++) {
			Vaga vaga = new Vaga(x);
			vagas[x - 1] = vaga;
		}
		return vagas;
	}

	public static void cadastrarVeiculo(Scanner input, Veiculo veiculo) {

		System.out.print("Digite o modelo do ve�culo: ");
		String modelo = input.next();
		System.out.print("Digite a placa do ve�culo: ");
		String placa = input.next();
		System.out.print("Digite o nome do propriet�rio: ");
		String proprietario = input.next();
		System.out.print("Digite o documento do propriet�rio: ");
		String documento = input.next();
		
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setProprietario(proprietario);
		veiculo.setDocumento(documento);
	}

	public static String mostrarVagasLivres(Vaga[] vagas) {
		String mensagem = "";
		
		for (int i = 0; i < vagas.length; i++) {
			if (!vagas[i].getOcupado()) {
				mensagem += "A vaga " + vagas[i].getPosicao() + " est� livre\n";
			}
		}
		if (mensagem.equalsIgnoreCase("")) {
			mensagem = "Nao ha nenhuma vaga livre!";
		}
		return mensagem;
	}

	public static String mostrarVagasOcupadas(Vaga[] vagas) {
		String mensagem = "";

		for (int i = 0; i < vagas.length; i++) {
			if (vagas[i].getOcupado()) {
				mensagem += "A vaga " + vagas[i].getPosicao() + " est� ocupada pelo " + vagas[i].getVeiculo() + "\n";
			}
		}
		if (mensagem.equalsIgnoreCase("")) {
			mensagem = "Nao ha nenhuma vaga ocupada!";
		}
		return mensagem;
	}

	public static String estacionar(Veiculo veiculo, Vaga[] vagas, String hora) {
		String mensagem = "";
		
		if (buscarVeiculo(veiculo.getPlaca(), vagas) != 51) {
			mensagem = "\nEste carro ja esta no estacionamento\n";
			return mensagem;
		}
		for (int x = 0; x <= 49; x++) {
			if (!vagas[x].getOcupado()) {
				vagas[x].setOcupado(true);
				vagas[x].setVeiculo(veiculo);
				vagas[x].setHoraEntrada(hora);
				mensagem = "\nEstacionado com sucesso as " + hora + "\n";
				return mensagem;
			} else if (x == 49) {
				mensagem = "\nNao ha vagas disponiveis\n";
				return mensagem;
			}
		}
		return mensagem;
	}

	public static Integer buscarVeiculo(String placa, Vaga[] vagas) {
		Veiculo veiculo;
		Integer posicao = 51;
		for (int x = 0; x <= 49; x++) {
			veiculo = vagas[x].getVeiculo();
			if (vagas[x].getOcupado()) {
				if (veiculo.getPlaca().compareToIgnoreCase(placa) == 0) {
					posicao = x;
				}
			}
		}
		return posicao;
	}

	public static String retirar(Integer posicao, Vaga[] vagas, String horaSaida, List<RegistroDeEntrada> registros) {
		String mensagem = "";
		
		vagas[posicao].setHoraSaida(horaSaida);
		mensagem = "\n" + vagas[posicao].getVeiculo() + ", foi retirado da vaga " + (posicao + 1) + " �s "
				+ horaSaida + "\n";
		Double valorHora = ServicoRegistroDeEntrada.calcularValorHora(vagas[posicao]);
		mensagem += "O valor foi de R$";
		mensagem += String.format("%.2f\n\n", valorHora);
		ServicoRegistroDeEntrada.atualizaRegistroDeEntrada(registros, vagas[posicao], valorHora);
		vagas[posicao] = new Vaga(posicao);
		return mensagem;
	}

	static String validarHora(Scanner input) {
		Pattern regex = Pattern.compile("^([0-1]{1})([0-9]{1})\\:([0-5]{1})([0-9]{1})$");
		Pattern regex2 = Pattern.compile("^([2]{1})([0-3]{1})\\:([0-5]{1})([0-9]{1})$");
		do {
			System.out.print("Digite o hor�rio: ");
			String hora = input.next();
			if (regex.matcher(hora).matches() || regex2.matcher(hora).matches()) {
				return hora;
			} else {
				System.err.println("\nERRO! Digite um horario dentro do padrao HH:mm e menor que 24 horas!\n");
				ThreadDelay();
			}
		} while (true);
	}

	static void ThreadDelay() {
		try {
			Thread.sleep(1L);
		} catch (InterruptedException e) {
			System.out.println("...");
		}
	}
}
