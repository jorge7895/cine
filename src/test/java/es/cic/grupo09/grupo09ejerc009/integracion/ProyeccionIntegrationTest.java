package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Sala;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProyeccionIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

	@PersistenceContext
	private EntityManager em;
	
	private Sala sala;
	private Sala sala1;
	private Sala sala2;
	
	@BeforeEach
	void setUp() throws Exception {
		
		transactionTemplate = new TransactionTemplate(transactionManager);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	        @Override
	        protected void doInTransactionWithoutResult(TransactionStatus status) {
	        	sala = new Sala();
	    		sala.setAforo(50);
	    		sala.setButacasFila(5);
	    		sala.setFilas(10);
	    		em.persist(sala);
	    		
	    		sala1 = new Sala();
	    		sala1.setAforo(30);
	    		sala1.setButacasFila(6);
	    		sala1.setFilas(5);
	    		em.persist(sala1);
	    		
	    		sala2 = new Sala();
	    		sala2.setAforo(100);
	    		sala2.setButacasFila(10);
	    		sala2.setFilas(10);
	    		em.persist(sala2);
	    		
	    		Proyeccion proyeccion = new Proyeccion();
	    		proyeccion.setDuracionMin(120);
	    		proyeccion.setEntradasVendidas(0);
	    		proyeccion.setSesion(1);
	    		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
	    		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
	    		proyeccion.setHoraProyeccion(LocalDateTime.of(2022, Month.SEPTEMBER, 11, 17, 00));
	    		proyeccion.setPelicula("La dura vida del programador");
	    		proyeccion.setSala(sala);
	    		em.persist(proyeccion);
	        }
	        	
        });
		
		
		
	}

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	void crearProyeccionRepetidatest() throws JsonProcessingException, Exception {
		
		Proyeccion proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setEntradasVendidas(0);
		proyeccion.setSesion(1);
		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
		proyeccion.setHoraProyeccion(LocalDateTime.of(2022, Month.SEPTEMBER, 11, 17, 00));
		proyeccion.setPelicula("La dura vida del programador");
		proyeccion.setSala(sala);
		
		mvc.perform(post("/api/v2/proyeccion")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(proyeccion)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	void crearProyecciontest() throws JsonProcessingException, Exception {
		
		Proyeccion proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setEntradasVendidas(0);
		proyeccion.setSesion(1);
		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
		proyeccion.setHoraProyeccion(LocalDateTime.of(2022, Month.SEPTEMBER, 11, 22, 00));
		proyeccion.setPelicula("La dura vida del programador");
		proyeccion.setSala(sala);
		
		mvc.perform(post("/api/v2/proyeccion")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(proyeccion)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.pelicula",is("La dura vida del programador")))
				.andDo(print());
	}

}
