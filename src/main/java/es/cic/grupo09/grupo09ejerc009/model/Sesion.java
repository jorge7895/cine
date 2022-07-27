package es.cic.grupo09.grupo09ejerc009.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sesion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "sala_id",nullable = false)
	private long salaId;
	
	private int butacas;

	public long getId() {
		return id;
	}

	public long getSalaId() {
		return salaId;
	}

	public int getButacas() {
		return butacas;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSalaId(long salaId) {
		this.salaId = salaId;
	}

	public void setButacas(int butacas) {
		this.butacas = butacas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, salaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return id == other.id && salaId == other.salaId;
	}
	
	
	
	
	
	
}
