package es.cic.grupo09.grupo09ejerc009.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VentaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PersistenceContext
	private EntityManager em;
	
	private Venta venta1;
	private Venta venta2;
	
	
	@BeforeEach
	void setUp() throws Exception {
/*		venta1 = new Venta();
		venta1.setNumeroEntradas(5);
		venta1.setSalaId(1);
		venta1.setSesionId(1);
		venta1.setPrecio(25);
		
		venta2 = new Venta();
		venta2.setNumeroEntradas(5);
		venta2.setSalaId(1);
		venta2.setSesionId(1);
		venta2.setPrecio(25);
		
		em.persist(venta1);
		em.persist(venta2); */
		
		Sesion sesion1=new Sesion();
		sesion1.setSalaId(1);
		sesion1.setButacas(100);
		
		Sesion sesion2=new Sesion();
		sesion2.setSalaId(1);
		sesion2.setButacas(100);
		
		Sesion sesion3=new Sesion();
		sesion3.setSalaId(1);
		sesion3.setButacas(100);
		
		Sesion sesion4=new Sesion();
		sesion4.setSalaId(2);
		sesion4.setButacas(50);
		
		Sesion sesion5=new Sesion();
		sesion5.setSalaId(2);
		sesion5.setButacas(50);
		
		Sesion sesion6=new Sesion();
		sesion6.setSalaId(2);
		sesion6.setButacas(30);
		
		Sesion sesion7=new Sesion();
		sesion7.setSalaId(3);
		sesion7.setButacas(30);
		
		Sesion sesion8=new Sesion();
		sesion8.setSalaId(3);
		sesion8.setButacas(30);
		
		Sesion sesion9=new Sesion();
		sesion9.setSalaId(3);
		sesion9.setButacas(30);
		
		em.persist(sesion1);
		em.persist(sesion2);
		em.persist(sesion3);
		em.persist(sesion4);
		em.persist(sesion5);
		em.persist(sesion6);
		em.persist(sesion7);
		em.persist(sesion8);
		em.persist(sesion9);
	}

	@Test
	void testCreate() throws JsonProcessingException, Exception {
		Venta venta = new Venta();
		venta.setSesionId(3);
		venta.setSalaId(3);
		venta.setNumeroEntradas(10);
		
		
		mockMvc.perform(post("/ventas")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(venta))
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.precio", is(50.0)))
		;
	}
	
 /* @Test
	void testCreateSesionIncorrecta() throws JsonProcessingException, Exception {
		Venta venta = new Venta();
		venta.setSesionId(-1);
		venta.setSalaId(1);
		venta.setNumeroEntradas(10);
		
		
		mockMvc.perform(post("/ventas")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(venta))
				)
		.andDo(print())
		.andExpect(status().isInternalServerError());
	}
	
	@Test
	void testCreateSalaIncorrecta() throws JsonProcessingException, Exception {
		Venta venta = new Venta();
		venta.setSesionId(0);
		venta.setSalaId(-1);
		venta.setNumeroEntradas(10);
		
		
		mockMvc.perform(post("/ventas")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(venta))
				)
		.andDo(print())
		.andExpect(status().isInternalServerError());
	}
	
	@Test
	void testCreateNumeroDeEntradasIncorrecto() throws JsonProcessingException, Exception {
		Venta venta = new Venta();
		venta.setSesionId(1);
		venta.setSalaId(1);
		venta.setNumeroEntradas(200);
		
		
		mockMvc.perform(post("/ventas")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(venta))
				)
		.andDo(print())
		.andExpect(status().isInternalServerError());
	}*/

}
