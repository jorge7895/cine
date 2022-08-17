package es.cic.grupo09.grupo09ejerc009.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "SALA")
public class Sala extends AbstractModel {

	private static final long serialVersionUID = 4073218442416202924L;

	@Min(1)
	@Max(100)
	@NotNull
	private int capacidad;

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

}
