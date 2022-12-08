package br.com.codigodebase.arifapark.model;



public class Vaga {

	private Integer numeroDaVaga;
	private String horaEntrada;
	private String horaSaida;
	private Veiculo veiculo;
	private Boolean ocupado;
	
	public Vaga(Integer numeroDaVaga) {
		this.ocupado = false;
		this.numeroDaVaga = numeroDaVaga;
	}

	public Integer getPosicao() {
		return numeroDaVaga;
	}

	public void setPosicao(Integer numeroDaVaga) {
		this.numeroDaVaga = numeroDaVaga;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	@Override
	public String toString() {
		return "Vaga #" + numeroDaVaga;
	}
}
