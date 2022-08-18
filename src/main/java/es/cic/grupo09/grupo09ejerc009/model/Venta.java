package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "VENTA")
public class Venta extends AbstractModel {

	private static final long serialVersionUID = 7212375577490655228L;

	@Min(0)
	private float importeTotal;

	@NotNull
	private LocalDateTime diaDeVenta;
	
	private boolean activa;

	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public LocalDateTime getDiaDeVenta() {
		return diaDeVenta;
	}

	public void setDiaDeVenta(LocalDateTime diaDeVenta) {
		this.diaDeVenta = diaDeVenta;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
