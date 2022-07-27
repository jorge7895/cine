package es.cic.grupo09.grupo09ejerc009.model;

import java.util.Objects;

public class Venta {

	private long id;
	private int numeroEntradas;
	
	private double precio;
	
	private int sesionId;
	
	private int salaId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumeroEntradas() {
		return numeroEntradas;
	}

	public void setNumeroEntradas(int numeroEntradas) {
		this.numeroEntradas = numeroEntradas;
	}

	public int getSesionId() {
		return sesionId;
	}

	public void setSesionId(int sesionId) {
		this.sesionId = sesionId;
	}

	public int getSalaId() {
		return salaId;
	}

	public void setSalaId(int salaId) {
		this.salaId = salaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", numeroEntradas=" + numeroEntradas + ", sesionId=" + sesionId + ", salaId="
				+ salaId + "]";
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
