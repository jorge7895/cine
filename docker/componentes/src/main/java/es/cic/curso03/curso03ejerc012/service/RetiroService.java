package es.cic.curso03.curso03ejerc012.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso03.curso03ejerc012.model.Retiro;
import es.cic.curso03.curso03ejerc012.repository.RetiroRepository;

@Service
@Transactional
public class RetiroService {
	@Autowired
	private RetiroRepository retiroRepository;

	public List<Retiro> readRetirosById(Long id) {
		return retiroRepository.readRetirosById(id);
	}
}
