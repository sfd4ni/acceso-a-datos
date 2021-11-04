package es.iespuertodelacruz.daniel.lapices.modelo;

public class Lapiz {
	private String marca;
	private int id, numero;
	public Lapiz() {}
	public Lapiz(int id, String marca, int numero) {
		this.id = id;
		this.marca = marca;
		this.numero = numero;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String imprimirLapiz() {
		return "Lápiz: " + this.id + " " + this.marca + " " + this.numero;
	}
}
