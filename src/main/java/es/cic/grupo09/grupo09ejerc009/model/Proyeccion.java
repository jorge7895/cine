package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "SESION")
public class Proyeccion extends AbstractModel {

	private static final long serialVersionUID = 4073218442416202924L;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Sala sala;

	@NotNull
	private String pelicula;

	@Min(1)
	@Max(100)
	@NotNull
	private int entradasVendidas;

//	@NotNull
	private LocalDateTime horaEmpieza;

	
	private int duracionMin;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getPelicula() {
		return pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public LocalDateTime getHoraEmpieza() {
		return horaEmpieza;
	}

	public void setHoraEmpieza(LocalDateTime horaEmpieza) {
		this.horaEmpieza = horaEmpieza;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}

}
