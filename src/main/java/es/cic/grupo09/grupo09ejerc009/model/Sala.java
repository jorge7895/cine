package es.cic.grupo09.grupo09ejerc009.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "SALA")
public class Sala extends AbstractModel {

	private static final long serialVersionUID = 4073218442416202924L;

	private int capacidad;

	@OneToMany(mappedBy = "sala")
	private List<Sesion> sesionSala = new ArrayList<>();

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<Sesion> getSesionSala() {
		return sesionSala;
	}

	public void setSesionSala(List<Sesion> sesionSala) {
		this.sesionSala = sesionSala;
	}

}
