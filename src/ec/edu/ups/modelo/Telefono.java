package ec.edu.ups.modelo;

import java.io.Serializable;

public class Telefono implements Serializable {

	/**
	 * Atributo necesario para el envío de objetos a través de una comunicación, en
	 * este caso hacia la base de datos
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String numero;
	private String tipo;
	private String operadora;

	public Telefono() {
		// Constructor obligatorio
	}

	public Telefono(int id, String numero, String tipo, String operadora) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.operadora = operadora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	// Método toString que permite describir a un objeto
	@Override
	public String toString() {
		return "Telefono [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", operadora=" + operadora + "]";
	}

}
