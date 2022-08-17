package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "VENTA")
//@NamedQueries({
//		@NamedQuery(name = "listaFilterSesionAndSala", query = "SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion AND sa = :sala"),
//		@NamedQuery(name = "listaFilterSesion", query = "SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion"),
//		@NamedQuery(name = "listaFilterDia", query = "SELECT v FROM Venta v WHERE v.fhCreacion >= :dia AND v.fhCreacion < :dia2") })
public class Venta extends AbstractModel {

	private static final long serialVersionUID = 7212375577490655228L;

	@Min(0)
	private float importeTotal;

//	@NotNull
	private LocalDateTime diaDeVenta;

	private LocalDateTime fhModificado;

	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public LocalDateTime getFhModificado() {
		return fhModificado;
	}

	public void setFhModificado(LocalDateTime fhModificado) {
		this.fhModificado = fhModificado;
	}

	public LocalDateTime getDiaDeVenta() {
		return diaDeVenta;
	}

	public void setDiaDeVenta(LocalDateTime diaDeVenta) {
		this.diaDeVenta = diaDeVenta;
	}

}
