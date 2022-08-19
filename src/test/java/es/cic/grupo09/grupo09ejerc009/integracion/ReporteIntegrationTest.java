package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;
import es.cic.grupo09.grupo09ejerc009.util.VentaTestUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReporteIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@PersistenceContext
	private EntityManager em;
	
	private VentaTestUtil ventaTestUtil = new VentaTestUtil();
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;
	private Entrada entrada;
	private Entrada entrada2;
	private LocalDateTime fecha;

	@BeforeEach
	void setUp() throws Exception {
		
		fecha = LocalDateTime.of(2022, Month.JULY, 10, 10, 30);
		
		sala = ventaTestUtil.getSala();
		em.persist(sala);
		
		proyeccion = ventaTestUtil.getProyeccion();
		em.persist(proyeccion);
		
		venta = ventaTestUtil.getVenta();
		venta.setDiaDeVenta(fecha);
		em.persist(venta);
		
		entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		em.persist(entrada);
		
		entrada2 = ventaTestUtil.crearEntrada(proyeccion, venta);
		entrada2.setTipoEntrada(TipoEntrada.NORMAL);
		entrada2.setButaca(6);
		em.persist(entrada2);
	}

	@Test
	void obtenerVentasTotalesTest() throws Exception {
		
		mvc.perform(get("/api/v2/reporte/total"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content.length()",is(2)))
				.andDo(print());
	}
	
	@Test
	void obtenerVentasPorDiaTest() throws Exception {
		
		mvc.perform(get("/api/v2/reporte/dia")
				.param("dia",fecha.toString()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content.length()",is(2)))
				.andDo(print());
	}
	
	@Test
	void obtenerVentasPorDiaNoExisteTest() throws Exception {
		
		fecha = LocalDateTime.of(2022, Month.JULY, 2, 10, 30);
		
		mvc.perform(get("/api/v2/reporte/dia")
				.param("dia",fecha.toString()))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}

	@Test
	void obtenerVentasPorProyeccionTest() throws Exception {
		
		StringBuilder idProyeccion = new StringBuilder();
		idProyeccion.append(proyeccion.getId());
		
		mvc.perform(get("/api/v2/reporte/proyeccion")
				.param("proyeccion",idProyeccion.toString()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content.length()",is(2)))
				.andDo(print());
	}
	
	@Test
	void obtenerVentasPorProyeccionSalaTest() throws Exception {
		
		StringBuilder idProyeccion = new StringBuilder();
		idProyeccion.append(proyeccion.getId());
		
		StringBuilder idSala = new StringBuilder();
		idSala.append(sala.getId());
		
		mvc.perform(get("/api/v2/reporte/proyeccion/sala")
				.param("idProyeccion",idProyeccion.toString())
				.param("idSala",idSala.toString()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content.length()",is(2)))
				.andDo(print());
	}
	
}
