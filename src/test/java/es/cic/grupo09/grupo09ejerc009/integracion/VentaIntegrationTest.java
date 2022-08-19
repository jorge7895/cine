package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;
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
import es.cic.grupo09.grupo09ejerc009.util.VentaTestUtil;

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
	
	private VentaTestUtil ventaTestUtil = new VentaTestUtil();
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;
	
	@BeforeEach
	void setUp()  {
		
		sala = ventaTestUtil.getSala();
		em.persist(sala);
		
		proyeccion = ventaTestUtil.getProyeccion();
		em.persist(proyeccion);
		
		venta = ventaTestUtil.getVenta();
		em.persist(venta);
	}


	
	@Test
	void testCrearVentaDeEntradaSenior() throws JsonProcessingException, Exception {
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		
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
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		
		entrada.setTipoEntrada(TipoEntrada.JOVEN);
		
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

	@Test
	void testDevolverVenta() throws JsonProcessingException, Exception {
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		em.persist(entrada);
		
		StringBuilder idVenta = new StringBuilder();
		idVenta.append(venta.getId());
		
		mvc.perform(delete("/api/v2/venta/devolucion")
				.param("idVenta",idVenta.toString()))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
	}
	
	@Test
	void testModificarVenta() throws JsonProcessingException, Exception {
		
		Entrada entradaOriginal = ventaTestUtil.crearEntrada(proyeccion, venta);
		em.persist(entradaOriginal);
		
		Entrada entradaModificada = ventaTestUtil.crearEntrada(proyeccion, venta);
		entradaModificada.setTipoEntrada(TipoEntrada.JOVEN);
		entradaModificada.setButaca(3);
		entradaOriginal.setFila(2);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entradaModificada);
		
		StringBuilder idVenta = new StringBuilder();
		idVenta.append(venta.getId());
		
		mvc.perform(put("/api/v2/venta/modificacion")
				.param("ventaId",idVenta.toString())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.[0].proyeccion.pelicula",is("La dura vida del programador")))
				.andExpect(jsonPath("$.[0].venta.importeTotal",is(4.25)))
				.andDo(print());
	}
	
	@Test
	void testCrearVentaAforoLleno() throws JsonProcessingException, Exception {
		
		proyeccion.setEntradasVendidas(50);
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	void testCrearVentaAntesDeAperturaDeProyeccion() throws JsonProcessingException, Exception {
		
		venta.setDiaDeVenta(LocalDateTime.of(2022, Month.SEPTEMBER, 5, 22, 00));
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	void testCrearVentaDespuesDeCierreDeProyeccion() throws JsonProcessingException, Exception {
		
		venta.setDiaDeVenta(LocalDateTime.of(2022, Month.OCTOBER, 29, 22, 00));
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	void testCrearVentaButacaNoDisponible() throws JsonProcessingException, Exception {
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		entrada.setButaca(6);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
	
	@Test
	void testCrearVentaFilaNoDisponible() throws JsonProcessingException, Exception {
		
		Entrada entrada = ventaTestUtil.crearEntrada(proyeccion, venta);
		entrada.setFila(11);
		
		List<Entrada> entradas = new ArrayList<>();
		entradas.add(entrada);
		
		mvc.perform(post("/api/v2/venta")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(entradas)))
				.andExpect(status().is4xxClientError())
				.andDo(print());
	}
}
