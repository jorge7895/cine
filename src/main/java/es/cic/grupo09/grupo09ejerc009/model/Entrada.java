package es.cic.grupo09.grupo09ejerc009.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;
import es.cic.grupo09.grupo09ejerc009.util.EnumDescuento;

@Entity
@Table(name = "ENTRADA")
public class Entrada extends AbstractModel {

	private static final long serialVersionUID = -5046366033882313433L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_sesion", foreignKey = @ForeignKey(name = "fk_entrada_sesionId"))
	private Sesion sesion;

	private EnumDescuento descuento;

	@OneToMany(mappedBy = "entrada")
	private List<DetalleVenta> detalleVentaEntrada = new ArrayList<>();

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public EnumDescuento getDescuento() {
		return descuento;
	}

	public void setDescuento(EnumDescuento descuento) {
		this.descuento = descuento;
	}

	public List<DetalleVenta> getDetalleVentaEntrada() {
		return detalleVentaEntrada;
	}

	public void setDetalleVentaEntrada(List<DetalleVenta> detalleVentaEntrada) {
		this.detalleVentaEntrada = detalleVentaEntrada;
	}

}
