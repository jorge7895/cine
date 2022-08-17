package es.cic.grupo09.grupo09ejerc009.integracion;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

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

	@Autowired
	private ObjectMapper objectMapper;

	@PersistenceContext
	private EntityManager em;
	
	private Sala sala;
	private Proyeccion proyeccion;
	private Venta venta;
	private Entrada entrada;
	private Entrada entrada2;

	@BeforeEach
	void setUp() throws Exception {
		
		sala = new Sala();
		sala.setAforo(100);
		em.persist(sala);
		
		proyeccion = new Proyeccion();
		proyeccion.setDuracionMin(120);
		proyeccion.setPelicula("El señor de los gramillos");
		proyeccion.setEntradasVendidas(proyeccion.getEntradasVendidas()+1);
		proyeccion.setHoraEmpieza(LocalDateTime.now());
		proyeccion.setSala(sala);
		em.persist(proyeccion);
		
		venta = new Venta();
		venta.setImporteTotal(5);
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

}
