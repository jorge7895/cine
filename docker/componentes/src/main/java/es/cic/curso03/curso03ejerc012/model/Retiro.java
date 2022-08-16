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
		@NamedQuery(name = "listaRetirosByCuenta", query = "SELECT r FROM Retiro r INNER JOIN Apunte a ON r.apunte = a.id INNER JOIN Cuenta c ON a.cuenta = c.id WHERE c.id = :cuenta") })
public class Retiro extends AbstractModel {

	private static final long serialVersionUID = -7128663402269083296L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Apunte apunte;

	@OneToMany(mappedBy = "retiro")
	private List<Transferencia> transferenciaRetiro = new ArrayList<>();

	public Apunte getApunte() {
		return apunte;
	}

	public void setApunte(Apunte apunte) {
		this.apunte = apunte;
	}

	public List<Transferencia> getTransferenciaRetiro() {
		return transferenciaRetiro;
	}

	public void setTransferenciaRetiro(List<Transferencia> transferenciaRetiro) {
		this.transferenciaRetiro = transferenciaRetiro;
	}

}
