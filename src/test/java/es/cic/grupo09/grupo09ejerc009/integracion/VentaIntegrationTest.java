package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VentaIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@PersistenceContext
	private EntityManager em;
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;
	
	@BeforeEach
	void setUp()  {
		
		sala = new Sala();
		sala.setAforo(100);
		em.persist(sala);
		
		proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setPelicula("El señor de los gramillos");
		proyeccion.setEntradasVendidas(proyeccion.getEntradasVendidas()+1);
		proyeccion.setHoraEmpieza(LocalDateTime.now());
		proyeccion.setSala(sala);
		em.persist(proyeccion);
		
		venta = new Venta();
		venta.setImporteTotal(5);
		em.persist(venta);
		

	}
	
	@Test
	void testCrearVentaDeEntradaSenior() throws JsonProcessingException, Exception {
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.SENIOR);
		entrada.setProyeccion(proyeccion);
		entrada.setVenta(venta);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		 
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].venta.importeTotal",is(3.75)))
				.andDo(print());
	}
	
	@Test
	void testCrearVentaDeEntradaJoven() throws JsonProcessingException, Exception {
		
		Entrada entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.JOVEN);
		entrada.setProyeccion(proyeccion);
		entrada.setVenta(venta);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		 
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].venta.importeTotal",is(4.25)))
				.andDo(print());
	}

}
