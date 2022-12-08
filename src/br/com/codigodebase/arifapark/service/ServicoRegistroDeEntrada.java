package br.com.codigodebase.arifapark.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.codigodebase.arifapark.model.RegistroDeEntrada;
import br.com.codigodebase.arifapark.model.Vaga;



public interface ServicoRegistroDeEntrada {
	
	static Double calcularValorHora(Vaga vaga) {
		LocalTime horarioDeEntrada = LocalTime.parse(vaga.getHoraEntrada());
		LocalTime horarioDeSaida = LocalTime.parse(vaga.getHoraSaida());
		Double taxa = 5.30;
		int qtdeTotalMinutos = (int) ChronoUnit.MINUTES.between(horarioDeEntrada, horarioDeSaida);
		int horas = qtdeTotalMinutos / 60;
		int minutos = qtdeTotalMinutos % 60;
		Double resultado = (horas + (minutos * 0.017)) * taxa;
		return resultado;
	}

	static void atualizaRegistroDeEntrada(List<RegistroDeEntrada> registros, Vaga vaga, Double valorHora) {
		RegistroDeEntrada registro = new RegistroDeEntrada();
		registro.setVeiculo(vaga.getVeiculo());
		registro.setHoraDeEntrada(vaga.getHoraEntrada());
		registro.setHoraDeSaida(vaga.getHoraSaida());
		registro.setValor(valorHora);
		registro.setRegistro(registros.size() + 1);
		registro.setVaga(vaga.getPosicao());
		registros.add(registro);
	}

	static String retornaRegistroDeEntradas(List<RegistroDeEntrada> registros) {
		String mensagem = "";
		for (RegistroDeEntrada registro : registros) {
			mensagem += registro;
		}
		if (registros.isEmpty()) {
			mensagem = "A lista de registros esta vazia \n";
		}
		
		return mensagem;
	}

	static String retornaValorDoDia(List<RegistroDeEntrada> registros) {
		Double valorTotal = 0.0;
		for (RegistroDeEntrada registro : registros) {
			valorTotal += registro.getValor();
		}
		String mensagem = String.format("O valor total do dia e de R$%.2f", valorTotal);
		return mensagem;

	}

}
