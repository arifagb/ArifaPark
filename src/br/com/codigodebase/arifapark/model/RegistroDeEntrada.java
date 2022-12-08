package br.com.codigodebase.arifapark.model;



public class RegistroDeEntrada {

	private Integer registro;
	private Veiculo veiculo;
	private String horaDeEntrada;
	private String horaDeSaida;
	private Double valor;
	private Integer vaga;
	
	public int getRegistro() {
		return registro;
	}
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public String getHoraDeEntrada() {
		return horaDeEntrada;
	}
	public String getHoraDeSaida() {
		return horaDeSaida;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public void setHoraDeEntrada(String horaDeEntrada) {
		this.horaDeEntrada = horaDeEntrada;
	}
	public void setHoraDeSaida(String horaDeSaida) {
		this.horaDeSaida = horaDeSaida;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}
	public Integer getVaga() {
		return vaga;
	}
	public void setVaga(Integer vaga) {
		this.vaga = vaga;
	}
	public RegistroDeEntrada () {
		
	}
	
	@Override
	public String toString() {
		String valorFormatado = String.format("%.2f\n\n", this.valor);
		
		return "Registro #" + registro + ": veiculo de placa [ " + veiculo.getPlaca() + "] deu entrada no estacionamento às " + horaDeEntrada +
				", na vaga #" + vaga + ", foi removido da vaga às " + horaDeSaida + " e pagou R$" + valorFormatado;
	}
}
