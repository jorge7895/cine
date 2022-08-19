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

	@Min(30)
	@Max(100)
	@NotNull
	private int aforo;
	
	@NotNull
	private int filas;
	
	@NotNull
	private int butacasFila;

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getButacasFila() {
		return butacasFila;
	}

	public void setButacasFila(int butacasFila) {
		this.butacasFila = butacasFila;
	}

}
