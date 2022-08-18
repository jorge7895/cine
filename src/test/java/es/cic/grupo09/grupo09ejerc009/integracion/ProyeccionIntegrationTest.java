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
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

	@PersistenceContext
	private EntityManager em;
	
	private Sala sala;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sala = new Sala();
		sala.setAforo(50);
		em.persist(sala);
		
	}

	@Test
	void crearProyecciontest() throws JsonProcessingException, Exception {
		
		Proyeccion proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setEntradasVendidas(10);
		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
		proyeccion.setHoraEmpieza(LocalDateTime.now());
		proyeccion.setPelicula("La dura vida del programador");
		proyeccion.setSala(sala);
		
		mvc.perform(post("/api/v2/proyeccion")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(proyeccion)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.sala.aforo",is(50)))
				.andExpect(jsonPath("$.pelicula",is("La dura vida del programador")))
				.andDo(print());
	}

}
