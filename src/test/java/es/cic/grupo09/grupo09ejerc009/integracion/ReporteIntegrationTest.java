package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.util.TipoEntrada;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReporteIntegrationTest {
	
	@Autowired
	private MockMvc mvc;

	@PersistenceContext
	private EntityManager em;
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;
	private Entrada entrada;
	private Entrada entrada2;
	private LocalDateTime fecha;

	@BeforeEach
	void setUp() throws Exception {
		
		fecha = LocalDateTime.of(2022, Month.JULY, 10, 10, 30);
		
		sala = new Sala();
		sala.setAforo(100);
		em.persist(sala);
		
		proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setPelicula("El señor de los gramillos");
		proyeccion.setEntradasVendidas(proyeccion.getEntradasVendidas()+1);
		proyeccion.setHoraEmpieza(LocalDateTime.now());
		proyeccion.setFechaApertura(LocalDate.of(2022, Month.SEPTEMBER, 10));
		proyeccion.setFechaCierre(LocalDate.of(2022, Month.OCTOBER, 10));
		proyeccion.setSala(sala);
		em.persist(proyeccion);
		
		venta = new Venta();
		venta.setImporteTotal(5);
		venta.setDiaDeVenta(fecha);
		venta.setActiva(true);
		em.persist(venta);
		
		entrada = new Entrada();
		entrada.setTipoEntrada(TipoEntrada.SENIOR);
		entrada.setProyeccion(proyeccion);
		entrada.setVenta(venta);
		em.persist(entrada);
		
		entrada2 = new Entrada();
		entrada2.setTipoEntrada(TipoEntrada.NORMAL);
		entrada2.setProyeccion(proyeccion);
		entrada2.setVenta(venta);
		em.persist(entrada2);
	}

	@Test
	void obtenerVentasTotalesTest() throws Exception {
		
		mvc.perform(get("/api/v2/reporte/total")
				.param("pagina", "0")
		        .param("tamano", "10"))
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
		
		mvc.perform(get("/api/v2/reporte/proyeccion")
				.param("proyeccion",idProyeccion.toString())
				.param("sala",idSala.toString()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.content.length()",is(2)))
				.andDo(print());
	}
	
}
