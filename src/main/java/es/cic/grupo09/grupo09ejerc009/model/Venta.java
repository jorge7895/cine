package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "VENTA")
@NamedQueries({
		@NamedQuery(name = "listaFilterSesionAndSala", query = "SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion AND sa = :sala"),
		@NamedQuery(name = "listaFilterSesion", query = "SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion"),
		@NamedQuery(name = "listaFilterDia", query = "SELECT v FROM Venta v WHERE v.fhCreacion >= :dia AND v.fhCreacion < :dia2") })
public class Venta extends AbstractModel {

	private static final long serialVersionUID = 7212375577490655228L;

	private float importeTotal;

	private LocalDateTime fhCreacion;

	private LocalDateTime fhModificado;

	@OneToMany(mappedBy = "venta")
	private List<DetalleVenta> detalleVentaVenta = new ArrayList<>();

	public float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public LocalDateTime getFhCreacion() {
		return fhCreacion;
	}

	public void setFhCreacion(LocalDateTime fhCreacion) {
		this.fhCreacion = fhCreacion;
	}

	public LocalDateTime getFhModificado() {
		return fhModificado;
	}

	public void setFhModificado(LocalDateTime fhModificado) {
		this.fhModificado = fhModificado;
	}

	public List<DetalleVenta> getDetalleVentaVenta() {
		return detalleVentaVenta;
	}

	public void setDetalleVentaVenta(List<DetalleVenta> detalleVentaVenta) {
		this.detalleVentaVenta = detalleVentaVenta;
	}

}
