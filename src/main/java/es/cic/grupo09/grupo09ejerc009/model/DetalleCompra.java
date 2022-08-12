package es.cic.grupo09.grupo09ejerc009.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.grupo09.grupo09ejerc009.util.AbstractModel;

@Entity
@Table(name = "DETALLE_COMPRA")
public class DetalleCompra extends AbstractModel {

	private static final long serialVersionUID = 7474788679925929893L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_detallecompra_1", foreignKey = @ForeignKey(name = "fk_entrada_detalleCompraId"))
	private Entrada entrada;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_detallecompra_2", foreignKey = @ForeignKey(name = "fk_venta_detalleCompraId"))
	private Venta venta;

	private float importe;

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

}
