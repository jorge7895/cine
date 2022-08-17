package es.cic.grupo09.grupo09ejerc009.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VentaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private VentaService ventaService;

	@PersistenceContext
	private EntityManager em;
	
	private static Sala[] salas = new Sala[3];
	private static Proyeccion[] sesiones = new Proyeccion[3];

	@BeforeEach
	void setUp() throws Exception {
		initSalas();
		initSesiones("Pelicula-01", "Pelicula-02", "Pelicula-03", "Pelicula-04", "Pelicula-05", "Pelicula-06",
				"Pelicula-07", "Pelicula-08", "Pelicula-09", "Pelicula-10");
	}

	@Test
	void crearVentaTest() throws JsonProcessingException, Exception {
		
		List<Entrada> entradas = initEntradas(10);		
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()",is(10)));
	}
	
	@Test
	void updateVentaTest() throws JsonProcessingException, Exception {
		
		List<Entrada> entradas = initEntradas(1);
		entradas.get(0).setDescuento(TipoEntrada.JOVEN);
		
		Venta nuevaVenta = ventaService.create(entradas);
		
		entradas.get(0).setDescuento(TipoEntrada.TERCERA_EDAD);
		
		mvc.perform(put("/api/v2/venta/devolucion/{1}",nuevaVenta.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].descuento",is("TERCERA_EDAD")));
	}

	private List<Entrada> initEntradas(int nEntradas) {
		List<Entrada> listaEntradasVenta = new ArrayList<>();
		for (int i = 0; i < nEntradas; i++) {
			Entrada auxEntrada = new Entrada();
			auxEntrada.setSesion(sesiones[(int) (Math.random() * sesiones.length)]);
			switch ((int) (Math.random() * 3)) {
			case 0:
				auxEntrada.setDescuento(TipoEntrada.GRUPO);
				break;
			case 1:
				auxEntrada.setDescuento(TipoEntrada.JOVEN);
				break;
			case 2:
				auxEntrada.setDescuento(TipoEntrada.TERCERA_EDAD);
				break;
			}
			listaEntradasVenta.add(auxEntrada);
		}
		return listaEntradasVenta;
	}

	private void initSalas() {

		for (int i = 0; i < salas.length; i++) {
			salas[i] = new Sala();
			switch (i) {
			case 0:
				salas[i].setCapacidad(100);
				break;
			case 1:
				salas[i].setCapacidad(50);
				break;
			case 2:
				salas[i].setCapacidad(30);
				break;
			}
			em.persist(salas[i]);
		}

	}

	private void initSesiones(String... pelicula) {

		for (int i = 0; i < sesiones.length; i++) {
			sesiones[i] = new Proyeccion();
			sesiones[i].setSala(salas[(int) (Math.random() * salas.length)]);
			sesiones[i].setAforo(sesiones[i].getSala().getCapacidad());
			sesiones[i].setPelicula(pelicula[i]);
			sesiones[i].setHoraEmpieza(LocalDateTime.now());
			sesiones[i].setDuracionMin(120);
			em.persist(sesiones[i]);
		}

	}
}
