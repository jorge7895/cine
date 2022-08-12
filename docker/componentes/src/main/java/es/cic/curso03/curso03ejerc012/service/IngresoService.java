package es.cic.curso03.curso03ejerc012.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso03.curso03ejerc012.model.Ingreso;
import es.cic.curso03.curso03ejerc012.repository.IngresoRepository;

@Service
@Transactional
public class IngresoService {
	@Autowired
	private IngresoRepository ingresoRepository;

	public List<Ingreso> readIngresosById(Long id) {
		return ingresoRepository.listaIngresoByCuenta(id);
	}
}
