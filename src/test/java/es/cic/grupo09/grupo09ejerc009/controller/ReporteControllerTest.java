package es.cic.grupo09.grupo09ejerc009.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;
import es.cic.grupo09.grupo09ejerc009.util.EnumDescuento;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReporteControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private VentaService ventaService;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ObjectMapper objectMapper;

	private Sala[] salas = new Sala[3];
	private Sesion[] sesiones = new Sesion[3];

	@BeforeEach
	void setUp() throws Exception {
		initSalas();
		initSesiones("Pelicula-01", "Pelicula-02", "Pelicula-03", "Pelicula-04", "Pelicula-05", "Pelicula-06",
				"Pelicula-07", "Pelicula-08", "Pelicula-09", "Pelicula-10");
	}

	@Test
	void readVentasBySesionAndSala() throws Exception {
		ventaService.create(initEntradas(10));

		mvc.perform(get("/api/v2/reporte/{id_sesion}/{id_sala}", sesiones[0].getId(), salas[0].getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isAccepted());
	}

	@Test
	void readVentasBySesion() throws Exception {
		ventaService.create(initEntradas(10));

		mvc.perform(get("/api/v2/reporte/{id_sesion}", sesiones[0].getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isAccepted());
	}

	@Test
	void readVentasByDia() throws Exception {
		ventaService.create(initEntradas(10));

		LocalDate dia = LocalDate.now();
		mvc.perform(get("/api/v2/reporte/dia").content(objectMapper.writeValueAsString(dia))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isAccepted());
	}

	@Test
	void readVentasTotales() throws Exception {
		ventaService.create(initEntradas(10));

		mvc.perform(get("/api/v2/reporte/total").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isAccepted());
	}

	private List<Entrada> initEntradas(int nEntradas) {
		List<Entrada> listaEntradasVenta = new ArrayList<>();
		for (int i = 0; i < nEntradas; i++) {
			Entrada auxEntrada = new Entrada();
			auxEntrada.setSesion(sesiones[(int) (Math.random() * sesiones.length)]);
			switch ((int) (Math.random() * 3)) {
			case 0:
				auxEntrada.setDescuento(EnumDescuento.GRUPO);
				break;
			case 1:
				auxEntrada.setDescuento(EnumDescuento.JOVEN);
				break;
			case 2:
				auxEntrada.setDescuento(EnumDescuento.TERCERA_EDAD);
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
			sesiones[i] = new Sesion();
			sesiones[i].setSala(salas[(int) (Math.random() * salas.length)]);
			sesiones[i].setAforo(sesiones[i].getSala().getCapacidad());
			sesiones[i].setPelicula(pelicula[i]);
			sesiones[i].setHoraEmpieza(LocalDateTime.now());
			sesiones[i].setDuracionMin(120);
			em.persist(sesiones[i]);
		}

	}
}
