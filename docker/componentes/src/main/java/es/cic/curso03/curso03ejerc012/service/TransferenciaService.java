package es.cic.curso03.curso03ejerc012.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso03.curso03ejerc012.model.Transferencia;
import es.cic.curso03.curso03ejerc012.repository.TransferenciaRepository;

@Service
@Transactional
public class TransferenciaService {
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	public List<Transferencia> readTransferenciasById(Long id) {
		return transferenciaRepository.readTransferenciasById(id);
	}
}
