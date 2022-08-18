package es.cic.grupo09.grupo09ejerc009.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

@Entity
@Table(name = "ENTRADA")
public class Entrada extends AbstractModel {

	private static final long serialVersionUID = -5046366033882313433L;
	
	private final int PRECIO_ENTRADA = 5;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Proyeccion proyeccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Venta venta;

	@NotNull
	private TipoEntrada tipoEntrada;
	
	private boolean activa;

	public Proyeccion getProyeccion() {
		return proyeccion;
	}

	public void setProyeccion(Proyeccion proyeccion) {
		this.proyeccion = proyeccion;
	}

	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(TipoEntrada tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public int getPrecioEntrada() {
		return PRECIO_ENTRADA;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
