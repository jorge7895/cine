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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@BeforeEach
	void setUp() throws Exception {
	}

	@Disabled
	@Test
	void testCreate() throws JsonProcessingException, Exception {
		Venta venta = new Venta();

		mockMvc.perform(post("/ventas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(venta))).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.precio", is(50.0)));
	}

	/*
	 * @Test void testCreateSesionIncorrecta() throws JsonProcessingException,
	 * Exception { Venta venta = new Venta(); venta.setSesionId(-1);
	 * venta.setSalaId(1); venta.setNumeroEntradas(10);
	 * 
	 * 
	 * mockMvc.perform(post("/ventas") .accept(MediaType.APPLICATION_JSON)
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(venta)) ) .andDo(print())
	 * .andExpect(status().isInternalServerError()); }
	 * 
	 * @Test void testCreateSalaIncorrecta() throws JsonProcessingException,
	 * Exception { Venta venta = new Venta(); venta.setSesionId(0);
	 * venta.setSalaId(-1); venta.setNumeroEntradas(10);
	 * 
	 * 
	 * mockMvc.perform(post("/ventas") .accept(MediaType.APPLICATION_JSON)
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(venta)) ) .andDo(print())
	 * .andExpect(status().isInternalServerError()); }
	 * 
	 * @Test void testCreateNumeroDeEntradasIncorrecto() throws
	 * JsonProcessingException, Exception { Venta venta = new Venta();
	 * venta.setSesionId(1); venta.setSalaId(1); venta.setNumeroEntradas(200);
	 * 
	 * 
	 * mockMvc.perform(post("/ventas") .accept(MediaType.APPLICATION_JSON)
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(venta)) ) .andDo(print())
	 * .andExpect(status().isInternalServerError()); }
	 */

}
