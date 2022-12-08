package br.com.codigodebase.arifapark.model;

public class Veiculo {

	private String modelo;
	private String placa;
	private String proprietario;
	private String rg;
	
	public Veiculo() {
		
	}
	
	public String getPlaca() {
		return placa;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public void setDocumento(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Veiculo modelo [ " + modelo + " ], placa [ " + placa + " ], proprietario(a) [ " + proprietario + " ], portador(a) do rg [ "
				+ rg + " ].";
	}
}
