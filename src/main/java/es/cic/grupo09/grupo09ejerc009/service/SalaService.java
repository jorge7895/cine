package es.cic.grupo09.grupo09ejerc009.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.repository.SalaRepository;

@Service
@Transactional
public class SalaService {
	@Autowired
	private SalaRepository salaRepository;
}
