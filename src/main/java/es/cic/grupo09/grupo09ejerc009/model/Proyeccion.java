package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(uniqueConstraints ={@UniqueConstraint(name = "UniqueProyeccion", columnNames = { "sala_id", "sesion", "horaProyeccion" })})
public class Proyeccion extends AbstractModel {

	private static final long serialVersionUID = 4073218442416202924L;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Sala sala;

	@NotNull
	private String pelicula;

	@NotNull
	private int entradasVendidas;

	@NotNull
	private LocalDateTime horaProyeccion;
	
	@Min(0)
	@Max(3)
	private int sesion;
	
	@NotNull
	private LocalDate fechaApertura;
	
	@NotNull
	private LocalDate fechaCierre;
	
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

	public LocalDateTime getHoraProyeccion() {
		return horaProyeccion;
	}

	public void setHoraProyeccion(LocalDateTime horaProyeccion) {
		this.horaProyeccion = horaProyeccion;
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

	public LocalDate getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public int getSesion() {
		return sesion;
	}

	public void setSesion(int sesion) {
		this.sesion = sesion;
	}

}
