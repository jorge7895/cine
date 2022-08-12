package es.cic.curso03.curso03ejerc012.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import es.cic.curso03.curso03ejerc012.util.AbstractModel;

@Entity
@NamedQueries({
		@NamedQuery(name = "listaIngresosByCuenta", query = "SELECT i FROM Ingreso i INNER JOIN Apunte a ON i.apunte = a.id INNER JOIN Cuenta c ON a.cuenta = c.id WHERE c.id = :cuenta") })
public class Ingreso extends AbstractModel {

	private static final long serialVersionUID = -6654862123991034915L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Apunte apunte;

	@OneToMany(mappedBy = "ingreso")
	private List<Transferencia> transferenciaIngreso = new ArrayList<>();

	public Apunte getApunte() {
		return apunte;
	}

	public void setApunte(Apunte apunte) {
		this.apunte = apunte;
	}

	public List<Transferencia> getTransferenciaIngreso() {
		return transferenciaIngreso;
	}

	public void setTransferenciaIngreso(List<Transferencia> transferenciaIngreso) {
		this.transferenciaIngreso = transferenciaIngreso;
	}

}
