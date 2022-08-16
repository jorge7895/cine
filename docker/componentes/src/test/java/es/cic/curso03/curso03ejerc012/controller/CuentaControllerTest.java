package es.cic.curso03.curso03ejerc012.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import es.cic.curso03.curso03ejerc012.model.Apunte;
import es.cic.curso03.curso03ejerc012.model.Cuenta;

@SpringBootTest
@AutoConfigureMockMvc
class CuentaControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper ob;
	@PersistenceContext
	private EntityManager em;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCreateCuenta() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0001");
		testCuenta.setSaldo(1000);

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().isCreated());
	}

	@Test
	void testCreateCuentaErrorIban() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 000");
		testCuenta.setSaldo(1000);

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateCuentaErrorIbanDuplicado() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0000");
		testCuenta.setSaldo(1000);

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta)));
		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateCuentaErrorIbanNull() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setSaldo(1000);

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateCuentaErrorSaldo() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0000");
		testCuenta.setSaldo(-1);

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateCuentaErrorSaldoNull() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0000");

		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testDeleteCuenta() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0000");
		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta)));

		Cuenta cuenta = (Cuenta) em.createQuery("select c from Cuenta c").getResultList().get(0);
		mvc.perform(delete("/cuentas/{id}", cuenta.getId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isAccepted());

		cuenta = (Cuenta) em.createQuery("select c from Cuenta c").getResultList().get(0);
		assertFalse(cuenta.isActiva());
	}

	@Test
	void testDeleteCuentaError() throws JsonProcessingException, Exception {
		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 0000 0000");
		mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(testCuenta)));

		mvc.perform(
				delete("/cuentas/{id}", 7L).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	@Transactional
	void testReadClientesPotenciales() throws JsonProcessingException, Exception {
		for (int i = 0; i < 10; i++) {
			Cuenta testCuenta = new Cuenta();
			testCuenta.setIban("ES01 0000 0000 1000 000".concat(String.valueOf(i)));
			testCuenta.setSaldo(i * 1000);
			mvc.perform(post("/cuentas").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(ob.writeValueAsString(testCuenta)));
		}

		Cuenta testCuenta = new Cuenta();
		testCuenta.setIban("ES01 0000 0000 2000 0000");
		testCuenta.setSaldo(0);
		em.persist(testCuenta);

		Apunte apunte = new Apunte();
		apunte.setCuenta(testCuenta);
		apunte.setImporte(1);

		for (int i = 0; i < 11; i++) {
			mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apunte)));
		}

		mvc.perform(get("/cuentas/buscarClientesPotenciales").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isFound());
	}

}
