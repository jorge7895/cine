package es.cic.curso03.curso03ejerc012.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso03.curso03ejerc012.model.Apunte;

@Repository
public interface ApunteRepository extends CrudRepository<Apunte, Long>{

}
