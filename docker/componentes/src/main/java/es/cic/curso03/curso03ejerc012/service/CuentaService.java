package es.cic.curso03.curso03ejerc012.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso03.curso03ejerc012.model.Cuenta;
import es.cic.curso03.curso03ejerc012.repository.CuentaRepository;

@Service
@Transactional
public class CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	public void create(Cuenta c) {
		if (Objects.equals(c.getIban(), null) || StringUtils.deleteWhitespace(c.getIban()).length() < 20) {
			throw new UnsupportedOperationException();
		}
		if (Objects.equals(c.getSaldo(), null) || c.getSaldo() < 0) {
			throw new UnsupportedOperationException();
		}
		c.setActiva(true);
		this.cuentaRepository.save(c);
	}

	public Cuenta readById(Long id) {
		return this.cuentaRepository.findById(id).get();
	}

	public Cuenta readByIban(String iban) {
		return this.cuentaRepository.findByIban(iban);
	}
	
	public List<Cuenta> buscarClientesPotenciales(){
		return this.cuentaRepository.buscarClientesPotenciales();
	}

	public void update(Long id, Cuenta auxCuenta) {
		Cuenta cuenta = this.cuentaRepository.findById(id).get();
		cuenta = auxCuenta;
		this.cuentaRepository.save(cuenta);

	}

	public void delete(Long id) {
		Cuenta cuenta = this.cuentaRepository.findById(id).get();
		cuenta.setActiva(false);
		this.cuentaRepository.save(cuenta);
	}

}
