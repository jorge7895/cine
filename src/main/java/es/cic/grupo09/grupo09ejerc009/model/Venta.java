package es.cic.grupo09.grupo09ejerc009.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "VENTA")
public class Venta extends AbstractModel {

	private static final long serialVersionUID = 7212375577490655228L;

	private LocalDateTime fhCreacion;

	private LocalDateTime fhModificado;

	@OneToMany(mappedBy = "venta")
	private List<DetalleVenta> detalleVentaVenta = new ArrayList<>();

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
