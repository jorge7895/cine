package es.cic.curso03.curso03ejerc012.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import es.cic.curso03.curso03ejerc012.model.Apunte;
import es.cic.curso03.curso03ejerc012.model.Cuenta;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApunteTransferenciaControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper ob;
	@PersistenceContext
	private EntityManager em;

	private List<Cuenta> cuentas = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		initCuentas5();
	}

	@Test
	void testCreateApunteTransferenciaSinComisionInterna() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(0));
		apunte.setImporte(500);
		Apunte apunte2 = new Apunte();
		apunte2.setCuenta(cuentas.get(1));
		apunte2.setImporte(500);
		List<Apunte> apuntes = new ArrayList<>();
		apuntes.add(apunte);
		apuntes.add(apunte2);

		mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes))).andDo(print())
				.andExpect(status().isCreated());

		assertEquals(em.find(Cuenta.class, cuentas.get(0).getId()).getSaldo(), 500);
		assertEquals(em.find(Cuenta.class, cuentas.get(1).getId()).getSaldo(), 1500);
	}

	@Test
	void testCreateApunteTransferenciaConComisionInterna() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(0));
		apunte.setImporte(1500);
		Apunte apunte2 = new Apunte();
		apunte2.setCuenta(cuentas.get(1));
		apunte2.setImporte(1500);
		List<Apunte> apuntes = new ArrayList<>();
		apuntes.add(apunte);
		apuntes.add(apunte2);

		mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes))).andDo(print())
				.andExpect(status().isCreated());

		assertEquals(em.find(Cuenta.class, cuentas.get(0).getId()).getSaldo(), -530);
		assertEquals(em.find(Cuenta.class, cuentas.get(1).getId()).getSaldo(), 2500);
	}

	@Test
	void testCreateApunteTransferenciaSinComisionExterna() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(0));
		apunte.setImporte(500);
		Apunte apunte2 = new Apunte();
		apunte2.setExterna(true);
		apunte2.setImporte(500);
		List<Apunte> apuntes = new ArrayList<>();
		apuntes.add(apunte);
		apuntes.add(apunte2);

		mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes))).andDo(print())
				.andExpect(status().isCreated());

		assertEquals(em.find(Cuenta.class, cuentas.get(0).getId()).getSaldo(), 500);
	}

	@Test
	void testCreateApunteTransferenciaConComisionExterna() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(0));
		apunte.setImporte(2000);
		Apunte apunte2 = new Apunte();
		apunte2.setExterna(true);
		apunte2.setImporte(2000);
		List<Apunte> apuntes = new ArrayList<>();
		apuntes.add(apunte);
		apuntes.add(apunte2);

		mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes))).andDo(print())
				.andExpect(status().isCreated());

		assertEquals(em.find(Cuenta.class, cuentas.get(0).getId()).getSaldo(), -1030);
	}

	@Test
	void testCreateApunteTransferenciaErrorImporteNoIgual() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(0));
		apunte.setImporte(1500);
		Apunte apunte2 = new Apunte();
		apunte2.setCuenta(cuentas.get(1));
		apunte2.setImporte(1000);
		List<Apunte> apuntes = new ArrayList<>();
		apuntes.add(apunte);
		apuntes.add(apunte2);

		mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes))).andDo(print())
				.andExpect(status().is5xxServerError());
	}

	@Test
	void testReadTransferenciaById() throws JsonProcessingException, Exception {
		for (int i = 0; i < 2; i++) {
			Apunte apunte = new Apunte();
			apunte.setCuenta(cuentas.get(3));
			apunte.setImporte(1500);
			Apunte apunte2 = new Apunte();
			apunte2.setCuenta(cuentas.get(4));
			apunte2.setImporte(1500);
			List<Apunte> apuntes = new ArrayList<>();
			apuntes.add(apunte);
			apuntes.add(apunte2);

			mvc.perform(post("/apuntes/transferir").accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apuntes)));
		}
		mvc.perform(get("/transferencias/{id}", cuentas.get(3).getId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isFound());

		assertEquals(em.find(Cuenta.class, cuentas.get(3).getId()).getSaldo(), -2060);
		assertEquals(em.find(Cuenta.class, cuentas.get(4).getId()).getSaldo(), 4000);
	}

	private void initCuentas5() throws Exception, JsonProcessingException {
		for (int i = 0; i < 5; i++) {
			Cuenta testCuenta = new Cuenta();
			testCuenta.setIban("ES01 0000 0000 0000 005".concat(String.valueOf(i)));
			testCuenta.setSaldo(1000);
			cuentas.add(testCuenta);
			em.persist(testCuenta);
			em.flush();
		}
	}

}
