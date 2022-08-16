package es.cic.curso03.curso03ejerc012.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import es.cic.curso03.curso03ejerc012.util.AbstractModel;

@Entity
public class Apunte extends AbstractModel {
	private static final long serialVersionUID = 1503419836972828589L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cuenta cuenta;

	@Min(value = 1)
	private float importe;

	private boolean externa;

	@OneToMany(mappedBy = "apunte")
	private List<Ingreso> ingresoApunte = new ArrayList<>();

	@OneToMany(mappedBy = "apunte")
	private List<Ingreso> retiroApunte = new ArrayList<>();

	public Apunte() {
		externa = false;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public boolean isExterna() {
		return externa;
	}

	public void setExterna(boolean externa) {
		this.externa = externa;
	}

	public List<Ingreso> getIngresoApunte() {
		return ingresoApunte;
	}

	public void setIngresoApunte(List<Ingreso> ingresoApunte) {
		this.ingresoApunte = ingresoApunte;
	}

	public List<Ingreso> getRetiroApunte() {
		return retiroApunte;
	}

	public void setRetiroApunte(List<Ingreso> retiroApunte) {
		this.retiroApunte = retiroApunte;
	}

}