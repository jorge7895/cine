package es.cic.curso03.curso03ejerc012.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import es.cic.curso03.curso03ejerc012.util.AbstractModel;

@Entity
@NamedQueries({
		@NamedQuery(name = "buscarClientesPotenciales", query = "SELECT c FROM Cuenta c WHERE c.saldo > 5000.0 OR 10 < "
				+ "(SELECT count(a) FROM Apunte a INNER JOIN Cuenta ca ON a.cuenta = ca.id WHERE ca.id = c.id)") })
public class Cuenta extends AbstractModel {
	private static final long serialVersionUID = -6955333383409912600L;

	@Column(nullable = false, unique = true)
	private String iban;

	@Column(nullable = false)
	private double saldo;

	@Column(nullable = false)
	private boolean activa;

	@OneToMany(mappedBy = "cuenta")
	private List<Apunte> apunteCuenta = new ArrayList<>();

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public String toString() {
		return "Cuenta [numCuenta=" + iban + ", saldo=" + saldo + ", activa=" + activa + "]";
	}

}
