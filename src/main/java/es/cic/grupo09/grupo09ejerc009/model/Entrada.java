package es.cic.grupo09.grupo09ejerc009.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

@Entity
@Table(name = "ENTRADA")
public class Entrada extends AbstractModel {

	private static final long serialVersionUID = -5046366033882313433L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_sesion", foreignKey = @ForeignKey(name = "fk_entrada_sesionId"))
	@NotNull
	private Sesion sesion;

	@NotNull
	private TipoEntrada descuento;

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public TipoEntrada getDescuento() {
		return descuento;
	}

	public void setDescuento(TipoEntrada descuento) {
		this.descuento = descuento;
	}

}
