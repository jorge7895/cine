package es.cic.curso03.curso03ejerc012.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import es.cic.curso03.curso03ejerc012.util.AbstractModel;

@Entity
@NamedQueries({
		@NamedQuery(name = "listaTransferenciasByCuenta", query = "SELECT t FROM Transferencia t INNER JOIN Retiro r ON t.ingreso = r.id INNER JOIN Apunte a ON r.apunte = a.id INNER JOIN Cuenta c ON a.cuenta = c.id WHERE c.id = :cuenta") })
public class Transferencia extends AbstractModel {

	private static final long serialVersionUID = -2060976627307852795L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Retiro retiro;

	@ManyToOne(fetch = FetchType.LAZY)
	private Ingreso ingreso;

	public Retiro getRetiro() {
		return retiro;
	}

	public void setRetiro(Retiro retiro) {
		this.retiro = retiro;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

}
