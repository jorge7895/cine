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
class ApunteIngresosControllerTest {

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
	void testCreateApunteIngresoSinComision() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(1));
		apunte.setImporte(500);

		mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(apunte))).andDo(print()).andExpect(status().isCreated());
		assertEquals(em.find(Cuenta.class, cuentas.get(1).getId()).getSaldo(), 1500);
	}

	@Test
	void testCreateApunteIngresoConComision() throws JsonProcessingException, Exception {
		cuentas.get(1).setSaldo(-100);
		em.persist(cuentas.get(1));
		em.flush();

		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(1));
		apunte.setImporte(100);

		mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(apunte))).andDo(print()).andExpect(status().isCreated());
		assertEquals(em.find(Cuenta.class, cuentas.get(1).getId()).getSaldo(), -30);
	}

	@Test
	void testCreateApunteIngresoErrorCuentaNull() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setImporte(0);

		mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(apunte))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateApunteIngresoErrorImporte() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(1));
		apunte.setImporte(0);

		mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(apunte))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testCreateApunteIngresoErrorImporteNull() throws JsonProcessingException, Exception {
		Apunte apunte = new Apunte();
		apunte.setCuenta(cuentas.get(1));

		mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(apunte))).andDo(print()).andExpect(status().is5xxServerError());
	}

	@Test
	void testReadIngresosByCuenta() throws JsonProcessingException, Exception {
		for (int i = 0; i < 5; i++) {
			Apunte apunte = new Apunte();
			apunte.setCuenta(cuentas.get(0));
			apunte.setImporte((i + 1) * 1000);

			mvc.perform(post("/apuntes/ingresar").accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON).content(ob.writeValueAsString(apunte)))
					.andExpect(status().isCreated());
		}

		mvc.perform(get("/ingresos/{id}", cuentas.get(0).getId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isFound());
	}

	private void initCuentas5() throws Exception, JsonProcessingException {
		for (int i = 0; i < 5; i++) {
			Cuenta testCuenta = new Cuenta();
			testCuenta.setIban("ES01 0000 0000 0000 001".concat(String.valueOf(i)));
			testCuenta.setSaldo(1000);
			cuentas.add(testCuenta);
			em.persist(testCuenta);
			em.flush();
		}
	}

}
