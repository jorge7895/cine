package es.cic.curso03.curso03ejerc012.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso03.curso03ejerc012.model.Apunte;
import es.cic.curso03.curso03ejerc012.model.Cuenta;
import es.cic.curso03.curso03ejerc012.model.Ingreso;
import es.cic.curso03.curso03ejerc012.model.Retiro;
import es.cic.curso03.curso03ejerc012.model.Transferencia;
import es.cic.curso03.curso03ejerc012.repository.ApunteRepository;
import es.cic.curso03.curso03ejerc012.repository.IngresoRepository;
import es.cic.curso03.curso03ejerc012.repository.RetiroRepository;
import es.cic.curso03.curso03ejerc012.repository.TransferenciaRepository;

@Service
@Transactional
public class ApunteService {

	@Autowired
	private ApunteRepository apunteRepository;
	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private IngresoRepository ingresoRepository;
	@Autowired
	private RetiroRepository retiroRepository;
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	public Ingreso createIngresar(Apunte apunte) {
		if (!apunte.isExterna()) {
			if (Objects.equals(apunte.getCuenta(), null)) {
				throw new UnsupportedOperationException();
			}
			if (Objects.equals(apunte.getImporte(), null) || apunte.getImporte() <= 0) {
				throw new UnsupportedOperationException();
			}
		}
		apunteRepository.save(apunte);

		Ingreso auxIngreso = new Ingreso();
		auxIngreso.setApunte(apunte);
		ingresoRepository.save(auxIngreso);

		if (!apunte.isExterna()) {
			Cuenta auxCuenta = apunte.getCuenta();
			if (auxCuenta.getSaldo() < -50) {
				auxCuenta.setSaldo(auxCuenta.getSaldo() + apunte.getImporte() - 30);
			} else {
				auxCuenta.setSaldo(auxCuenta.getSaldo() + apunte.getImporte());
			}
			this.cuentaService.update(apunte.getCuenta().getId(), auxCuenta);
		}

		return auxIngreso;
	}

	public Retiro createRetirar(Apunte apunte) {
		if (Objects.equals(apunte.getCuenta(), null)) {
			throw new UnsupportedOperationException();
		}
		if (Objects.equals(apunte.getImporte(), null) || apunte.getImporte() <= 0) {
			throw new UnsupportedOperationException();
		}

		apunteRepository.save(apunte);

		Retiro auxRetiro = new Retiro();
		auxRetiro.setApunte(apunte);
		retiroRepository.save(auxRetiro);

		Cuenta auxCuenta = apunte.getCuenta();
		if ((auxCuenta.getSaldo() - apunte.getImporte()) >= -50) {
			auxCuenta.setSaldo(auxCuenta.getSaldo() - apunte.getImporte());
		} else {
			auxCuenta.setSaldo(auxCuenta.getSaldo() - apunte.getImporte() - 30);
		}
		this.cuentaService.update(apunte.getCuenta().getId(), auxCuenta);

		return auxRetiro;
	}

	public Transferencia createTransferencia(List<Apunte> apuntes) {
		if (!Objects.equals(apuntes.get(0).getImporte(), apuntes.get(1).getImporte())) {
			throw new UnsupportedOperationException();
		}
		Transferencia transferencia = new Transferencia();
		transferencia.setRetiro(createRetirar(apuntes.get(0)));
		transferencia.setIngreso(createIngresar(apuntes.get(1)));

		transferenciaRepository.save(transferencia);
		return transferencia;

	}
}
